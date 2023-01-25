//**********************************************************/
//Projet javaFX
//30-09-2022
//Cedric raymond  , 0867477
//Guillaume sauve , 1440441
//Mykhailo Niemtsev,2242977
//**********************************************************/

package com.manager;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Employe 
{
    private SimpleIntegerProperty  id;
    private SimpleStringProperty nom;
    private SimpleStringProperty prenom;
    private SimpleStringProperty sexe;
    private SimpleStringProperty emploi;
    private SimpleIntegerProperty experience;
    
    public Employe(){};

    public Employe(int id, String nom, String prenom, String sexe, String emploi, int experience) {
        this.id = new SimpleIntegerProperty(id);
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.sexe = new SimpleStringProperty(sexe);
        this.emploi = new SimpleStringProperty(emploi);
        this.experience = new SimpleIntegerProperty(experience);
    }

    public int getId() {
        return id.get();
    }
    public void setId(int id) {
        this.id = new SimpleIntegerProperty(id);
    }
    public String getNom() {
        return nom.get();
    }
    public void setNom(String nom) {
        this.nom = new SimpleStringProperty(nom);;
    }
    public String getPrenom() {
        return prenom.get();
    }
    public void setPrenom(String prenom) {
        this.prenom = new SimpleStringProperty(prenom);;
    }
    public String getSexe() {
        if(this.sexe.get().equals("Masculin"))
        {
            return LanguageData.getText(12, 0);
        }
        else if(this.sexe.get().equals("Feminin"))
        {
            return LanguageData.getText(12, 1);
        }
        else
        {
            return LanguageData.getText(12, 2);
        }
    }

    public String getUnlocalizedSexe()
    {
        return sexe.get();
    }

    public void setSexe(String sexe) {
        this.sexe = new SimpleStringProperty(sexe);
    }
    public String getEmploi() {
        return emploi.get();
    }
    public void setEmploi(String emploi) {
        this.emploi = new SimpleStringProperty(emploi);
    }
    public int getExperience() {
        return experience.get();
    }
    public void setExperience(int experience) {
        this.experience = new SimpleIntegerProperty(experience);;
    } 


    
}
