package com.thiru.register;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends Activity {
	Database db2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		db2=new Database(this);
		final EditText et1=(EditText)findViewById(R.id.editText1);
		final EditText et2=(EditText)findViewById(R.id.editText2);
		final EditText et3=(EditText)findViewById(R.id.editText3);
		final EditText et4=(EditText)findViewById(R.id.editText4);
		Button b1=(Button)findViewById(R.id.button1);
		Button b2=(Button)findViewById(R.id.button2);
		Button b3=(Button)findViewById(R.id.btnClear);

		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				String sn=et1.getText().toString();
				String se=et2.getText().toString();
				String sc=et3.getText().toString();
				String sp=et4.getText().toString();
				
				//to check if previously any one registered with same name.
				int count=0;
				count=db2.isDuplicate(sn);
				int sn1=sn.length();
				int sn2=se.length();
				int sn3=sc.length();
				int sn4=sp.length();
				if(sn1==0||sn2==0||sn3==0||sn4==0){
//					et1.setError("All fields are mandatory");
//					et1.requestFocus();
				Toast.makeText(getApplicationContext(), "Enter all Details", Toast.LENGTH_SHORT).show();
		
				}
				else if(count>0){ //returns if any registered 
					et1.setError("User already registerd\nEnter Unique name");
					et1.requestFocus();
					et1.setText("");
				}
				else{
					db2.regdata(sn,se,sc,sp);//to insert the data.
				Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_SHORT).show();
//				Intent i1=new Intent(Signup.this,Login.class);
//				startActivity(i1);
				}
			}
		});
		b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				Intent it=new Intent(getApplicationContext(),Login.class);
				startActivity(it);
				
			}
		});
		b3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				et1.setText("");
				et2.setText("");
				et3.setText("");
				et4.setText("");
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_signup, menu);
		return true;
	}

}
