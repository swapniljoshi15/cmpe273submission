package main.scala;

import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
class InvalidMethodParameterException(msg:String) extends RuntimeException(msg){

  
}