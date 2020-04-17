/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab8;

/**
 *
 * @author Palalae
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ArtistController {

    public void create(Artist artist) {
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
            PreparedStatement posted = connection.prepareStatement("insert into artists(name,country) values('" + artist.getName() + "','" + artist.getCountry() + "');");
            posted.executeUpdate();
            System.out.println("insert works");
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

    public void findByName(String name) throws SQLException {
        try {
            Connection connection = Database.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from artists where name='" + name + "';");
            //System.out.println("select works");
            while(result.next())
            {
                System.out.print(result.getString("id") + " " + result.getString("name") + " " + result.getString("country") + "\n");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        
    }
}
