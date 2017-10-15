package com.thiru.register;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class NameList extends Activity {
	Database db2;
	ArrayList<String> a1 = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_name_list);
		final ListView lv = (ListView) findViewById(R.id.listView1);
		db2 = new Database(getApplicationContext());
		a1 = db2.retdata();
		lv.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, a1));
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int click,
					long arg3) {
				String aa = lv.getItemAtPosition(click).toString();
				Intent it = new Intent(NameList.this, Display.class);
				it.putExtra("first", aa);
				// it.putExtra("second", email);
				// it.putExtra("third", phone);
				startActivity(it);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_name_list, menu);
		return true;
	}

}
