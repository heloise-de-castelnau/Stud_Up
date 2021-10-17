package com.riseup.studup.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.riseup.studup.Model.Cours;
import com.riseup.studup.Model.CoursBank;
import com.riseup.studup.Model.User;
import com.riseup.studup.R;

import java.util.List;

public class DetailCours extends AppCompatActivity {

	private TextView mNom;
	private TextView mPrenom;
	private TextView mNote;
	private TextView mEcole;
	private TextView mAnnee;
	private TextView mMatiere;
	private TextView mSujet;
	private TextView mHoraire;
	private Button mReserver;

	private User mUser;
	private CoursBank mCoursBank;
	private Cours mCours;
	private int mPosition;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_cours);
		System.out.println("DetailCours::onCreate()");

		mNom = findViewById(R.id.nomOrganisateurDetailCours);
		mPrenom = findViewById(R.id.prenomOrganisateurDetailCours);
		mNote = findViewById(R.id.noteOrganisateurDetailCours);
		mEcole = findViewById(R.id.ecoleOrganisateurDetailCours);
		mAnnee = findViewById(R.id.anneeOrganisateurDetailCours);
		mMatiere = findViewById(R.id.typeOrganisateurDetailCours);
		mSujet = findViewById(R.id.nomOffreOrganisateurDetailCours);
		mHoraire = findViewById(R.id.horaireOrganisateurDetailCours);
		mReserver = findViewById(R.id.boutonReserverDetailCours);

		Intent intent = getIntent();
		if(intent != null){
			mUser = intent.getParcelableExtra("user");
			mCours = intent.getParcelableExtra("cours");
			mCoursBank = intent.getParcelableExtra("courseBank");
			mPosition = intent.getIntExtra("position",-1);



			mNom.setText(mUser.getNom());
			mPrenom.setText(mUser.getPrenom());
			mNote.setText(mUser.getNote()+"");
			mEcole.setText(mUser.getEcole());
			mAnnee.setText(mUser.getAnnee());
			mMatiere.setText(mCours.getMatiere());
			mSujet.setText(mCours.getSujet());
			mHoraire.setText(mCours.getHoraire());
		}

		mReserver.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String b = String.valueOf(mCoursBank.unsetCoursBank(mPosition));
				Toast toast =Toast.makeText(DetailCours.this,b, Toast.LENGTH_SHORT);
				toast.show();
				Intent intent1 = new Intent(DetailCours.this,Accueil.class);
				intent1.putExtra("user",mUser);
				intent1.putExtra("courseBank",mCoursBank);
				startActivity(intent1);
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