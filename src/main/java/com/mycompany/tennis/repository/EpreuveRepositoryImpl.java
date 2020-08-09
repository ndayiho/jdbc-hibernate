package com.mycompany.tennis.repository;

import com.mycompany.tennis.DataSourceProvider;
import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.entity.Epreuve;
import com.mycompany.tennis.entity.Score;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.Session;

import java.sql.*;

public class EpreuveRepositoryImpl {

    public Epreuve findById(Long id) {
        //get session
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.get(Epreuve.class, id);
    }

}
