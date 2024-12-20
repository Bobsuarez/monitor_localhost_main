/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.accesspark.monitor.app.config;

import co.accesspark.monitor.business.util.UtilMonitor;
import co.accesspark.monitor.business.util.json.LocalDateTimeDeserializer;
import co.accesspark.monitor.business.util.json.LocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.time.LocalDateTime;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 *
 * @author spiwer
 */
@Configuration
public class ObjectMapperConfig {

  @Bean
  @Primary
  public ObjectMapper config()
  {

    ObjectMapper objectMapper = new ObjectMapper();
    JavaTimeModule timeModule = new JavaTimeModule();
    timeModule
            .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer())
            .addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .registerModule(timeModule);
    return objectMapper;
  }

  @Bean
  public void createFile()
  {
    try {
      String ruta = "configuration.json";
      File file = new File(ruta);
      // Si el archivo no existe es creado
      if (file.exists()) {
        return;
      }
      file.createNewFile();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
