/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bmg.entities;

/**
 *
 * @author Daly
 */
public class ImageProp {
    private int id;
    private String url;
    Propriete propriete;

    public ImageProp() {
    }

    public ImageProp(int id, String url, Propriete propriete) {
        this.id = id;
        this.url = url;
        this.propriete = propriete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Propriete getPropriete() {
        return propriete;
    }

    public void setPropriete(Propriete propriete) {
        this.propriete = propriete;
    }

    @Override
    public String toString() {
        return "Image{" + "id=" + id + ", url=" + url + ", propriete=" + propriete.getId() + '}';
    }
    

   
    
    
}
