/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab8;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Palalae
 */
public class Chart {

    private int id;
    private String name;
    private ArrayList<Album> albums = new ArrayList<>();

    public Chart(String name, int id) {
        this.name = name;
        Connection connection;
        Statement statement = null;
        try {
            connection = Database.getConnection();
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS charts "
                    + "(id int primary key unique auto_increment,"
                    + " id_album int not null references albums on delete restrict,"
                    + " position int not null )");
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

    public void add(int id_album, int position) {

        Connection connection;
        Statement statement = null;
        try {
            connection = Database.getConnection();
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS charts (id int primary key unique auto_increment, id_album int not null references albums on delete restrict, name varchar(100) not null, position int not null )");
            PreparedStatement posted = connection.prepareStatement("insert into charts(name,id_album,position) values('" + name + "'," + id_album + "," + position + ");");
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

    public ArrayList<Album> Get() {
        return albums;
    }
}
