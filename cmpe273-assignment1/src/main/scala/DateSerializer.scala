package main.scala;

import org.joda.time.DateTime
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import org.joda.time.format.DateTimeFormatter
import org.joda.time.format.DateTimeFormat

class DateSerializer extends JsonSerializer[DateTime] {

  @Override
  def serialize(value: DateTime, jgen: JsonGenerator, provider: SerializerProvider): Unit = {

    var formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    jgen.writeString(value.toString(formatter))

  }

}