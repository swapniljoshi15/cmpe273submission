package main.scala;

import java.lang.String
import java.lang.Integer
import java.util.Date
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import org.hibernate.validator.constraints.NotEmpty
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(Include.NON_EMPTY)
class IDCard(c_cardid:Integer, c_cardname:String, c_cardnumber:String, c_expirationdate:Date) {

    private var card_id:Integer = c_cardid
    
    @NotEmpty
	private var card_name:String = c_cardname
	@NotEmpty
	private var card_number:String = c_cardnumber
	private var expiration_date:Date = c_expirationdate
	
	def this()={
      this(0,null,null,null);
    }
  
    @JsonSerialize(using = classOf[IdCardIdSerializer])
	def getCard_id = card_id
	
	def setCard_id(newCard_id: Integer) = card_id = newCard_id
	
	def getCard_name = card_name
	
	def setCard_name(newCard_name: String) = card_name = newCard_name
	
	def getCard_number = card_number
	
	def setCard_number(newCard_number: String) = card_number = newCard_number
	
	@JsonSerialize(using = classOf[CustomDateJSONParser])
	def getExpiration_date = expiration_date
	
	@JsonDeserialize(using = classOf[CustomDateJSONParserDeserialize])
	def setExpiration_date(newExpiration_date: Date) = expiration_date = newExpiration_date
	
}