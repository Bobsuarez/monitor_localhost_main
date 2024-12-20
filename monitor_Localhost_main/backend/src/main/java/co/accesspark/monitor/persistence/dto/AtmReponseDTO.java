/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.accesspark.monitor.persistence.dto;

/**
 *
 * @author coman
 */
public class AtmReponseDTO implements Comparable<AtmReponseDTO> {

    private String ip;
    private String name;
    private String location;
    private String status;

    public AtmReponseDTO() {
    }

    public AtmReponseDTO(String ip, String name, String location, String status) {
        this.ip = ip;
        this.name = name;
        this.location = location;
        this.status = status;
    }

    public String getIp() {
        return ip;
    }

    public AtmReponseDTO setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public String getName() {
        return name;
    }

    public AtmReponseDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public AtmReponseDTO setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public AtmReponseDTO setStatus(String status) {
        this.status = status;
        return this;
    }

    @Override
    public int compareTo(AtmReponseDTO atmReponseDTO) {
        String a = this.getName();
        String b = atmReponseDTO.getName();
        return a.compareTo(b);
    }

}
