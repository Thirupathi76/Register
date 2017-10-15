package com.thiru.register;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Display extends Activity {
	Database db3;
	String a1;
	String s,s11,s12,s13;
	Button bCall,bMsg,bEmail;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display);
		db3 = new Database(this);
		TextView tv = (TextView) findViewById(R.id.textView1);
		TextView tv1 = (TextView) findViewById(R.id.textView2);
		TextView tv2 = (TextView) findViewById(R.id.textView3);
		TextView tv3 = (TextView) findViewById(R.id.textView4);
		Bundle b = getIntent().getExtras();
		String s1 = b.getString("first");
		a1 = db3.showDetails(s1);
		String ss[] = a1.split("#");
		 s = ss[0];
		tv.setText(s);
		System.out.println("@@##" + a1);
		 s11 = ss[1];
		tv1.setText(s11);

		 s12 = ss[2];
		tv2.setText(s12);

		 s13 = ss[3];
		tv3.setText(s13);

		// tv.setAdapter(new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_1,a1));
		// String s2=b.getString("second");
		// String s3=b.getString("third");
		// tv.setText(s1+s2+s3);
		
		
	}
	public void action(View view){
		Intent intent=null, chooser=null;
		
		if(view.getId()==R.id.btnEmail){
			
			intent=new Intent(Intent.ACTION_SEND);
			intent.setData(Uri.parse("mailto:"));
			//String to[]={"tirupathi7676@gmail.com","thirupathi7676@outlook.com"};
			intent.putExtra(Intent.EXTRA_EMAIL, s11);
			intent.putExtra(Intent.EXTRA_SUBJECT,"Great App");
			intent.putExtra(Intent.EXTRA_TEXT, "super ra");
			intent.setType("message/rfc822");
			chooser=Intent.createChooser(intent, "choose any");
			startActivity(chooser);
		}
		else if(view.getId()==R.id.buttonCall){
			intent=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+s12));
			chooser=Intent.createChooser(intent, "Choose app");
			startActivity(intent);
		}
		else if(view.getId()==R.id.buttonMsg){
			intent=new Intent(Intent.ACTION_VIEW,Uri.parse("sms:"+s12));
			chooser=Intent.createChooser(intent, "Choose app");
			startActivity(intent);
			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_display, menu);
		return true;
	}

}
