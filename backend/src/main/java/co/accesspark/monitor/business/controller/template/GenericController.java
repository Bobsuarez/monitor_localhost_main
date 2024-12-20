/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.accesspark.monitor.business.controller.template;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spiwer.rosilla.exception.JdbcException;
import com.spiwer.standard.dto.Answer;
import com.spiwer.standard.exception.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author AccessSoftware2
 */
public class GenericController
{

  @ExceptionHandler({
    AppException.class,
    JdbcException.class,
    JsonProcessingException.class,
    Exception.class          
  })
  @ResponseStatus(HttpStatus.OK)
  public Answer error(AppException e)
  {
    return new Answer(e.getCode(), e.getMessage());
  }

}
