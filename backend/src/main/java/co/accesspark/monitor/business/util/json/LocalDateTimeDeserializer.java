/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.accesspark.monitor.business.util.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author
 */
public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime>
{

  private final static String DATE_TIME_FORMATTER = "yyyy-MM-dd HH:mm:ss";

  @Override
  public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
          throws IOException, JsonProcessingException
  {

    String date = jsonParser.getValueAsString();
    if (date == null) {
      return null;
    }
    try {
      return LocalDateTime.parse(date, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    } catch (Exception e) {
      return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER));
    }
  }

}
