package com.riseup.studup.Model;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class UserBank {
	private List<User> mUserList;
	SharedPreferences userLocalDatabase;
	public static final String SP_NAME = "userDetails";

	public UserBank(List<User> userList) {
		// Storing User
		mUserList=userList;

	}

	public User getUser(String mUsermail) {
		// Loop over the questions and return a new one at each call
		int indexutilisateur=0;
		for(int index=0;index<  mUserList.size();index++)
		{
			if(mUserList.get(index).getEmail()==mUsermail)
			{
				indexutilisateur=index;
			}

		}
		return mUserList.get(indexutilisateur);
	}

	public ArrayList<String> getUserMail() {
		ArrayList<String> emailuser = new ArrayList<String>();
		for(int i=0; i<mUserList.size();i++)
		{
			emailuser.add(mUserList.get(i).getEmail());
		}
		return emailuser;
	}

	public ArrayList<String> getUserPassword() {
		ArrayList<String> passworduser = new ArrayList<String>();
		for(int i=0; i<mUserList.size();i++)
		{
			passworduser.add(mUserList.get(i).getPassword());
		}
		return passworduser;
	}

	public boolean UserExist(String mUsermail)
	{

		boolean answer =false;
		for(int index=0;index<  mUserList.size();index++)
		{
			if(mUserList.get(index).getEmail()==mUsermail)
			{
				answer=true;
			}
		}
		return answer;
	}
}
