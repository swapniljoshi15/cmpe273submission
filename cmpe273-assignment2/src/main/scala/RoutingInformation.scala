package main.scala;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class RoutingInformation {

  @JsonProperty("customer_name")
  private var customer_name: String = ""
    
  @JsonProperty("code")
  private var code: String = ""
    
  @JsonProperty("message")
  private var message: String = ""

  def getCustomer_name = customer_name

  def setCustomer_name(req_customer_name: String) = customer_name = req_customer_name

  def getCode = code

  def setCode(res_code: String) = code = res_code
  
  def getMessage = message

  def setMessage(res_message: String) = message = res_message


}