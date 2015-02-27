package main.scala

import org.springframework.web.bind.annotation.ResponseStatus

object UserOperations {

  def createUser(user: User): User = {
    //randomly generate user id
    var randomNumber = 0
    do {
      randomNumber = scala.util.Random.nextInt(10000)
    } while (WalletUtil.useridExists(randomNumber) || randomNumber < 0);
    user.setUser_id(randomNumber.toInt)
    //validate email id and password
    if (!WalletUtil.validateString(user.getEmail)) return null
    if (!WalletUtil.validateString(user.getPassword)) return null
    if (user.getName == null) user.setName("")
    //check email id si already exists or not
    if (WalletUtil.emailExists(user.getEmail)) return null
    //create new user
    var newuser = new User(user.getUser_id, user.getEmail.toLowerCase(), user.getPassword, user.getName, WalletUtil.currentDate, null)
    //add useer to map
    WalletLoader.map.put(newuser.getUser_id.toString, newuser)
    //add email id to map
    WalletLoader.loginMap.put(user.getEmail, newuser)
    //return user
    return newuser
  }

  def getUser(user_idS: String): User = {
    //validate userid
    var user_id = WalletUtil.convertStringIdtoInt(user_idS)
    if (!WalletUtil.validateInteger(user_id)) return null
    //check user exist for user_id
    if (!WalletUtil.useridExists(user_id)) return null
    //fetch user
    return WalletLoader.map.get(user_id.toString)
  }

  def updateUser(user_idS: String, user: User): User = {
    //validate userid
    var user_id = WalletUtil.convertStringIdtoInt(user_idS)
    if (!WalletUtil.validateInteger(user_id)) return null
    //check user exist for user_id
    if (!WalletUtil.useridExists(user_id)) return null
    //fetch user
    var exist_user = WalletLoader.map.get(user_id.toString)
    //validate request 
    //validate email id and password
    /*if (!WalletUtil.validateString(user.getEmail)) return null
    if (!WalletUtil.validateString(user.getPassword)) return null*/
    //update email map
    WalletLoader.loginMap.remove(exist_user.getEmail)
    //update user
    if(user.getEmail != null)
    	exist_user.setEmail(user.getEmail)
    if(user.getPassword != null)
    	exist_user.setPassword(user.getPassword)
    exist_user.setUpdated_at(WalletUtil.currentDate)
    if(user.getName != null)
    	exist_user.setName(user.getName)
    WalletLoader.loginMap.put(user.getEmail, exist_user)
    return exist_user
  }

}