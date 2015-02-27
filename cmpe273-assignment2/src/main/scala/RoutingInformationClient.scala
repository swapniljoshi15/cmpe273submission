package main.scala;

import org.springframework.web.client.RestTemplate
import org.springframework.http.converter.HttpMessageConverter
import java.util.ArrayList
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter
import org.springframework.http.MediaType
import org.codehaus.jackson.map.ObjectMapper
import org.codehaus.jackson.map.DeserializationConfig
import org.springframework.web.client.HttpClientErrorException

class RoutingInformationClient {
  
  val restEndpoint = "http://www.routingnumbers.info/api/data.json?rn="

  def getRoutingInformation(routing_number:String):RoutingInformation={
    
    val restTemplate = new RestTemplate()
    
    var supportedMediaTypes = new ArrayList[MediaType]()
    supportedMediaTypes.add(new MediaType("text","plain"))
    supportedMediaTypes.add(new MediaType("application","json"))
    
    var objectMapper = new ObjectMapper()
    objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    
    val convertor = new ArrayList[HttpMessageConverter[_]]()
    var jacsonMessageConvertor = new MappingJacksonHttpMessageConverter()
    jacsonMessageConvertor.setSupportedMediaTypes(supportedMediaTypes)
    jacsonMessageConvertor.setObjectMapper(objectMapper)
    convertor.add(jacsonMessageConvertor)
    restTemplate.setMessageConverters(convertor)
    var routingInformation:RoutingInformation = null
    try{
    	routingInformation = restTemplate.getForObject(restEndpoint+routing_number, classOf[RoutingInformation])
    }catch{
      case ex:HttpClientErrorException => if( ex.getStatusCode() == 404) throw ex
    }
    return routingInformation
  }
  
}