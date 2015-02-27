package main.scala;

import com.fasterxml.jackson.databind.JsonDeserializer
import java.util.Date
import com.fasterxml.jackson.databind.DeserializationContext
import java.text.SimpleDateFormat
import com.fasterxml.jackson.core.JsonParser

class CustomDateJSONParserDeserialize extends JsonDeserializer[Date]{

  @Override
  def deserialize(jp:JsonParser, ctxt:DeserializationContext):Date={
    
    var formatter = new SimpleDateFormat("MM-dd-yyyy")
    //var date = formatter.format(jp.getText())
    try{
      return formatter.parse(jp.getText())
    }catch{
      case exception: Exception => return null
    }
  }
}
