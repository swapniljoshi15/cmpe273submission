package main.scala

import main.scala.WalletLoader
import java.util.Date
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone
import java.util.Calendar
import org.joda.time.DateTime
import java.text.ParseException
import java.util.HashMap
import scala.reflect.ClassTag
import java.util.ArrayList
import java.util.Map

object WalletUtil {

  def validateString(input: String): Boolean = {

    if (input == null || "".equals(input.trim())) return false

    return true
  }

  def validateInteger(input: Int): Boolean = {

    if (input == null || input < 0) return false

    return input.isValidInt

  }

  def emailExists(email: String): Boolean = {

    if (WalletLoader.loginMap.containsKey(email)) return true

    return false
  }

  def useridExists(userid: Int): Boolean = {

    if (WalletLoader.map.containsKey(userid.toString)) return true

    return false

  }

  def currentDateInString(): String = {

    var today: Date = Calendar.getInstance().getTime()
    var formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
    formatter.setTimeZone(TimeZone.getTimeZone("UTC")) //z for zullu time
    return formatter.format(today)

  }

  def currentDate(): DateTime = {

    return DateTime.now()

  }

  def convertDateToString(date: Date): String = {

    if (date == null) return ""
    var formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
    formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
    return formatter.format(date)

  }

  def validateDate(date: Date, format: String): Boolean = {
    var formatter = new SimpleDateFormat(format);
    if (date == null) return false
    try {
      formatter.format(date.toString())
    } catch {
      case exception: Exception => return false
    }
    return true

  }

  def idCardExists(idcardno: Int, user: User): Boolean = {

    if (user.idcards.containsKey(idcardno)) return true

    return false

  }

  def mapToArrayListForIdCards(map: HashMap[Int, IDCard]): ArrayList[String] = {

    var iterator = map.entrySet().iterator()
    var list = new ArrayList[String]()

    while (iterator.hasNext()) {
      var row = iterator.next.asInstanceOf[Map.Entry[Int, IDCard]]
      list.add("" + row.getKey().toString + ":" + row.getValue().toString())
    }

    return list
  }

  def webLoginExists(login_id: Int, user: User): Boolean = {

    if (user.weblogins.containsKey(login_id)) return true

    return false

  }

  def convertStringIdtoInt(input: String): Int = {

    var pos = input.indexOf("-")
    var id: Int = Integer.parseInt(input.substring(pos+1).trim())
    return id

  }

  def bankAccountExists(ba_id: Int, user: User): Boolean = {

    if (user.bankaccounts.containsKey(ba_id)) return true

    return false

  }
  
  def mapToArrayListIdCards(map: HashMap[Int, IDCard]): ArrayList[IDCard] = {

    var iterator = map.entrySet().iterator()
    var list = new ArrayList[IDCard]()

    while (iterator.hasNext()) {
      var row = iterator.next.asInstanceOf[Map.Entry[Int, IDCard]]
      list.add(row.getValue())
    }

    return list
  }
  
  def mapToArrayIdCards(map: HashMap[Int, IDCard]): Array[IDCard] = {

    var iterator = map.entrySet().iterator()
    //var list = new ArrayList[IDCard]()
    var list = new Array[IDCard](map.size())

    while (iterator.hasNext()) {
      var row = iterator.next.asInstanceOf[Map.Entry[Int, IDCard]]
      list :+ row.getValue()
    }

    return list
  }
  
  def mapToArrayListWebLogins(map: HashMap[Int, WebLogin]): ArrayList[WebLogin] = {

    var iterator = map.entrySet().iterator()
    var list = new ArrayList[WebLogin]()

    while (iterator.hasNext()) {
      var row = iterator.next.asInstanceOf[Map.Entry[Int, WebLogin]]
      list.add(row.getValue())
    }

    return list
  }
  
  def mapToArrayListBankAccounts(map: HashMap[Int, BankAccount]): ArrayList[BankAccount] = {

    var iterator = map.entrySet().iterator()
    var list = new ArrayList[BankAccount]()

    while (iterator.hasNext()) {
      var row = iterator.next.asInstanceOf[Map.Entry[Int, BankAccount]]
      list.add(row.getValue())
    }

    return list
  }
  
}
