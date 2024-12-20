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
public class EGenericQuery {

    public final static class Status {

        public static final String ACTIVE = "A";
        public static final String DELETE = "D";
        public static final String UPDATE = "U";
        public static final String SUSPEND = "S";
        public static final String PENDING = "P";
        public static final String INCOMPLETE = "I";
    }

    public final static class system {

        public static final String NULL = "null";
        public static final String SYSTEM = "S";
        public static final String USER = "U";
    }

    public final static class level {

        public static final String ERROR = "E";
        public static final String DEBUG = "D";
        public static final String WARNING = "W";

    }

    public final static class testQuery {

        public static final String TEST_QUERY = "SELECT NOW() AS current";
    }
}
