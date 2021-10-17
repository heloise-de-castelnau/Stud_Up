package com.riseup.studup.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.riseup.studup.Model.CoursBank;
import com.riseup.studup.Model.User;
import com.riseup.studup.R;

public class Statistiques extends AppCompatActivity {

	private TextView stat1;
	private TextView stat2;
	private User mUser;
	private CoursBank mCoursBank;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_statistiques);

		Intent intent = getIntent();
		if(intent != null){
			mUser = intent.getParcelableExtra("user");
			mCoursBank = intent.getParcelableExtra("courseBank");
		}


		stat1 = (TextView) findViewById(R.id.activity_statistiques_stat1);
		stat2 = (TextView) findViewById(R.id.activity_statistiques_stat2);


		stat1.setText("Nombre de cours donnés : "+ mUser.getHeuresDonnees());
		stat2.setText("Nombre de cours reçus : "+ mUser.getHeuresRecues());
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