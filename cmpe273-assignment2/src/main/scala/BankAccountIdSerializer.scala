package main.scala;

import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider


class BankAccountIdSerializer extends JsonSerializer[Int]{

  @Override
  def serialize(value:Int, jgen:JsonGenerator, provider:SerializerProvider):Unit={
    
    jgen.writeString("b-"+value)
 
  }
}