package com.example.ferenc.quiz_game;

import com.mysql.jdbc.Connection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ABKezelo {

    private static Connection conn;
    private static PreparedStatement stm;

    public ABKezelo(String connectionURL, String felhasznalo, String jelszo) throws SQLException {

        try {

            conn = (Connection) DriverManager.getConnection(connectionURL, felhasznalo, jelszo);

        } catch (Exception e) {
            throw new SQLException("Sikertelen csatlakozás! "+e.getMessage());
        }

    }

    public void KapcsolatBontasa() throws SQLException {
        try {
            conn.close();
        } catch (Exception e) {
            throw new SQLException("Sikertelen lecsatlakozás! "+e.getMessage());
        }
    }

    public List<Kerdes> AdatBetoltes() throws SQLException {

        List<Kerdes> kerdesek = new ArrayList<Kerdes>();

        try {

            stm = conn.prepareStatement("Select * from kerdes");

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                Kerdes kerdes = new Kerdes(rs.getInt("ID"), rs.getString("kerdes"), rs.getString("valasz_a"), rs.getString("valasz_b"), rs.getString("valasz_c"), rs.getString("valasz_d"), rs.getString("helyes_valasz"));
                kerdesek.add(kerdes);
            }

        } catch (Exception e) {
            throw new SQLException("Sikertelen csatlakozás! "+e.getMessage());
        }

        return kerdesek;
    }

    public void AdatFelvitel(Kerdes kerdes) throws SQLException {

        try {
            stm = conn.prepareStatement("Insert into kerdes (kerdes, valasz_a, valasz_b, valasz_c, valasz_d, helyes_valasz) values (?, ?, ?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, kerdes.getKerdes());
            stm.setString(2, kerdes.getValasz_A());
            stm.setString(3, kerdes.getValasz_B());
            stm.setString(4, kerdes.getValasz_C());
            stm.setString(5, kerdes.getValasz_D());
            stm.setString(6, kerdes.getHelyesValasz());

            stm.executeUpdate();

            ResultSet rs = stm.getGeneratedKeys();
            if(rs.next()){
                kerdes.setId(rs.getInt(1));
            }

            rs.close();
            stm.clearParameters();


        } catch (Exception e) {
            throw new SQLException("Az adatok felvitele sikertelen volt! "+e.getMessage());
        }

    }

    public void AdatModositas(Kerdes kerdes) throws SQLException {

        try {
            stm = conn.prepareStatement("Update kerdes set kerdes=?, valasz_a=?, valasz_b=?, valasz_c=?, valasz_d=?, helyes_valasz=? where id=?");
            stm.setString(1, kerdes.getKerdes());
            stm.setString(2, kerdes.getValasz_A());
            stm.setString(3, kerdes.getValasz_B());
            stm.setString(4, kerdes.getValasz_C());
            stm.setString(5, kerdes.getValasz_D());
            stm.setString(6, kerdes.getHelyesValasz());
            stm.setInt(7, kerdes.getId());

            stm.executeUpdate();

            stm.clearParameters();


        } catch (Exception e) {
            throw new SQLException("Az adatok módosítása sikertelen volt! "+e.getMessage());
        }

    }

    public void AdatTorles(Kerdes kerdes) throws SQLException {

        try {
            stm = conn.prepareStatement("Delete from kerdes where ID=?");
            stm.setInt(1, kerdes.getId());

            stm.executeUpdate();
            stm.clearParameters();

        } catch (SQLException e) {
            throw new SQLException("Az adatok törlése sikertelen volt! "+e.getMessage());
        }

    }

}
