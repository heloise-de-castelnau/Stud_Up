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


public class Profil extends AppCompatActivity {

	private SharedPreferences mSharedPreferences;
	private TextView mNomProfil;
	private TextView mPrenomProfil;
	private TextView mMailProfil;
	private TextView mEcoleProfil;
	private TextView mAnneeProfil;
	private Button mBoutonStatistiquesProfil;
	private Button mBoutonModifierProfil;
	private User mUser;
	private CoursBank mCoursBank;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profil);
		System.out.println("Profil::onCreate()");

		mNomProfil = findViewById(R.id.nomProfil);
		mPrenomProfil = findViewById(R.id.prenomProfil);
		mMailProfil = findViewById(R.id.mailProfil);
		mEcoleProfil = findViewById(R.id.ecoleProfil);
		mAnneeProfil = findViewById(R.id.anneeProfil);
		mBoutonStatistiquesProfil = findViewById(R.id.boutonStatistiquesProfil);
		mBoutonModifierProfil = findViewById(R.id.boutonModifierProfil);

		//region On lit les informations transmises à propos du User
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
			mCoursBank=intent.getParcelableExtra("courseBank");
		}
		//endregion

		//region Modifier les informations
		mBoutonModifierProfil.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent profil = new Intent(Profil.this, ProfilModification.class);
				profil.putExtra("user",(Parcelable) mUser);
				profil.putExtra("courseBank",mCoursBank);
				startActivity(profil);
			}
		});

		mBoutonStatistiquesProfil.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent stats = new Intent(Profil.this, Statistiques.class);
				stats.putExtra("user",(Parcelable) mUser);
				stats.putExtra("courseBank",mCoursBank);
				startActivity(stats);
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


	//region General Actions de Profil Activity
	@Override
	protected void onStart() {
		super.onStart();

		System.out.println("Profil::onStart()");
	}

	@Override
	protected void onResume() {
		super.onResume();

		System.out.println("Profil::onResume()");
	}

	@Override
	protected void onPause() {
		super.onPause();

		System.out.println("Profil::onPause()");
	}

	@Override
	protected void onStop() {
		super.onStop();

		System.out.println("Profil::onStop()");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		System.out.println("Profil::onDestroy()");
	}
}