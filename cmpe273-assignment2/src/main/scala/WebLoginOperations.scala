package main.scala

import java.util.HashMap
import java.util.ArrayList

object WebLoginOperations {

  var WalletDao = new WalletDaoImpl
  
  def createWebLogin(user_idS: String, weblogin: WebLogin): WebLogin = {
    
    //validate userid
    var user_id = WalletUtil.convertStringIdtoInt(user_idS)
    var user = WalletDao.getObject(user_id.toString)
    if(user == null) throw new Exception("User Not Found")	
   /* if (!WalletUtil.validateInteger(user_id)) return null*/
    
    //validate Web Login
    if (!WalletUtil.validateString(weblogin.getUrl)) return null
    if (!WalletUtil.validateString(weblogin.getPassword)) return null
    if (!WalletUtil.validateString(weblogin.getLogin)) return null
    
    //generate random id for id card no
    var randomNumber = 0
    do {
      randomNumber = scala.util.Random.nextInt(10000)
    } while (WalletUtil.webLoginExists(randomNumber, user) || randomNumber < 0);
    
    //add id card
    weblogin.setLogin_id(randomNumber)
    user.weblogins.put(randomNumber, weblogin)

    WalletDao.removeObject(user.getUser_id)
    WalletDao.saveObject(user)	
    
    return weblogin
  }

  def getWebLoginList(user_idS: String): ArrayList[WebLogin] = {
    
    //validate userid
    var user_id = WalletUtil.convertStringIdtoInt(user_idS)
    var user = WalletDao.getObject(user_id.toString)
    if(user == null) throw new Exception("User Not Found")	
    /*if (!WalletUtil.validateInteger(user_id)) return null*/
    
    return WalletUtil.mapToArrayListWebLogins(user.weblogins)
  }

  def deleteWebLogin(user_idS: String, login_idS: String): Unit = {
    
    //validate user_id and card_id
    var user_id = WalletUtil.convertStringIdtoInt(user_idS)
    var user = WalletDao.getObject(user_id.toString)
    if(user == null) throw new Exception("User Not Found")	
   /* if (!WalletUtil.validateInteger(user_id)) return*/ 
    
    var login_id = WalletUtil.convertStringIdtoInt(login_idS)
    if (!WalletUtil.validateInteger(login_id)) return 

    //delete particular idcard
    if(user.weblogins.containsKey(login_id.toString)) user.weblogins.remove(login_id.toString)
    
    WalletDao.removeObject(user.getUser_id)
    WalletDao.saveObject(user)	
    
  }
  
  
}