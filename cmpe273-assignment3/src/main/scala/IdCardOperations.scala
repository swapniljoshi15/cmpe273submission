package main.scala

import java.util.ArrayList
import java.text.SimpleDateFormat
import java.util.Date
import java.util.HashMap

object IdCardOperations {

  def createIdCard(user_idS: String, idcard: IDCard): IDCard = {

    //validate userid
    var user_id = WalletUtil.convertStringIdtoInt(user_idS)
    if (!WalletUtil.validateInteger(user_id)) return null

    
    //validate ID card
    if (!WalletUtil.validateString(idcard.getCard_name)) return null
    if (!WalletUtil.validateString(idcard.getCard_number)) return null
    //if (!WalletUtil.validateDate(idcard.getExpiration_date, "dd-MM-yyyy")) return null
    
    //date conversion
    var formatter = new SimpleDateFormat("MM-dd-yyyy");
    if(idcard.getExpiration_date != null){
      var dateString = formatter.format(idcard.getExpiration_date)
      idcard.setExpiration_date(formatter.parse(dateString))
    }

    
    //fetch user
    var user = WalletLoader.map.get(user_id.toString)

    if (user == null) return null
    
    //generate random id for id card no
    var randomNumber = 0
    do {
      randomNumber = scala.util.Random.nextInt(10000)
    } while (WalletUtil.idCardExists(randomNumber, user) || randomNumber < 0);

    //add id card
    idcard.setCard_id(randomNumber)
    user.idcards.put(randomNumber, idcard)

    return idcard
  }

  def getIdCardList(user_idS: String): ArrayList[IDCard] = {

    //validate userid
    var user_id = WalletUtil.convertStringIdtoInt(user_idS)
    if (!WalletUtil.validateInteger(user_id)) return null

    //fetch user
    var user = WalletLoader.map.get(user_id.toString)

    if (user == null) return null

   return WalletUtil.mapToArrayListIdCards(user.idcards)
   //return WalletUtil.mapToArrayIdCards(user.idcards)
  }

  def deleteIdCard(user_idS: String, card_idS: String): Unit = {

    //validate user_id and card_id
    var user_id = WalletUtil.convertStringIdtoInt(user_idS)
    if (!WalletUtil.validateInteger(user_id)) return 
     var card_id = WalletUtil.convertStringIdtoInt(card_idS)
    if (!WalletUtil.validateInteger(card_id)) return 

    //fetch user
    var user = WalletLoader.map.get(user_id.toString)

    if (user == null) return

    //delete particular idcard
    if(user.idcards.containsKey(card_id)) user.idcards.remove(card_id)

  }

}