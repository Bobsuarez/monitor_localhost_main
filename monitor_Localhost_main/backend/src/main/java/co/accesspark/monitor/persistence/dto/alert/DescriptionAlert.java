/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.accesspark.monitor.persistence.dto.alert;

import java.util.List;

/**
 *
 * @author Bobsuarez
 */
public class DescriptionAlert {

    private String Location;
    private List<String> error;

    public DescriptionAlert() {
    }

    public DescriptionAlert(String Location, List<String> error) {
        this.Location = Location;
        this.error = error;
    }

    public String getLocation() {
        return Location;
    }

    public DescriptionAlert setLocation(String Location) {
        this.Location = Location;
        return this;
    }

    public List<String> getError() {
        return error;
    }

    public DescriptionAlert setError(List<String> error) {
        this.error = error;
        return this;
    }

}
