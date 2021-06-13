package dao.models;

import java.time.LocalDate;

public class Etudiant {
    private int id;
	private String nom;
	private String prenom;
	private String sexe;
    private LocalDate dateDeNaissance;
    private String lieuDeNaissance;
    private String nationalite;
    private String matricule;
    private String email;
    private String telephone;
    private Groupe groupe = new Groupe();
    
    public Etudiant() {
    }

    public Etudiant(int id, String nom, String prenom, String sexe, LocalDate dateDeNaissance, String lieuDeNaissance,
            String nationalite, String matricule, String email, String telephone, Groupe groupe) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.dateDeNaissance = dateDeNaissance;
        this.lieuDeNaissance = lieuDeNaissance;
        this.nationalite = nationalite;
        this.matricule = matricule;
        this.email = email;
        this.telephone = telephone;
        this.groupe = groupe;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public LocalDate getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(LocalDate dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public String getLieuDeNaissance() {
        return lieuDeNaissance;
    }

    public void setLieuDeNaissance(String lieuDeNaissance) {
        this.lieuDeNaissance = lieuDeNaissance;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    @Override
    public String toString() {
        return "Etudiant [dateDeNaissance=" + dateDeNaissance + ", email=" + email + ", groupe=" + groupe + ", id=" + id
                + ", lieuDeNaissance=" + lieuDeNaissance + ", matricule=" + matricule + ", nationalite=" + nationalite
                + ", nom=" + nom + ", prenom=" + prenom + ", sexe=" + sexe + ", telephone=" + telephone + "]";
    }

   



}
