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

    public void create(Album album) {
        Connection connection;
        Statement statement = null;

        try {
            connection = Database.getConnection();
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS artists (id int primary key unique auto_increment, name varchar(100) not null, country varchar(100))");
            statement.execute("CREATE TABLE IF NOT EXISTS albums "
                    + "(id int primary key unique auto_increment,"
                    + " name varchar(100) not null,"
                    + " artist_id int not null references artists on delete restrict,"
                    + " release_year int)");
            PreparedStatement posted = connection.prepareStatement("insert into albums(name,artist_id,release_year) values('" + album.getName() + "'," + album.getArtist_id() + "," + album.getRelease() + ");");
            posted.executeUpdate();
            //System.out.println("insert works");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

    }

    public void findByArtist(int artistId) throws SQLException {
        try {
            Connection connection = Database.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from albums where artist_id='" + artistId + "';");
            //System.out.println("select works");
            while (result.next()) {
                System.out.print(result.getString("id") + " " + result.getString("name")+ " "+ result.getString("artist_id") + " "+ result.getString("release_year") + "\n");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
