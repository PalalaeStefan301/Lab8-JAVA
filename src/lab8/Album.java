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
public class Album {
    private String name;
    private int id;
    private int release_year;
    private int artist_id;
    public Album(int id,String name,int release_year,int artist_id)
    {
        this.id=id;
        this.release_year= release_year;
        this.name =name;
        this.artist_id = artist_id;
    }
    public String getName()
    {
        return name;
    }
    public int getId()
    {
        return id;
    }
    public int getRelease()
    {
        return release_year;
    }
    public int getArtist_id()
    {
        return artist_id;
    }
}
