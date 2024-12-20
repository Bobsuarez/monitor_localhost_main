/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.accesspark.monitor.business.constant;

/**
 *
 * @author AccessSoftware2
 */
public class ERoute {

    public final static class MainInitial {

        public static final String READ_PROPERTY = "/api/property/read";
    }

    public final static class User {

        public static final String CREATE_USER = "/api/user/createuser";
        public static final String SEARCH_USER = "/api/user/searchuser";
        public static final String DELETE_USER = "/api/user/deleteuser";
        public static final String PROFILE_USER = "/api/user/profileuser";
    }

    public final static class Product {

        public static final String SEARCH_TYPE_PRODUCT = "/api/product/searchTypeProduct";
    }

}
