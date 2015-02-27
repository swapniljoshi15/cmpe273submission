package main.scala

import org.springframework.boot._
import org.springframework.boot.autoconfigure._
import org.springframework.stereotype._
import org.springframework.web.bind.annotation._
import org.springframework.context.annotation.ComponentScan
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.context.annotation.Configuration
import java.util.Calendar
import java.util.Date
import scala.Equals
import java.util.ArrayList
import java.util.HashMap
import javax.validation.Valid
import org.springframework.http.HttpStatus
import javax.servlet.annotation.WebFilter
import org.springframework.boot.context.embedded.FilterRegistrationBean
import org.springframework.web.filter.ShallowEtagHeaderFilter
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.view.json.MappingJacksonJsonView
import com.fasterxml.jackson.databind.ObjectMapper
import com.justinsb.etcd.EtcdClient
import java.net.URI
import com.justinsb.etcd.EtcdResult

@Configuration
@EnableAutoConfiguration
@ComponentScan
@RestController
@RequestMapping(Array("/api/v1/*"))
class WalletApplication {

  //var etcdClient = new EtcdClient(URI.create("http://54.183.225.139:4001/"))
  var etcdClient = new EtcdClient(URI.create("http://54.67.30.24:4001/"))
  var key = "/010042695/counter"
  
  @Bean
  def shallowEtagHeaderFilter(): FilterRegistrationBean = {

    var shallowEtagHeaderFilter = new ShallowEtagHeaderFilter()
    var etagBean = new FilterRegistrationBean()
    etagBean.setFilter(shallowEtagHeaderFilter)
    var urlPatterns = new ArrayList[String]()
    urlPatterns.add("/api/v1/users/*")
    etagBean.setUrlPatterns(urlPatterns)

    return etagBean
  }

  /*@Bean
  def mappingJacksonJsonView(): MappingJacksonJsonView = {

    var beanView = new MappingJacksonJsonView
    beanView.setExtractValueFromSingleKeyModel(true)
    
    return beanView
  }*/
  
  //get counter count
  @ResponseStatus(HttpStatus.OK) 
  @RequestMapping(value = Array("/counter"), method = Array(RequestMethod.GET))
  @ResponseBody
  def getCounter(): String = {
    WalletUtil.updateEtcdCounter(key, etcdClient)
    var result:EtcdResult = etcdClient.get(key)
    if(result == null) return ""
    else return result.node.value
  }
  
  //user operations
  @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(value = Array("/users"), method = Array(RequestMethod.POST))
  @ResponseBody
  def createUser(@Valid @RequestBody user: User): User = {
    return UserOperations.createUser(user)
  }

  @ResponseStatus(HttpStatus.OK) //@WebFilter(filterName = "etagFilter", value=Array("/users/{user_id}"), servletNames=Array("org.springframework.web.filter.ShallowEtagHeaderFilter"))
  @RequestMapping(value = Array("/users/{user_id}"), method = Array(RequestMethod.GET))
  @ResponseBody
  def getUser(@PathVariable user_id: String): User = {
    //WalletUtil.updateEtcdCounter(key, etcdClient)
    return UserOperations.getUser(user_id)
  }

  @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(value = Array("/users/{user_id}"), method = Array(RequestMethod.PUT))
  @ResponseBody
  def updateUser(@PathVariable user_id: String,@Valid @RequestBody user: User): User = {
    return UserOperations.updateUser(user_id, user)
  }

  ///id card operations
  @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(value = Array("/users/{user_id}/idcards"), method = Array(RequestMethod.POST))
  @ResponseBody
  def createIdCard(@PathVariable user_id: String, @Valid @RequestBody idcard: IDCard): IDCard = {
    return IdCardOperations.createIdCard(user_id, idcard)
  }

  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(value = Array(" /users/{user_id}/idcards"), method = Array(RequestMethod.GET))
  @ResponseBody
  def getIdCardList(@PathVariable user_id: String): ArrayList[IDCard] = {
    /*var list = IdCardOperations.getIdCardList(user_id)
    var mapper = new ObjectMapper()
    return mapper.writeValueAsString(list)*/
    //WalletUtil.updateEtcdCounter(key, etcdClient)
    return IdCardOperations.getIdCardList(user_id)
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @RequestMapping(value = Array(" /users/{user_id}/idcards/{card_id}"), method = Array(RequestMethod.DELETE))
  @ResponseBody
  def deleteIdCard(@PathVariable user_id: String, @PathVariable card_id: String): Unit = {
    return IdCardOperations.deleteIdCard(user_id, card_id)
  }

  ///Web Login operations
  @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(value = Array("/users/{user_id}/weblogins"), method = Array(RequestMethod.POST))
  @ResponseBody
  def createWebLogin(@PathVariable user_id: String, @Valid @RequestBody weblogin: WebLogin): WebLogin = {
    return WebLoginOperations.createWebLogin(user_id, weblogin)
  }

  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(value = Array("/users/{user_id}/weblogins"), method = Array(RequestMethod.GET))
  @ResponseBody
  def getWebLoginList(@PathVariable user_id: String): ArrayList[WebLogin] = {
    //WalletUtil.updateEtcdCounter(key, etcdClient)
    return WebLoginOperations.getWebLoginList(user_id)
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @RequestMapping(value = Array(" /users/{user_id}/weblogins/{login_id}"), method = Array(RequestMethod.DELETE))
  @ResponseBody
  def deleteWebLogin(@PathVariable user_id: String, @PathVariable login_id: String): Unit = {
    return WebLoginOperations.deleteWebLogin(user_id, login_id)
  }

  ///Bank Account operations
  @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(value = Array("/users/{user_id}/bankaccounts"), method = Array(RequestMethod.POST))
  @ResponseBody
  def createBankAccount(@PathVariable user_id: String, @Valid @RequestBody bank_account: BankAccount): BankAccount = {
    return BankAccountOperations.createBankAccount(user_id, bank_account)
  }

  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(value = Array("/users/{user_id}/bankaccounts"), method = Array(RequestMethod.GET))
  @ResponseBody
  def getBankAccounts(@PathVariable user_id: String): ArrayList[BankAccount] = {
    return BankAccountOperations.getBankAccounts(user_id)
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @RequestMapping(value = Array("/users/{user_id}/bankaccounts/{ba_id}"), method = Array(RequestMethod.DELETE))
  @ResponseBody
  def deleteBankAccount(@PathVariable user_id: String, @PathVariable ba_id: String): Unit = {
    return BankAccountOperations.deleteBankAccount(user_id, ba_id)
  }

}