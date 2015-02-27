package main.scala

import java.util.HashMap
import java.util.ArrayList

object BankAccountOperations {

  def createBankAccount(user_idS: String, bank_account: BankAccount): BankAccount = {

    //validate userid
    var user_id = WalletUtil.convertStringIdtoInt(user_idS)
    if (!WalletUtil.validateInteger(user_id)) return null

    //validate Bank Account
    if (!WalletUtil.validateString(bank_account.getAccount_number)) return null
    if (!WalletUtil.validateString(bank_account.getRouting_number)) return null

    //fetch user
    var user = WalletLoader.map.get(user_id.toString)

    if (user == null) return null

    //generate random id for id card no
    var randomNumber = 0
    do {
      randomNumber = scala.util.Random.nextInt(10000)
    } while (WalletUtil.bankAccountExists(randomNumber, user) || randomNumber < 0);

    //add id card
    bank_account.setBa_id(randomNumber)
    user.bankaccounts.put(randomNumber, bank_account)

    return bank_account
  }

  def getBankAccounts(user_idS: String): ArrayList[BankAccount] = {

    //validate userid
    var user_id = WalletUtil.convertStringIdtoInt(user_idS)
    if (!WalletUtil.validateInteger(user_id)) return null

    //fetch user
    var user = WalletLoader.map.get(user_id.toString)

    if (user == null) return null

    return WalletUtil.mapToArrayListBankAccounts(user.bankaccounts)
  }

  def deleteBankAccount(user_idS: String, ba_idS: String): Unit = {

    //validate user_id and card_id
    var user_id = WalletUtil.convertStringIdtoInt(user_idS)
    if (!WalletUtil.validateInteger(user_id)) return

    var ba_id = WalletUtil.convertStringIdtoInt(ba_idS)
    if (!WalletUtil.validateInteger(ba_id)) return

    //fetch user
    var user = WalletLoader.map.get(user_id.toString)

    if (user == null) return

    //delete particular idcard
    if (user.bankaccounts.containsKey(ba_id)) user.bankaccounts.remove(ba_id)

  }

}