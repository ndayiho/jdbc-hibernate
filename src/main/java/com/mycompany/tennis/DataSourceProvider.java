package com.mycompany.tennis;

import org.apache.commons.dbcp.BasicDataSource;

public class DataSourceProvider {
   private static BasicDataSource singletonDataSource;

    public static BasicDataSource getSingletonDataSource(){
        if(singletonDataSource==null) {
            singletonDataSource=new BasicDataSource();
            singletonDataSource.setInitialSize(5);
            singletonDataSource.setUrl("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris");
            singletonDataSource.setUsername("root");
            singletonDataSource.setPassword("root");
        }
        return singletonDataSource;
    }

}
