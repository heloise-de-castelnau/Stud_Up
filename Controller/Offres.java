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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.riseup.studup.Model.Cours;
import com.riseup.studup.Model.CoursBank;
import com.riseup.studup.Model.User;
import com.riseup.studup.R;

import java.util.ArrayList;
import java.util.List;

public class Offres extends AppCompatActivity {

	private ListView description;
	private User mUser;
	private CoursBank mCoursBank;
	private String mMot;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_offres);

		Intent mIntent = getIntent();
		if(mIntent != null){
			mUser = mIntent.getParcelableExtra("user");
			mCoursBank = mIntent.getParcelableExtra("courseBank");
			mMot = mIntent.getStringExtra("mot");
		}

		description = (ListView) findViewById(R.id.activity_show_course_description);

		List<Cours> mCoursShow = new ArrayList<Cours>();

		if(mMot.equals("offres"))
		{
			mCoursShow = mCoursBank.getCoursBank();
		}
		else
		{
			for(int i = 0; i<mCoursBank.getCoursBank().size(); i++)
			{
				if(mCoursBank.getCoursBank().get(i).getMatiere().equals(mMot) || mCoursBank.getCoursBank().get(i).getSujet().equals(mMot))
				{
					mCoursShow.add(mCoursBank.getCoursBank().get(i));
				}
			}
		}

		ArrayAdapter<Cours> arrayAdapter = new ArrayAdapter<Cours>(this, android.R.layout.simple_list_item_1, mCoursShow);

		description.setAdapter(arrayAdapter);

		List<Cours> finalMCoursShow = mCoursShow;
		description.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Cours mCours = finalMCoursShow.get(position);
				Intent detail = new Intent(Offres.this, DetailCours.class);
				detail.putExtra("position",position);
				detail.putExtra("cours",mCours);
				detail.putExtra("user",mUser);
				detail.putExtra("courseBank",(Parcelable) mCoursBank);
				startActivity(detail);
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

	//region General Actions de Inscription Activity
	@Override
	protected void onStart() {
		super.onStart();

		System.out.println("Offres::onStart()");
	}

	@Override
	protected void onResume() {
		super.onResume();

		System.out.println("Offres::onResume()");
	}

	@Override
	protected void onPause() {
		super.onPause();

		System.out.println("Offres::onPause()");
	}

	@Override
	protected void onStop() {
		super.onStop();

		System.out.println("Offres::onStop()");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		System.out.println("Offres::onDestroy()");
	}
	//endregion
}