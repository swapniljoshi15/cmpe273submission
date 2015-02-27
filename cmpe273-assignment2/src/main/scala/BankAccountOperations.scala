package main.scala

import java.util.HashMap
import java.util.ArrayList
import org.springframework.context.annotation.Bean
import org.springframework.web.client.HttpClientErrorException

object BankAccountOperations {

  var WalletDao = new WalletDaoImpl

  def createBankAccount(user_idS: String, bank_account: BankAccount): BankAccount = {

    //validate userid
    var user_id = WalletUtil.convertStringIdtoInt(user_idS)
    var user = WalletDao.getObject(user_id.toString)
    if (user == null) throw new Exception("User Not Found")
    /*if (!WalletUtil.validateInteger(user_id)) return null*/

    //validate Bank Account
    if (!WalletUtil.validateString(bank_account.getAccount_number)) return null
    if (!WalletUtil.validateString(bank_account.getRouting_number)) return null

    //generate random id for id card no
    var randomNumber = 0
    do {
      randomNumber = scala.util.Random.nextInt(10000)
    } while (WalletUtil.bankAccountExists(randomNumber, user) || randomNumber < 0);

    //add id card
    bank_account.setBa_id(randomNumber)
    user.bankaccounts.put(randomNumber, bank_account)

    //get routing info from service
    var routingInformationClient = new RoutingInformationClient()
    try {
      var routingInformation = routingInformationClient.getRoutingInformation(bank_account.getRouting_number)
      if (routingInformation != null && routingInformation.getCode == "200") {
        bank_account.setAccount_name(routingInformation.getCustomer_name)
        WalletDao.removeObject(user.getUser_id)
        WalletDao.saveObject(user)
      } else {
        // throw new Exception("Not found inforamation for routing no "+bank_account.getRouting_number)
        if(routingInformation.getCode == "404") throw new InvalidMethodParameterException("Invalid routing number.")
      }

    } catch {
      case ex: HttpClientErrorException => if (ex.getStatusCode() == 404) throw ex
      case ex: InvalidMethodParameterException => throw ex
      case ex: Exception => throw new Exception("Not found inforamation for routing no " + bank_account.getRouting_number)
    }

    return bank_account
  }

  def getBankAccounts(user_idS: String): ArrayList[BankAccount] = {

    //validate userid
    var user_id = WalletUtil.convertStringIdtoInt(user_idS)
    var user = WalletDao.getObject(user_id.toString)
    if (user == null) throw new Exception("User Not Found")
    /*if (!WalletUtil.validateInteger(user_id)) return null*/

    return WalletUtil.mapToArrayListBankAccounts(user.bankaccounts)
  }

  def deleteBankAccount(user_idS: String, ba_idS: String): Unit = {

    //validate user_id and card_id
    var user_id = WalletUtil.convertStringIdtoInt(user_idS)
    var user = WalletDao.getObject(user_id.toString)
    if (user == null) throw new Exception("User Not Found")
    /*if (!WalletUtil.validateInteger(user_id)) return*/

    var ba_id = WalletUtil.convertStringIdtoInt(ba_idS)
    if (!WalletUtil.validateInteger(ba_id)) return

    //delete particular idcard
    if (user.bankaccounts.containsKey(ba_id.toString)) user.bankaccounts.remove(ba_id.toString)

    WalletDao.removeObject(user.getUser_id)
    WalletDao.saveObject(user)

  }

}