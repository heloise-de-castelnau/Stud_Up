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
import android.widget.EditText;

import com.riseup.studup.Model.CoursBank;
import com.riseup.studup.Model.User;
import com.riseup.studup.R;

public class Recherche extends AppCompatActivity {

	private EditText motCle;
	private Button recherche;
	String mot = "";
	private User mUser;
	private CoursBank mCoursBank;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recherche);

		motCle = (EditText) findViewById(R.id.activity_research_mot);
		recherche = (Button) findViewById(R.id.activity_research_button);

		Intent mIntent = getIntent();

		mCoursBank = (CoursBank) mIntent.getParcelableExtra("courseBank");

		if(mIntent != null){
			mUser = mIntent.getParcelableExtra("user");
		}

		recherche.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mot = motCle.getText().toString();

				Intent researchCourse = new Intent(Recherche.this, Offres.class);
				researchCourse.putExtra("mot", mot);
				researchCourse.putExtra("courseBank", (Parcelable) mCoursBank);
				researchCourse.putExtra("user",(Parcelable) mUser);
				startActivity(researchCourse);
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