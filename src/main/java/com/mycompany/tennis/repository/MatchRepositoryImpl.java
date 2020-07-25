package com.mycompany.tennis.repository;

import com.mycompany.tennis.DataSourceProvider;
import com.mycompany.tennis.entity.Match;
import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MatchRepositoryImpl {

    public void create(Match match){
        Connection conn = null;
        try {
            BasicDataSource dataSource = DataSourceProvider.getSingletonDataSource();
            conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("insert into match_tennis(id_epreuve,id_vainqueur,id_finaliste) values (?,?,?)");
            preparedStatement.setLong(1, match.getEpreuve().getId());
            preparedStatement.setLong(2, match.getVainqueur().getId());
            preparedStatement.setLong(3, match.getFinaliste().getId());
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                match.setId(rs.getLong(1));
            }
            System.out.println("Match created");
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
}
