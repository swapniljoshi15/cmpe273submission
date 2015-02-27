package main.scala;

import java.util.HashMap
import com.fasterxml.jackson.databind.JsonSerializable
import com.fasterxml.jackson.databind.annotation.JsonSerialize

class IdCardMap {

  var idcards = new HashMap[Int,IDCard]()
  
  @JsonSerialize(using = classOf[CustomIDMapSerializer])
  def getIdcards : HashMap[Int,IDCard] = idcards
  
}