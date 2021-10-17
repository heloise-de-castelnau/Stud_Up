package com.riseup.studup.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.riseup.studup.Model.Cours;
import com.riseup.studup.Model.CoursBank;
import com.riseup.studup.Model.User;
import com.riseup.studup.R;

public class CreationOffre extends AppCompatActivity {

	private TextView mQuestion;
	private EditText mAnswer;
	private Button mSubmit;

	String matiere = "";
	String sujet = "";

	private User mUser;
	private CoursBank mCoursBank;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_creation_offre);

		mQuestion = (TextView) findViewById(R.id.activity_create_course_question);
		mAnswer = (EditText) findViewById(R.id.activity_create_course_answer);
		mSubmit = (Button) findViewById(R.id.activity_create_course_submit);

		Intent mIntent = getIntent();
		if(mIntent != null){
			mUser = mIntent.getParcelableExtra("user");
			mCoursBank = mIntent.getParcelableExtra("courseBank");
		}


		mQuestion.setText("Saisissez la matière :");

		mSubmit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				matiere = mAnswer.getText().toString();

				mAnswer.getText().clear();

				mQuestion.setText("Saisissez le sujet :");

				mSubmit.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						sujet = mAnswer.getText().toString();
						Cours cours = new Cours(matiere, sujet,"03/06/2021 14:00" , "Cours a venir", mUser.getEmail());

						String b = String.valueOf(mCoursBank.setCoursBank(cours));
						Toast toast =Toast.makeText(CreationOffre.this,b, Toast.LENGTH_SHORT);
						toast.show();

						Intent retourCourse = new Intent(CreationOffre.this,Accueil.class);
						retourCourse.putExtra("courseBank",(Parcelable) mCoursBank);
						retourCourse.putExtra("user",(Parcelable) mUser);
						startActivity(retourCourse);
						//setResult(RESULT_OK, retourCourse);
						//finish();
					}
				});
			}
		});
	}
}