package com.riseup.studup.Controller;

// On importe les packages nécessaires pour notre code

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.riseup.studup.Model.CoursBank;
import com.riseup.studup.Model.User;
import com.riseup.studup.Model.UserBank;
import com.riseup.studup.R;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

	// On initialise nos variables de classes
	private static final String PREF_KEY_MAIL ="Mail";

	private SharedPreferences mPreferences;
	private EditText mMailInput;
	private EditText mPasswordInput;
	private Button mEnterButton;
	private Button mInscriptionButton;

	private User mUser;
	private CoursBank mCoursBank = new CoursBank();
	private UserBank mUserBank;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//On lie le main avec son visuel
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		System.out.println("MainActivity::Oncreate()");

		//On initialise User
		mUser = new User("","","","","","");
		generateUser();
		mPreferences=getPreferences(MODE_PRIVATE);

		//On lie la lecture du layout avec le fichier en background
		mMailInput = (EditText) findViewById(R.id.activity_main_mail_input);
		mPasswordInput = (EditText) findViewById(R.id.activity_main_password_input);
		mEnterButton = (Button) findViewById(R.id.activity_main_enter_btn);
		mInscriptionButton = (Button) findViewById(R.id.activity_main_inscription_btn);
		mEnterButton.setEnabled(false);

		//region Si on commence à écrire son mail
		mMailInput.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// This is where we'll check the user input
				mEnterButton.setEnabled(s.toString().length() != 0);
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
		//endregion

		//region Si on clique sur le bouton de Connection
		//Verifier si les infos de l'utilisateur existent déja , si elles existe lui dire bonjour,
		// sinon le demander de sinscrire
		mEnterButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v) {
				String mail=mMailInput.getText().toString();
				mUser.setEmail(mail);
				String password=mPasswordInput.getText().toString();
				mUser.setPassword(password);
				mPreferences.edit().putString(PREF_KEY_MAIL,mUser.getEmail()).apply();

				if(UserExistMain(mail))
				{
					if(UsermatchPassword(mail,password))
					{
						mUser=getUserfromgenerate(mail);

						Intent accueil = new Intent(MainActivity.this,Accueil.class);
						accueil.putExtra("user",(Parcelable) mUser);
						accueil.putExtra("courseBank",(Parcelable) mCoursBank);
						startActivity(accueil);
					}
					else
					{

						Toast toast =Toast.makeText(MainActivity.this,"Oups, mauvais mot de passe :)", Toast.LENGTH_SHORT);
						toast.show();
					}
				}
				else
				{

					Toast toast =Toast.makeText(MainActivity.this,"Oups, mauvais email :)", Toast.LENGTH_SHORT);
					toast.show();
				}
			}
		});
		//endregion

		//region  Si on clique sur le bouton d'inscription
		// le dirige vers une autre page avec l'inscription
		mInscriptionButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v) {
				Intent inscription_utilisateur = new Intent(MainActivity.this, Inscription.class);
				startActivity(inscription_utilisateur);
			}
		});
		//endregion
	}

	private UserBank generateUser(){
		Intent inscription_init_utilisateur = getIntent();
		String nom_inscription = "";
		String prenom_inscription = "";
		String ecole_inscription = "";
		String annee_inscription = "";
		String mail_inscription = "";
		String mdp_inscription = "";

		if (inscription_init_utilisateur != null){
			if (inscription_init_utilisateur.hasExtra("Mail")){
				nom_inscription=inscription_init_utilisateur.getStringExtra("Nom");
				prenom_inscription=inscription_init_utilisateur.getStringExtra("Prenom");
				ecole_inscription=inscription_init_utilisateur.getStringExtra("Ecole");
				annee_inscription=inscription_init_utilisateur.getStringExtra("Annee");
				mail_inscription = inscription_init_utilisateur.getStringExtra("Mail");
				mdp_inscription=inscription_init_utilisateur.getStringExtra("Password");
			}
		}
		User user1= new User("Pout","Tom","ESILV","2","plout","azerty",3,0,3.5);
		User user2= new User("de Castelnau","Héloïse","ESILV","3","heloise.de_castelnau@edu.devinci.fr","azerty123",2,0,3.8);
		User user3= new User("Coursimault ","Chloé","ESILV","3","chloe.coursimault@edu.devinci.fr","azerty12",2,0,4);
		User user_inscrit=new User(nom_inscription,prenom_inscription,ecole_inscription,annee_inscription,mail_inscription,mdp_inscription);

		return new UserBank(Arrays.asList(user1,user2,user3,user_inscrit));
	}

	public boolean UsermatchPassword(String mUsermail,String Password)
	{
		boolean answer=false;
		int index1=0;
		ArrayList<String> emailS = new ArrayList<String>();
		emailS=generateUser().getUserMail();
		ArrayList <String> passwordS = new ArrayList<String>();
		passwordS=generateUser().getUserPassword();
		for (String element : emailS){
			index1++;
			if (element.contains(mUsermail))
			{
				if(index1-1==passwordS.indexOf(Password))
				{
					answer=true;
				}
			}
		}
		return answer;
	}

	public boolean UserExistMain(String mUsermail)
	{
		boolean answer=false;
		ArrayList <String> emailS = new ArrayList<String>();
		emailS=generateUser().getUserMail();
		for (String element : emailS){

			if (element.contains(mUsermail)){

				answer=true;

			}
		}
		return answer;
	}

	private User getUserfromgenerate(String mymail)
	{
		User myUser;
		Intent inscription_init_utilisateur = getIntent();
		String nom_inscription = "";
		String prenom_inscription = "";
		String ecole_inscription = "";
		String annee_inscription = "";
		String mail_inscription = "";
		String mdp_inscription = "";
		if (inscription_init_utilisateur != null){
			if (inscription_init_utilisateur.hasExtra("Mail")){
				nom_inscription=inscription_init_utilisateur.getStringExtra("Nom");
				prenom_inscription=inscription_init_utilisateur.getStringExtra("Prenom");
				ecole_inscription=inscription_init_utilisateur.getStringExtra("Ecole");
				annee_inscription=inscription_init_utilisateur.getStringExtra("Annee");
				mail_inscription = inscription_init_utilisateur.getStringExtra("Mail");
				mdp_inscription=inscription_init_utilisateur.getStringExtra("Password");
			}
		}
		User user1= new User("Pout","Tom","ESILV","2","plout","azerty",3,0,3.5);
		User user2= new User("de Castelnau","Héloïse","ESILV","3","heloise.de_castelnau@edu.devinci.fr","azerty123",2,0,3.8);
		User user3= new User("Coursimault ","Chloé","ESILV","3","chloe.coursimault@edu.devinci.fr","azerty12",2,0,4);
		User user_inscrit=new User(nom_inscription,prenom_inscription,ecole_inscription,annee_inscription,mail_inscription,mdp_inscription);
		myUser=user1;
		ArrayList <String> liste = new ArrayList<String>();
		liste.add(user1.getEmail());
		liste.add(user2.getEmail());
		liste.add(user3.getEmail());
		liste.add(user_inscrit.getEmail());
		int index=0;
		for (String element : liste){
			index++;
			if (element.contains(mymail))
			{
				if(index==1){myUser=user1;}
				if(index==2){myUser=user2;}
				if(index==3){myUser=user3;}
				if(index==4){myUser=user_inscrit;}



			}
		}
		return myUser;
	}

	//region General Actions de Inscription Activity
	@Override
	protected void onStart() {
		super.onStart();

		System.out.println("MainActivity::onStart()");
	}

	@Override
	protected void onResume() {
		super.onResume();

		System.out.println("MainActivity::onResume()");
	}

	@Override
	protected void onPause() {
		super.onPause();

		System.out.println("MainActivity::onPause()");
	}

	@Override
	protected void onStop() {
		super.onStop();

		System.out.println("MainActivity::onStop()");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		System.out.println("MainActivity::onDestroy()");
	}
	//endregion
}