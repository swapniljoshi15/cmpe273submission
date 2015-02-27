package main.scala;

import org.springframework.data.mongodb.repository.MongoRepository

trait WalletDao[T]{
  
  def saveObject(obj:T):Unit
  
  def getObject(id:String):T
  
  def getAllObjects():java.util.List[T]
  
  def removeObject(id:String):Unit

}