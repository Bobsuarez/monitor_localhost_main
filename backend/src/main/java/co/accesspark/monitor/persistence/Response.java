package co.accesspark.monitor.persistence;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.spiwer.standard.template.IGenericMessage;
import com.spiwer.standard.lasting.EMessageStandard;

import java.util.List;

/**
 *
 * @author AccessSoftware2
 * @param <T>
 */
public class Response<T> {

  private int codigo;
  private String mensaje;
  private T datos;

  public Response()
  {
    this(EMessageStandard.OK);
  }

  public Response(IGenericMessage iMensaje)
  {
    setRespuesta(iMensaje);
  }

  private void setRespuesta(IGenericMessage iMensaje)
  {
    this.codigo = iMensaje.getCode();
    this.mensaje = iMensaje.getMessage();
  }

  public Response(int codigo, String mensaje)
  {
    this.codigo = codigo;
    this.mensaje = mensaje;
  }
  public int getCodigo()
  {
    return codigo;
  }

  public Response<T> setCodigo(int code)
  {
    this.codigo = code;
    return this;
  }

  public String getMensaje()
  {
    return mensaje;
  }

  public Response<T> setMensaje(String mensaje)
  {
    this.mensaje = mensaje;
    return this;
  }

  public T getDatos()
  {
    return datos;
  }

  public Response<T> setDatos(T datos)
  {
    this.datos = datos;
    if (datos instanceof List) {
      validarLista((List) datos);
    }
    return this;
  }

  private void validarLista(List datos)
  {
    if (datos == null || datos.isEmpty()) {
      setRespuesta(EMessageStandard.OK);
      this.datos = null;
    }
  }

}
