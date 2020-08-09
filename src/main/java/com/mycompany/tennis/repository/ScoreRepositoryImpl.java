package com.mycompany.tennis.repository;

import com.mycompany.tennis.DataSourceProvider;
import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.entity.Joueur;
import com.mycompany.tennis.entity.Score;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.Session;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScoreRepositoryImpl {

    public void create(Score score) {
        Connection conn = null;
        try {
            BasicDataSource dataSource = DataSourceProvider.getSingletonDataSource();
            conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("insert into score_vainqueur(id_match,set_1,set_2,set_3,set_4,set_5) values (?,?,?,?,?,?)");
            preparedStatement.setLong(1,score.getMatch().getId());
            preparedStatement.setByte(2, score.getSet1());
            preparedStatement.setByte(3, score.getSet2());
            if (score.getSet3() == null) {
                preparedStatement.setNull(4, Types.TINYINT);
            } else {
                preparedStatement.setByte(4, score.getSet3());
            }
            if (score.getSet4() == null) {
                preparedStatement.setNull(5, Types.TINYINT);
            } else {
                preparedStatement.setByte(5, score.getSet4());
            }

            if (score.getSet5() == null) {
                preparedStatement.setNull(6, Types.TINYINT);
            } else {
                preparedStatement.setByte(6, score.getSet5());
            }

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                score.setId(rs.getLong(1));
            }

            System.out.println("score created");
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
    }


    public Score findById(Long id) {
        //get session
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.get(Score.class, id);
    }

}
