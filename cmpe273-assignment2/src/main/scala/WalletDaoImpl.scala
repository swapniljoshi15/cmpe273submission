package main.scala

import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Criteria

class WalletDaoImpl extends WalletDao[User] {

  private var mongoTemplate = MongoDbConnection.mongoConnection 
  
  def setMongoTemplate(mongoTemp:MongoTemplate):Unit={
    mongoTemplate = mongoTemp
  }
  
  
  override def saveObject(user:User):Unit={
    mongoTemplate.insert(user)
  }
  
  override def getObject(user_id:String):User={
    mongoTemplate.findOne(new Query((Criteria.where("_id").is(user_id))), classOf[User])
  }
  
  override def getAllObjects():java.util.List[User]={
    mongoTemplate.findAll(classOf[User])
  }
  
  override def removeObject(user_id:String):Unit={
    mongoTemplate.remove(new Query((Criteria.where("_id").is(user_id))), classOf[User])
  }
  
}