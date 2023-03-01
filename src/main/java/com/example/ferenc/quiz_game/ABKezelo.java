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
            throw new SQLException("Sikertelen csaatlakozás! "+e.getMessage());
        }

        return kerdesek;
    }
}
