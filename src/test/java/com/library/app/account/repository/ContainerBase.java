package com.library.app.account.repository;

import org.testcontainers.containers.MySQLContainer;

public class ContainerBase {
    
    private static final MySQLContainer MY_SQL_CONTAINER;
    
    static {
        MY_SQL_CONTAINER = new MySQLContainer("mysql:latest");
        
        MY_SQL_CONTAINER.start();
    }
}
