package com.riseup.studup.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Cours implements Parcelable {
	private String matiere;
	private String sujet;
	private String horaire;
	private String libelle;
	private String mailDonneur;

	public Cours(String matiere, String sujet, String horaire, String libelle, String mailDonneur) {
		this.matiere = matiere;
		this.sujet = sujet;
		this.horaire = horaire;
		this.libelle = libelle;
		this.mailDonneur = mailDonneur;
	}

	protected Cours(Parcel in) {
		matiere = in.readString();
		sujet = in.readString();
		horaire = in.readString();
		libelle = in.readString();
		mailDonneur = in.readString();
	}

	public static final Creator<Cours> CREATOR = new Creator<Cours>() {
		@Override
		public Cours createFromParcel(Parcel in) {
			return new Cours(in);
		}

		@Override
		public Cours[] newArray(int size) {
			return new Cours[size];
		}
	};

	public String getMatiere() {
		return matiere;
	}

	public void setMatiere(String matiere) {
		this.matiere = matiere;
	}

	public String getSujet() {
		return sujet;
	}

	public void setSujet(String sujet) {
		this.sujet = sujet;
	}

	public String getHoraire() {
		return horaire;
	}

	public void setHoraire(String horaire) {
		this.horaire = horaire;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getMailDonneur() {
		return mailDonneur;
	}

	public void setMailDonneur(String mailDonneur) {
		this.mailDonneur = mailDonneur;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(matiere);
		dest.writeString(sujet);
		dest.writeString(horaire);
		dest.writeString(libelle);
		dest.writeString(mailDonneur);
	}

	@Override
	public String toString()
	{
		String value = this.matiere + ", " + this.sujet + "\n" + this.horaire;
		return value;
	}
}
