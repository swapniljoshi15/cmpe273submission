package main.scala;

import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import java.text.SimpleDateFormat
import java.util.Date
import org.joda.time.DateTime
import java.util.HashMap
import java.util.Map

class CustomIDMapSerializer extends JsonSerializer[HashMap[Int,IDCard]] {

  @Override
  def serialize(value:HashMap[Int,IDCard], jgen:JsonGenerator, provider:SerializerProvider):Unit={
    
    //code to convert map to given format
    var iterator = value.entrySet().iterator()
    println("1");
    while(iterator.hasNext()){
      println("2");
      var row = iterator.next.asInstanceOf[Map.Entry[Int, IDCard]]
      println("3");
      jgen.writeString("\"card_id\" : \""+row.getValue().getCard_id+"\",")
      jgen.writeString("\"card_name\" : \""+row.getValue().getCard_name+"\",")
      jgen.writeString("\"card_number\" : \""+row.getValue().getCard_number+"\",")
      jgen.writeString("\"expiration_date\" : \""+row.getValue().getExpiration_date+"\"")
       println("4");
    }
      println("5");
  }
  
}

