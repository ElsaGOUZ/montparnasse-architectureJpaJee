package com.infotel.metier;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity

//1er mode d'heritage(une table pour ttes les classes)= single table
@ Inheritance(strategy= InheritanceType.SINGLE_TABLE)
//ensuite on donne le nom de la colonne de discrimination
@DiscriminatorColumn(name="TYPE_PERS")
@DiscriminatorValue("PERS")

//2eme mode d'heritage(une table par classe)=table per class

//pour table per class la ligne :@GeneratedValue(strategy=GenerationType.IDENTITY) doit etre enlever
//@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)

//3eme mode d'heritage(jointure)= Joined
//@Inheritance(strategy=InheritanceType.JOINED)

public class Personne {
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
     protected int id;
    protected  String nom;
    protected String prenom;
     protected int age;
     
     @ManyToOne //(cascade = {CascadeType.PERSIST})
     private Adresse adresse;
     
     @OneToOne(cascade= {CascadeType.PERSIST})
     private Connexion connexion;
     
     @ManyToMany(mappedBy="personnes")
	 private List<Club> clubs= new ArrayList<Club>();
    
	public List<Club> getClubs() {
		return clubs;
	}
	public void setClubs(List<Club> clubs) {
		this.clubs = clubs;
	}
	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	public Connexion getConnexion() {
		return connexion;
	}
	public void setConnexion(Connexion connexion) {
		this.connexion = connexion;
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Personne [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", age=" + age + "]";
	}
	
     
}
