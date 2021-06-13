package dao.Interfaces;

import java.util.List;

import dao.models.Etudiant;
import dao.models.Groupe;

public interface IGroupeEtudiant {
    public List<Etudiant> getEtudiants (Groupe groupe);
}
