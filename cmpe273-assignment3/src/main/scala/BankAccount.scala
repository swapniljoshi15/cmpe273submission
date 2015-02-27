package main.scala;

import java.lang.String
import java.lang.Integer
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import org.hibernate.validator.constraints.NotEmpty
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
class BankAccount(c_ba_id:Integer, c_account_name:String, c_routing_number:String, c_account_number:String) {

    private var ba_id:Integer = c_ba_id
	private var account_name:String = c_account_name
	@NotEmpty
	private var routing_number:String = c_routing_number
	@NotEmpty
	private var account_number:String = c_account_number
  
	def this()={
      this(0,null,null,null);
    }
	
	@JsonSerialize(using = classOf[BankAccountIdSerializer])
	def getBa_id = ba_id
	
	@JsonDeserialize(using = classOf[BankAccountIdDeserializer])
	def setBa_id(newBa_id: Integer) = ba_id = newBa_id
	
	def getAccount_name = account_name
	
	def setAccount_name(newAccount_name: String) = account_name = newAccount_name
	
	def getRouting_number = routing_number
	
	def setRouting_number(newRouting_number: String) = routing_number = newRouting_number
	
	def getAccount_number = account_number
	
	def setAccount_number(newAccount_number: String) = account_number = newAccount_number
	
}