/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bmg.entities;

/**
 *
 * @author Haroun
 */
public class User {

    protected int id_u;
    protected String nom;
    protected String prenom;
    protected String email;
    protected String login;
    protected String password;
    protected String role;
    protected String imageurl;
    protected String sexe;
    protected String daten;
    protected String numtel;

    public User() {
    }
    public User(int id_u, String nom, String prenom){
        this.id_u=id_u;
        this.nom=nom;
        this.prenom=prenom;
    }

        public User(int id_u, String nom, String prenom, String email, String login, String password) {
        this.id_u = id_u;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.login = login;
        this.password = password;
    }
        
    public User(int id_u, String nom, String prenom, String email, String login, String password, String role,String imageurl,String sexe,String daten, String numtel ) {
        this.id_u = id_u;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.login = login;
        this.password = password;
        this.imageurl= imageurl;
        this.role = role;
        this.sexe= sexe;
        this.daten=daten;
        this.numtel=numtel;
    }

    public String getNumtel() {
        return numtel;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }

   

    public String getSexe() {
        return sexe;
    }

    public String getDaten() {
        return daten;
    }

    public void setDaten(String daten) {
        this.daten = daten;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public int getId_u() {
        return id_u;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setId_u(int id_u) {
        this.id_u = id_u;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" + "id_u=" + id_u + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", login=" + login + ", password=" + password + ", role=" + role + '}';
    }
}
