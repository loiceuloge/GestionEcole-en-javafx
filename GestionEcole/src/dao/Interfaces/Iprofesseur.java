package dao.Interfaces;

import java.util.List;

import dao.models.Professeur;

public interface Iprofesseur {
    public Professeur getByid(int id);

    public boolean addUpdate(Professeur professeur, boolean choice);
 
    public boolean delete(int id);
 
    public List<Professeur> getAll();
 
    public int countProfesseur();

 
}
