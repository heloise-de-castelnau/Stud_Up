package com.riseup.studup.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
	private String mPrenom;
	private String mNom;
	private String mEcole;
	private String mAnnee;
	private String mEmail;
	private String mPassword;
	private int mHeuresDonnees;
	private int mHeuresRecues;
	private double mNote;


	public User(String prenom, String nom, String ecole, String annee, String email, String password) {
		mPrenom = prenom;
		mNom = nom;
		mEcole = ecole;
		mAnnee = annee;
		mEmail = email;
		mPassword = password;
		mHeuresDonnees = 0;
		mHeuresRecues = 0;
		mNote = 2.5;
	}

	public User(String prenom, String nom, String ecole, String annee, String email, String password,int heuresDonnees, int heuresRecues, double note) {
		mPrenom = prenom;
		mNom = nom;
		mEcole = ecole;
		mAnnee = annee;
		mEmail = email;
		mPassword = password;
		mHeuresDonnees = heuresDonnees;
		mHeuresRecues = heuresRecues;
		mNote = note;
	}

	public User(){

	}

	//region Méthodes pour recréer un user à partir d'un Parcel
	protected User(Parcel in) {
		mPrenom = in.readString();
		mNom = in.readString();
		mEcole = in.readString();
		mAnnee = in.readString();
		mEmail = in.readString();
		mPassword = in.readString();
		mHeuresDonnees = in.readInt();
		mHeuresRecues = in.readInt();
		mNote = in.readDouble();
	}

	public static final Creator<User> CREATOR = new Creator<User>() {
		@Override
		public User createFromParcel(Parcel in) {
			return new User(in);
		}

		@Override
		public User[] newArray(int size) {
			return new User[size];
		}
	};
	//endregion

	//region Getter et Setter des attributs de classe
	public String getPrenom() {
		return mPrenom;
	}

	public void setPrenom(String prenom) {
		mPrenom = prenom;
	}

	public String getNom() {
		return mNom;
	}

	public void setNom(String nom) {
		mNom = nom;
	}

	public String getEcole() {
		return mEcole;
	}

	public void setEcole(String ecole) {
		mEcole = ecole;
	}

	public String getAnnee() {
		return mAnnee;
	}

	public void setAnnee(String annee) {
		mAnnee = annee;
	}

	public String getEmail() {
		return mEmail;
	}

	public void setEmail(String email) {
		mEmail = email;
	}

	public String getPassword() {
		return mPassword;
	}

	public void setPassword(String password) {
		mPassword = password;
	}

	public int getHeuresDonnees() {
		return mHeuresDonnees;
	}

	public void setHeuresDonnees(int heuresDonnees) {
		mHeuresDonnees = heuresDonnees;
	}

	public int getHeuresRecues() {
		return mHeuresRecues;
	}

	public void setHeuresRecues(int heuresRecues) {
		mHeuresRecues = heuresRecues;
	}

	public double getNote() {
		return mNote;
	}

	public void setNote(double note) {
		mNote = note;
	}

	//enregion

	//Méthodes pour créer le parcel à partir du user
	@Override
	public String toString(){
		return "User{ \n" +
				"mPrenom = " + mPrenom + "\'" + "mNom = " + mNom + "\n" +
				"}";
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(mPrenom);
		dest.writeString(mNom);
		dest.writeString(mEcole);
		dest.writeString(mAnnee);
		dest.writeString(mEmail);
		dest.writeString(mPassword);
		dest.writeInt(mHeuresDonnees);
		dest.writeInt(mHeuresRecues);
		dest.writeDouble(mNote);
	}
	//endregion
}
