package main.scala;

import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import java.text.SimpleDateFormat
import java.util.Date
import org.joda.time.DateTime

class CustomDateJSONParser extends JsonSerializer[Date] {

  @Override
  def serialize(value:Date, jgen:JsonGenerator, provider:SerializerProvider):Unit={
    
    var formatter = new SimpleDateFormat("MM-dd-yyyy")
    var date = formatter.format(value)
    jgen.writeString(date)
    
  }
  
}
