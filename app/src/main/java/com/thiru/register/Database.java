package com.thiru.register;

import java.util.ArrayList;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

	public Database(Context context) {
		super(context, "details", null, 1);
	}
	

	@Override
	public void onCreate(SQLiteDatabase db1) {
		db1.execSQL("create table if not exists user(name varchar(20) not null, email varchar(30) not null,phone varchar(20),password varchar(20))");	
	}

	@Override
	public void onUpgrade(SQLiteDatabase db1, int arg1, int arg2) {
		
		
	}


	public void regdata(String sn, String se, String sc, String sp) {
		
		SQLiteDatabase db1=getWritableDatabase();
		db1.execSQL("insert into user values('"+sn+"','"+se+"','"+sc+"','"+sp+"')");
	}


	public ArrayList<String> retdata() {
		
		SQLiteDatabase db1=getReadableDatabase();
		ArrayList<String> a=new ArrayList<String>();
		Cursor c=db1.rawQuery("select * from user",null);
		c.moveToFirst();
		if (c!=null) {
			do {
				int c1=c.getColumnIndex("name");
				String s=c.getString(c1);
				a.add(s);
			} while (c.moveToNext());
		}
		
		return a;
	}


	public String showDetails(String s1) {
		
		SQLiteDatabase db1=getReadableDatabase();
		Cursor cur=db1.rawQuery("select * from user where name ='"+s1+"'",null);
		cur.moveToFirst();
		String as = null;
		if(cur!=null){
			do{
				int ca1=cur.getColumnIndex("name");//name'ld be same as declared in db.
				int email=cur.getColumnIndex("email");
				int phn=cur.getColumnIndex("phone");
				int pass=cur.getColumnIndex("password");
				
				String str=cur.getString(ca1);
				String str1=cur.getString(email);
				String str2=cur.getString(phn);
				String str3=cur.getString(pass);
				 as=str+"#"+str1+"#"+str2+"#"+str3;
			} while (cur.moveToNext());
			System.out.println("will it retrieve correct details");
		}
		return as;
	}


	
	public int login(String s1, String s2) {
		int count=0;
		SQLiteDatabase db1=getReadableDatabase();
		Cursor cr=db1.rawQuery("select * from user where name ='"+s1+"' and password='"+s2+"'", null);
		cr.moveToFirst();
//		String get=null;
		
		if(cr!=null){
			do {
//				int nam=cr.getColumnIndex("name");
//				int mail=cr.getColumnIndex("email");
//				int pas=cr.getColumnIndex("password");
//				String st1=cr.getString(nam);
//				String st2=cr.getString(mail);
//				String st3=cr.getString(pas);
//				get=st1+"$"+st2+"$"+st3;
				count=cr.getCount();
				
			} while (cr.moveToNext());
		}
		return count;
	}


	public int isDuplicate(String sname) {
		int check=0;
		SQLiteDatabase database=getReadableDatabase();
		Cursor cur=database.rawQuery("select * from user where name like '"+sname+"'", null);
		cur.moveToFirst();
		if(cur!=null){
			do{
				check=cur.getCount();
			}while(cur.moveToNext());
		}
		
		return check;
	}

	
}
