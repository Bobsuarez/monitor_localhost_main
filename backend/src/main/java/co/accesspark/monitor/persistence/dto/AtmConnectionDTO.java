/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.accesspark.monitor.persistence.dto;

/**
 *
 * @author AccessSoftware2
 */
public class AtmConnectionDTO {

    private Integer id;
    private String name;
    private String ip;
    private String port;
    private String bdname;
    private String user;
    private String password;
    private String location;
    private String status;

    public AtmConnectionDTO(Integer id, String name, String ip, String port, String bdname, String user, String password, String location, String status) {
        this.id = id;
        this.name = name;
        this.ip = ip;
        this.port = port;
        this.bdname = bdname;
        this.user = user;
        this.password = password;
        this.location = location;
        this.status = status;
    }

    public AtmConnectionDTO() {
    }

    public Integer getId() {
        return id;
    }

    public AtmConnectionDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AtmConnectionDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public AtmConnectionDTO setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public String getPort() {
        return port;
    }

    public AtmConnectionDTO setPort(String port) {
        this.port = port;
        return this;
    }

    public String getBdname() {
        return bdname;
    }

    public AtmConnectionDTO setBdname(String bdname) {
        this.bdname = bdname;
        return this;
    }

    public String getUser() {
        return user;
    }

    public AtmConnectionDTO setUser(String user) {
        this.user = user;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AtmConnectionDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public AtmConnectionDTO setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public AtmConnectionDTO setStatus(String status) {
        this.status = status;
        return this;
    }

}
