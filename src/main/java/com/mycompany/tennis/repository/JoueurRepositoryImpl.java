package com.mycompany.tennis.repository;

import com.mycompany.tennis.DataSourceProvider;
import com.mycompany.tennis.entity.Joueur;
import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JoueurRepositoryImpl {

    public void create(Joueur joueur){
        Connection conn = null;
        try {
            BasicDataSource dataSource = DataSourceProvider.getSingletonDataSource();
            conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("insert into joueur(NOM,PRENOM,SEXE) values (?,?,?)");
            preparedStatement.setString(1, joueur.getNom());
            preparedStatement.setString(2, joueur.getPrenom());
            preparedStatement.setString(3, joueur.getSexe().toString());
            preparedStatement.executeUpdate();
            System.out.println("user created");
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

    public void update(Joueur joueur){
        Connection conn = null;
        try {
            BasicDataSource dataSource = DataSourceProvider.getSingletonDataSource();
            conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("update joueur set nom=?,prenom=?,sexe=? where id=?");
            preparedStatement.setString(1, joueur.getNom());
            preparedStatement.setString(2, joueur.getPrenom());
            preparedStatement.setString(3, joueur.getSexe().toString());
            preparedStatement.setLong(4, joueur.getId());
            preparedStatement.executeUpdate();
            System.out.println(" updated");
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


    public void delete(Long id){
        Connection conn = null;
        try {
            BasicDataSource dataSource = DataSourceProvider.getSingletonDataSource();
            conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("delete from joueur where id=?");
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            System.out.println("user deleted");
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

    public Joueur findById(Long id){
        Connection conn = null;
        Joueur joueur=new Joueur();
        try {
            BasicDataSource dataSource = DataSourceProvider.getSingletonDataSource();
            conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("select nom, prenom, sexe from joueur where id=?");
            preparedStatement.setLong(1,id);
            preparedStatement.executeQuery();
            ResultSet rs=preparedStatement.getResultSet();
            if(rs.next()){
                joueur.setId(id);
                joueur.setNom(rs.getString("NOM"));
                joueur.setPrenom(rs.getString("PRENOM"));
                joueur.setSexe(rs.getString("SEXE").charAt(0));
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
        return joueur;
    }

    public List<Joueur> findAll(){
        Connection conn = null;
        List<Joueur> joueurs=new ArrayList<>();
        try {
            BasicDataSource dataSource = DataSourceProvider.getSingletonDataSource();
            conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("select id, nom, prenom, sexe from joueur");
            preparedStatement.executeQuery();
            ResultSet rs=preparedStatement.getResultSet();
            while (rs.next()) {
                Joueur joueur =new Joueur();
                joueur.setId(rs.getLong("ID"));
                joueur.setNom(rs.getString("NOM"));
                joueur.setPrenom(rs.getString("PRENOM"));
                joueur.setSexe(rs.getString("SEXE").charAt(0));
                joueurs.add(joueur);
            }
            System.out.println("on a "+joueurs.size());

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
        return joueurs;
    }
}
