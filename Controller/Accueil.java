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

import com.riseup.studup.Model.CoursBank;
import com.riseup.studup.Model.User;
import com.riseup.studup.R;

public class Accueil extends AppCompatActivity {

	private Button creator;
	private Button show;

	private CoursBank mCoursBank;
	private User mUser;

	private static final int CREATE_COURS_REQUEST_CODE = 42;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accueil);
		System.out.println("Accueil::OnCreate()");

		creator = (Button) findViewById(R.id.activity_main_create_course);
		show = (Button) findViewById(R.id.activity_main_show_course);

		Intent intent = getIntent();
		if(intent != null)
		{
			mUser = intent.getParcelableExtra("user");
			mCoursBank = intent.getParcelableExtra("courseBank");
		}

		creator.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent offres = new Intent(Accueil.this, Offres.class);
				offres.putExtra("courseBank", (Parcelable) mCoursBank);
				offres.putExtra("mot", "offres");
				offres.putExtra("user",(Parcelable) mUser);
				startActivity(offres);
			}
		});

		show.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent recherche = new Intent(Accueil.this, Recherche.class);
				recherche.putExtra("courseBank", (Parcelable) mCoursBank);
				recherche.putExtra("user",(Parcelable) mUser);
				startActivity(recherche);
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

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == RESULT_OK && requestCode == CREATE_COURS_REQUEST_CODE)
		{
			mCoursBank = data.getParcelableExtra("courseBank");
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	//region General Actions de Inscription Activity
	@Override
	protected void onStart() {
		super.onStart();

		System.out.println("Accueil::onStart()");
	}

	@Override
	protected void onResume() {
		super.onResume();

		System.out.println("Accueil::onResume()");
	}

	@Override
	protected void onPause() {
		super.onPause();

		System.out.println("Accueil::onPause()");
	}

	@Override
	protected void onStop() {
		super.onStop();

		System.out.println("Accueil::onStop()");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		System.out.println("Accueil::onDestroy()");
	}
	//endregion
}