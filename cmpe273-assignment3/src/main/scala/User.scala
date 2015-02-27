package main.scala;

import java.lang.String
import org.joda.time.DateTime
import java.util.HashMap
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.hibernate.validator.constraints.NotEmpty
import org.hibernate.validator.constraints.Email
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
class User(c_userid:Int,c_email:String,c_password:String,c_name:String,c_createdate:DateTime,c_updatedate:DateTime) {

    private var user_id:Int = c_userid
    
    @NotEmpty
    @Email
	private var email:String = c_email
	
	@NotEmpty
	private var password:String = c_password
	
	private var name:String = c_name
	private var created_at:DateTime = c_createdate
	private var updated_at:DateTime = c_updatedate
	
    var idcards = new HashMap[Int,IDCard]()
	//var IdCardMap = new IdCardMap
	
    var weblogins = new HashMap[Int,WebLogin]()
    
    var bankaccounts = new HashMap[Int,BankAccount]()
    
    def this()={
      this(0,null,null,null,null,null);
    }
  
    @JsonSerialize(using = classOf[UserIdSerializer])
	def getUser_id = user_id
	
	def setUser_id(newUser_id: Integer) = user_id = newUser_id
	
	def getEmail = email
	
	def setEmail(newEmail: String) = email = newEmail
	
	def getPassword = password
	
	def setPassword(newPassword: String) = password = newPassword
	
	def getName = name
	
	def setName(newName: String) = name = newName
	
	@JsonSerialize(using = classOf[DateSerializer])
	def getCreated_at = created_at
	
	def setCreated_at(newCreated_at: DateTime) = created_at = newCreated_at
	
	@JsonSerialize(using = classOf[DateSerializer])
	def getUpdated_at = updated_at
	
	def setUpdated_at(newUpdated_at: DateTime) = updated_at = newUpdated_at
	
}