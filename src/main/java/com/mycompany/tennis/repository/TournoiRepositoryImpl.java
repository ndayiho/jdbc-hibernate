package com.mycompany.tennis.repository;

import com.mycompany.tennis.DataSourceProvider;
import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.entity.Tournoi;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TournoiRepositoryImpl {

    public void create(Tournoi tournoi) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.persist(tournoi);
    }


    public Tournoi findById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.get(Tournoi.class, id);
    }


    public List<Tournoi> findAll() {
        Connection conn = null;
        List<Tournoi> liste = new ArrayList<>();
        try {
            BasicDataSource dataSource = DataSourceProvider.getSingletonDataSource();
            conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("select id,nom, code from tournoi");
            preparedStatement.executeQuery();
            ResultSet rs = preparedStatement.getResultSet();
            while (rs.next()) {
                Tournoi tournoi = new Tournoi();
                tournoi.setId(rs.getLong("ID"));
                tournoi.setNom(rs.getString("NOM"));
                tournoi.setCode(rs.getString("CODE"));
                liste.add(tournoi);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return liste;
    }

    public void delete(Long id) {
        final Tournoi tournoi = findById(id);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.delete(tournoi);
    }
}
