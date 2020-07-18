package com.mycompany.tennis.repository;

import com.mycompany.tennis.DataSourceProvider;
import com.mycompany.tennis.entity.Tournoi;
import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TournoiRepositoryImpl {

    public void create(Tournoi tournoi){
        Connection conn = null;
        try {
            BasicDataSource dataSource = DataSourceProvider.getSingletonDataSource();
            conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("insert into tournoi(NOM,CODE) values (?,?)");
            preparedStatement.setString(1, tournoi.getNom());
            preparedStatement.setString(2, tournoi.getCode());
            preparedStatement.executeUpdate();
            System.out.println("Tournoi created");
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


    public Tournoi findById(Long id){
        Connection conn = null;
        Tournoi tournoi=new Tournoi();
        try {
            BasicDataSource dataSource = DataSourceProvider.getSingletonDataSource();
            conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("select nom, code from tournoi where id=?");
            preparedStatement.setLong(1,id);
            preparedStatement.executeQuery();
            ResultSet rs=preparedStatement.getResultSet();
            if(rs.next()){
                tournoi.setId(id);
                tournoi.setNom(rs.getString("NOM"));
                tournoi.setCode(rs.getString("CODE"));
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
        return tournoi;
    }


    public List<Tournoi> findAll(){
        Connection conn = null;
        List<Tournoi>liste=new ArrayList<>();
        try {
            BasicDataSource dataSource = DataSourceProvider.getSingletonDataSource();
            conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("select id,nom, code from tournoi");
            preparedStatement.executeQuery();
            ResultSet rs=preparedStatement.getResultSet();
            while(rs.next()){
                Tournoi tournoi=new Tournoi();
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
}
