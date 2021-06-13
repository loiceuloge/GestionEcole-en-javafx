package dao.Interfaces;

import java.util.List;

import dao.models.Etudiant;

public interface IEtudiant {
   public Etudiant getByid(int id);

   public boolean addUpdate(Etudiant etudiant, boolean choice);

   public boolean delete(int id);

   public List<Etudiant> getAll();

   public int countEtudiant();

}
