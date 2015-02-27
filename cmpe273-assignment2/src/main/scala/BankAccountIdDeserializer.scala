package main.scala;

import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.core.JsonParser

class BankAccountIdDeserializer extends JsonDeserializer[Int]{

  @Override
  def deserialize(jp:JsonParser, ctxt:DeserializationContext):Int={
    
   var pos = jp.getText().indexOf("-")
   var id :Int = Integer.parseInt(jp.getText().substring(pos+1).trim())
   return id
    
  }
  
}