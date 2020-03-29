package com.example.androidavecsqlite;

public class Client {
    public int id_Cl;
    public String Nom;
    public String Ville;
    public Client(int id_Cl, String nom, String ville) {
        this.id_Cl = id_Cl;
        Nom = nom;
        Ville = ville;
    }
    public Client() {
        this.id_Cl = 0;
        Nom = "";
        Ville = "";
    }
    public int getId_Cl() {
        return id_Cl;
    }
    public void setId_Cl(int id_Cl) {
        this.id_Cl = id_Cl;
    }
    public String getNom() {
        return Nom;
    }
    public void setNom(String nom) {
        Nom = nom;
    }
    public String getVille() {
        return Ville;
    }
    public void setVille(String ville) {
        Ville = ville;
    }
}