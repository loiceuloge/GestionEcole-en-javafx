package dao.models;

import java.time.LocalDate;

public class Groupe {
    private int id;
	private String nom;
	private LocalDate dateDeCreation;

    

    public Groupe() {
    }

    public Groupe(int id, String nom, LocalDate dateDeCreation) {
        this.id = id;
        this.nom = nom;
        this.dateDeCreation = dateDeCreation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDateDeCreation() {
        return dateDeCreation;
    }

    public void setDateDeCreation(LocalDate dateDeCreation) {
        this.dateDeCreation = dateDeCreation;
    }

    @Override
    public String toString() {
        return "Groupe [dateDeCreation=" + dateDeCreation + ", id=" + id + ", nom=" + nom + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Groupe other = (Groupe) obj;
        if (id != other.id)
            return false;
        return true;
    }

    
    
    
}
