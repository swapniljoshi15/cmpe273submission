package main.scala;

import java.lang.String
import java.lang.Integer
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import org.hibernate.validator.constraints.NotEmpty
import org.hibernate.validator.constraints.URL
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
class WebLogin(c_loginid:Int, c_url:String, c_login:String, c_password:String) {

    private var login_id:Int = c_loginid
    @NotEmpty
    @URL
	private var url:String = c_url
	@NotEmpty
	private var login:String = c_login
	@NotEmpty
	private var password:String = c_password
  
	def this()={
      this(0,null,null,null);
    }
	
	@JsonSerialize(using = classOf[WebLoginSerializer])
	def getLogin_id = login_id
	
	@JsonDeserialize(using = classOf[WebLoginDeserializer])
	def setLogin_id(newLogin_id: Int) = login_id = newLogin_id
	
	def getUrl = url
	
	def setUrl(newUrl: String) = url = newUrl
	
	def getLogin = login
	
	def setLogin(newLogin: String) = login = newLogin
	
	def getPassword = password
	
	def setPassword(newPassword: String) = password = newPassword
	
	
}