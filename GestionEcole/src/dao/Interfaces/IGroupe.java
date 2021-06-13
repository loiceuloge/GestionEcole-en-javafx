package dao.Interfaces;

import java.util.List;

import dao.models.Etudiant;
import dao.models.Groupe;

public interface IGroupe {
    
    public Groupe getById(int id);
    
    public boolean addUpdate(Groupe groupe, boolean choice);
        
    public boolean delete(int id);

    public List<Groupe> getAll();
    
    public List<Etudiant> getEtudiantByGroupe(int id);

    public int countGroupe();


}
