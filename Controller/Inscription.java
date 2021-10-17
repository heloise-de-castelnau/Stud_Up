package com.riseup.studup.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.riseup.studup.Model.User;
import com.riseup.studup.Model.UserBank;
import com.riseup.studup.R;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class Inscription extends AppCompatActivity {

	private SharedPreferences mPreferences;
	private EditText iNomInput;
	private EditText iPrenomInput;
	private EditText iEcoleInput;
	private EditText iAnneeInput;
	private EditText iMailInput;
	private EditText iPasswordInput;
	private Button iInscriptionButton;
	private User iUser;
	private User newUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inscription);
		System.out.println("Inscription::onCreate()");

		// On initialise User
		iUser = new User("","","","","","");
		mPreferences=getPreferences(MODE_PRIVATE);
		SharedPreferences settings = getSharedPreferences("UserInfo", MODE_PRIVATE);
		//On lie la lecture du layout avec le fichier en background

		iNomInput = (EditText) findViewById(R.id.activity_inscription_nom_input);
		iPrenomInput = (EditText) findViewById(R.id.activity_inscription_prenom_input);
		iEcoleInput = (EditText) findViewById(R.id.activity_inscription_ecole_input);
		iAnneeInput = (EditText) findViewById(R.id.activity_inscription_annee_input);
		iMailInput = (EditText) findViewById(R.id.activity_inscription_mail_input);
		iPasswordInput = (EditText) findViewById(R.id.activity_inscription_password_input);
		iInscriptionButton = (Button) findViewById(R.id.activity_inscription_init_btn);
		iInscriptionButton.setEnabled(false);

		//region Si on commence à écrire son mail
		iPasswordInput.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// This is where we'll check the user input
				if(iNomInput.toString().length()!=0 && iPrenomInput.toString().length()!=0 &&
						iEcoleInput.toString().length()!=0 && iAnneeInput.toString().length()!=0 && iMailInput.toString().length()!=0){
					iInscriptionButton.setEnabled(s.toString().length() != 0);
				}

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
		//endregion

		//region  Si on clique sur le bouton d'inscription
		// le dirige vers la page de main
		iInscriptionButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v) {
				//String firstname=mNameInput.getText().toString();
				//mUser.setFirstName(firstname);
				//mPreferences.edit().putString(PREF_KEY_FIRSTNAME,mUser.getFirstName()).apply();
				String nom=iNomInput.getText().toString();
				String prenom=iPrenomInput.getText().toString();
				String ecole=iEcoleInput.getText().toString();
				String annee=iAnneeInput.getText().toString();
				String mail=iMailInput.getText().toString();
				String password=iPasswordInput.getText().toString();

				iUser.setNom(nom);
				iUser.setPrenom(prenom);
				iUser.setEcole(ecole);
				iUser.setAnnee(annee);
				iUser.setEmail(mail);
				iUser.setPassword(password);
				newUser= new User(nom,prenom,ecole,annee,mail,password);

				SharedPreferences.Editor editor = settings.edit();
				editor.putString("Nom",iUser.getNom());
				editor.putString("Prenom",iUser.getPrenom());
				editor.putString("Ecole",iUser.getEcole());
				editor.putString("Annee",iUser.getAnnee());
				editor.putString("Mail",iUser.getEmail());
				editor.putString("Password",iUser.getPassword());
				editor.apply();

				Intent inscription_init_utilisateur = new Intent(Inscription.this, MainActivity.class);
				inscription_init_utilisateur.putExtra("Nom", nom);
				inscription_init_utilisateur.putExtra("Prenom", prenom);
				inscription_init_utilisateur.putExtra("Ecole", ecole);
				inscription_init_utilisateur.putExtra("Annee", annee);
				inscription_init_utilisateur.putExtra("Mail", mail);
				inscription_init_utilisateur.putExtra("Password", password);
				startActivity(inscription_init_utilisateur);
			}
		});
		//endregion
	}

	public User GetUserFromInscription()
	{
		return newUser ;
	}

	//region General Actions de Inscription Activity
	@Override
	protected void onStart() {
		super.onStart();

		System.out.println("Inscription::onStart()");
	}

	@Override
	protected void onResume() {
		super.onResume();

		System.out.println("Inscription::onResume()");
	}

	@Override
	protected void onPause() {
		super.onPause();

		System.out.println("Inscription::onPause()");
	}

	@Override
	protected void onStop() {
		super.onStop();

		System.out.println("Inscription::onStop()");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		System.out.println("Inscription::onDestroy()");
	}

	//endregion
}