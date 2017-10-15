package com.thiru.register;


import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class Login extends Activity {
String str,s1,s2;

Database db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		final EditText et1;
		final EditText et2;
		et1=(EditText)findViewById(R.id.userName);
		et2=(EditText)findViewById(R.id.pswd);
		db=new Database(this);
		Button bt1=(Button)findViewById(R.id.login);
		bt1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				 s1=et1.getText().toString();
				 s2=et2.getText().toString();
				 int count=0;
				count=db.login(s1,s2);
			 
//				String get[]=str.split("$");
//				String name=get[0];
//				String mail=get[1];
//				String pas=get[2];
//				if((s1.equals(name)||s1.equals(mail))&& s2.equals(pas)){
//					Intent it=new Intent(getApplicationContext(),Details.class);
//					it.putExtra("name", s1);
//					startActivity(it);
//				}
				int field1=s1.length();
				int field2=s2.length();
				if(field1==0||field2==0){
					//Toast.makeText(getApplicationContext(), "Enter all fields", Toast.LENGTH_LONG).show();
					et1.setError("User name required");
					et2.setError("Password required");
					et1.requestFocus();
				}
				else if(count<=0)
				{
					et1.setError("invalid username or password");
					et1.requestFocus();
					et1.setText("");
					et2.setText("");
				}
				else
				{
					Intent it=new Intent(Login.this,Details.class);
					it.putExtra("name", s1);
					startActivity(it);
				}
			}
		});
		
		Button bt2=(Button)findViewById(R.id.reg);
		bt2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			
			Intent it=new Intent(Login.this,Signup.class);
			startActivity(it);
			}
		});
	}
	@Override
	public void onBackPressed() {
	new AlertDialog.Builder(this).setTitle("EXIT").setNegativeButton("no", null)
		.setMessage("Are you sure?")
		.setPositiveButton("yes", new DialogInterface.OnClickListener(){

		@Override
		public void onClick(DialogInterface arg0, int arg1) {
			Login.super.onBackPressed();
		}
	 
	}).create().show();

}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}

}
