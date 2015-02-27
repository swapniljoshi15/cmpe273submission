package main.scala;

import com.mongodb.MongoClient
import org.springframework.data.mongodb.core.MongoTemplate
import com.mongodb.MongoClientURI
import com.mongodb.MongoCredential
import java.util.Arrays


object MongoDbConnection {

  //var credential =  MongoCredential.createMongoCRCredential("swapnil", "wallet", "swap".toCharArray())
  //val uri = new MongoClientURI("mongodb://ds043200.mongolab.com:43200/wallet", Arrays.asList(credential))
  //val uri = new MongoClientURI("mongodb://swapnil:swap@ds043200.mongolab.com:43200/wallet")
  val uri = new MongoClientURI("mongodb://swapnil:swap1590j@ds047950.mongolab.com:47950/wallet")
  val mongoclient = new MongoClient(uri)
  
  //val databaseConnection = mongoclient.getDB("wallet")
  val mongoConnection = new MongoTemplate(mongoclient, "wallet")
  
}