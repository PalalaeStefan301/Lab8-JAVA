/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab8;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Palalae
 */
public class AlbumController {
    public void create(Integer artistID, String name, Integer releaseYear) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement("insert into albums (artistID, name, releaseYear) values (?, ?, ?)")) {
            pstmt.setString(1, String.valueOf(artistID));
            pstmt.setString(2, name);
            pstmt.setString(3, String.valueOf(releaseYear));
            pstmt.executeUpdate();
        }
    }
    public void list(Integer artistID) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.getResultSet()) {
            stmt.executeQuery("select name from albums where id = artistID");
            System.out.println(rs.next() ? rs.getInt(1) : null);
        }
    }
}
