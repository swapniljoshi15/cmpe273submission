package main.scala

import org.springframework.boot._
import org.springframework.boot.autoconfigure._
import org.springframework.stereotype._
import org.springframework.web.bind.annotation._
import java.util.HashMap
import java.util.Map
import scala.collection.JavaConversions._
import org.springframework.boot.context.embedded.FilterRegistrationBean
import org.springframework.web.filter.ShallowEtagHeaderFilter
import java.util.ArrayList
import org.springframework.context.annotation.Bean

object WalletLoader {

  //user id and user map
  var map = new HashMap[String, User]()
  
  //email and User map ..if require for login
  var loginMap = new HashMap[String, User]()
  
  
  def main(args: Array[String]) {

    map = new HashMap[String, User]()
    loginMap = new HashMap[String, User]()
    
    SpringApplication.run(classOf[WalletApplication])

  }
  

}
