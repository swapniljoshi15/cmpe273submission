package main.scala;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer
import org.springframework.http.MediaType
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@Configuration
@EnableWebMvc
class WebConfig extends WebMvcConfigurerAdapter{

  
  override def configureContentNegotiation(configure:ContentNegotiationConfigurer):Unit={
    configure.defaultContentType(MediaType.APPLICATION_JSON);
  }
  
  
}