package com.riseup.studup.Model;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class CoursBank implements Parcelable {
	private List<Cours> coursBank;

	public CoursBank()
	{
		this.coursBank = new ArrayList<Cours>();

		Cours cours1 = new Cours("Probabilités Numériques", "Méthode de Monte Carlo","03/06/2021 14:00" , "A venir","plout");
		Cours cours2 = new Cours("Probabilités Numériques", "Estimateur","04/06/2021 08:00" , "A venir", "heloise.de_castelnau@edu.devinci.fr");
		Cours cours3 = new Cours("Statistiques", "ACP","04/06/2021 17:00" , "A venir","chloe.coursimault@edu.devinci.fr");
		Cours cours4 = new Cours("Statistiques", "Régression linéaire","05/06/2021 19:00" , "A venir","plout");
		Cours cours5 = new Cours("Traitement du signal", "Filtrage","08/06/2021 12:00" , "A venir", "heloise.de_castelnau@edu.devinci.fr");
		Cours cours6 = new Cours("Traitement du signal", "Modulation","08/06/2021 15:00" , "A venir", "chloe.coursimault@edu.devinci.fr");
		Cours cours7 = new Cours("Optimisation", "Simplexe","09/06/2021 16:00" , "A venir", "plout");

		coursBank.add(cours1);
		coursBank.add(cours2);
		coursBank.add(cours3);
		coursBank.add(cours4);
		coursBank.add(cours5);
		coursBank.add(cours6);
		coursBank.add(cours7);
	}



	public CoursBank(List<Cours> coursBank) {
		this.coursBank = coursBank;
	}

	protected CoursBank(Parcel in) {
		coursBank = in.createTypedArrayList(Cours.CREATOR);
	}

	public static final Creator<CoursBank> CREATOR = new Creator<CoursBank>() {
		@Override
		public CoursBank createFromParcel(Parcel in) {
			return new CoursBank(in);
		}

		@Override
		public CoursBank[] newArray(int size) {
			return new CoursBank[size];
		}
	};

	public List<Cours> getCoursBank() {
		return coursBank;
	}

	public boolean setCoursBank(Cours cours) {
		boolean b = this.coursBank.add(cours);
		return b;
	}

	public Cours unsetCoursBank(int position) {
		Cours cours = this.coursBank.remove(position);
		return cours;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeTypedList(coursBank);
	}
}