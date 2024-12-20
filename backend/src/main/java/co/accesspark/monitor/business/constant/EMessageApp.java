/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.accesspark.monitor.business.constant;

import com.spiwer.standard.template.IGenericMessage;

/**
 *
 * @author AccessSoftware2
 */
public enum EMessageApp implements IGenericMessage {
    
    
    OK(1,"OK"),
    ADD_PROPERTY(2,"Se agrego correctamente la property"),
    VIEW_CONNECTION(3,"__COMPLEMENT__"),
    
    

    ERROR_FOUND(-1000, "No se encontrarón resultados"),
    ERROR_PROPERTY(-1001, "Error en las propiedades de conexión"),
    ERROR_PROPERTY_JSON(-1002, "Error, ocurrio al parsear JSON las propiedades"),
    ERROR_CONECCTION_ROSILLA(-1003, "Error, no existe la conexión con el nombre __COMPLEMENT__"),
    ERROR_FOUND_LEVEL(-1004, "Error, no hay información de niveles para la estación  __COMPLEMENT__"),
    ERROR_INSERT_USER(-1005, "Error, no se pudo insertar el usuario  __COMPLEMENT__"),;
    

    private final int code;
    private final String message;

    private EMessageApp(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
