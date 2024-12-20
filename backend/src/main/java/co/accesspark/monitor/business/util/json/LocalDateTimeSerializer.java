/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.accesspark.monitor.business.util.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author
 */
public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime>
{

  private final static String DATE_TIME_FORMATTER = "yyyy-MM-dd HH:mm:ss";

  @Override
  public void serialize(LocalDateTime t, JsonGenerator jg, SerializerProvider sp)
          throws IOException
  {
    jg.writeString(t.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER)));

  }

}
