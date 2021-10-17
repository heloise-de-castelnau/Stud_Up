package com.riseup.studup.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.riseup.studup.Model.CoursBank;
import com.riseup.studup.Model.User;
import com.riseup.studup.R;

public class ProfilModification extends AppCompatActivity {

	private User mUser;
	private SharedPreferences mSharedPreferences;
	private EditText mNomProfil;
	private EditText mPrenomProfil;
	private TextView mMailProfil;
	private EditText mEcoleProfil;
	private EditText mAnneeProfil;
	private Button mBoutonStatistiquesProfil;
	private Button mBoutonModifierProfil;
	private CoursBank mCoursBank;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profil_modification);

		System.out.println("ProfilModification::onCreate()");

		mNomProfil = findViewById(R.id.nomProfilModification);
		mPrenomProfil = findViewById(R.id.prenomProfilModification);
		mMailProfil = findViewById(R.id.mailProfilModification);
		mEcoleProfil = findViewById(R.id.ecoleProfilModification);
		mAnneeProfil = findViewById(R.id.anneeProfilModification);
		mBoutonStatistiquesProfil = findViewById(R.id.boutonStatistiquesProfilModification);
		mBoutonModifierProfil = findViewById(R.id.boutonModifierProfilModification);

		Intent intent = getIntent();
		if(intent != null){
			mUser = intent.getParcelableExtra("user");
			if(mUser != null){
				if(mUser.getPrenom()!=null && mUser.getPrenom()!=""){
					mPrenomProfil.setText(mUser.getPrenom());
				}
				if(mUser.getNom()!=null && mUser.getNom()!=""){
					mNomProfil.setText(mUser.getNom());
				}
				if(mUser.getAnnee()!=null && mUser.getAnnee()!=""){
					mAnneeProfil.setText(mUser.getAnnee());
				}
				if(mUser.getEmail()!=null && mUser.getEmail()!=""){
					mMailProfil.setText(mUser.getEmail());
				}
				if(mUser.getEcole()!=null && mUser.getEcole()!=""){
					mEcoleProfil.setText(mUser.getEcole());
				}
			}
			mCoursBank = intent.getParcelableExtra("courseBank");
		}

		mBoutonStatistiquesProfil.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ProfilModification.this,Statistiques.class);
				intent.putExtra("user",mUser);
				intent.putExtra("courseBank",mCoursBank);
				startActivity(intent);
			}
		});

		mBoutonModifierProfil.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mUser.setPrenom(mPrenomProfil.getText().toString());
				mUser.setNom(mNomProfil.getText().toString());
				mUser.setEcole(mEcoleProfil.getText().toString());
				mUser.setAnnee(mAnneeProfil.getText().toString());
				Intent intent = new Intent(ProfilModification.this,Profil.class);
				intent.putExtra("user",mUser);
				intent.putExtra("courseBank",mCoursBank);
				startActivity(intent);
			}
		});
	}

	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		switch (item.getItemId()){
			case R.id.Home:
				Intent intent = new Intent(this, Accueil.class);
				intent.putExtra("user",(Parcelable) mUser);
				intent.putExtra("courseBank",(Parcelable) mCoursBank);
				this.startActivity(intent);
				return true;

			case R.id.Profil:
				Intent intent2 = new Intent(this, Profil.class);
				intent2.putExtra("user",(Parcelable) mUser);
				intent2.putExtra("courseBank",(Parcelable) mCoursBank);
				this.startActivity(intent2);
				return true;

			case R.id.Statistique:
				Intent intent3 = new Intent(this, Statistiques.class);
				intent3.putExtra("user",(Parcelable) mUser);
				intent3.putExtra("courseBank",(Parcelable) mCoursBank);
				this.startActivity(intent3);
				return true;

			case R.id.CreaCours:
				Intent intent4 = new Intent(this, CreationOffre.class);
				intent4.putExtra("user",(Parcelable) mUser);
				intent4.putExtra("courseBank",(Parcelable) mCoursBank);
				this.startActivity(intent4);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}