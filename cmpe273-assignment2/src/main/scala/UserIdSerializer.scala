package main.scala;

import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider

class UserIdSerializer extends JsonSerializer[String]{

  @Override
  def serialize(value:String, jgen:JsonGenerator, provider:SerializerProvider):Unit={
    
    jgen.writeString("u-"+value)
 
  }
  
}