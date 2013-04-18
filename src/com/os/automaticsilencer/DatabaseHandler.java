package com.os.automaticsilencer;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

public class DatabaseHandler {

	private static final int db_version = 1;
	private static final String db_name = "testschedule11101.db";
    static final String table_name01 = "schedule1";
	private static final String table_name02 = "schedule2";
	
	static final String KEY_ID = "_id";
	
	private static Context context;
	static SQLiteDatabase db;
	
	private SQLiteStatement insertStmt1;
	private SQLiteStatement insertStmt2;
	
	private static final String INSERT1 = "insert into "
			+ table_name01 + " (name, bdd, bmm, byy, bhh, bmn, edd, emm, eyy, ehh, emn, intentidb, intentide) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	private static final String INSERT2 = "insert into "
			+ table_name02 + " (name, bhh, bmn, ehh, emn, intentidb, intentide, mon, tue, wed, thu, fri, sat, sun) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	public DatabaseHandler(Context context) {
		DatabaseHandler.context = context;
		OpenHelper openHelper = new OpenHelper(DatabaseHandler.context);
		DatabaseHandler.db = openHelper.getWritableDatabase();
		this.insertStmt1 = DatabaseHandler.db.compileStatement(INSERT1);
		this.insertStmt2 = DatabaseHandler.db.compileStatement(INSERT2);

	}
		
		
	public long addschedulesingle(String name, String bdd, String bmm, String byy, String bhh, String bmn, String edd, String emm, String eyy, String ehh, String emn, String intentidb, String intentide) {
		this.insertStmt1.bindString(1, name);
		this.insertStmt1.bindString(2, bdd);
		this.insertStmt1.bindString(3, bmm);
		this.insertStmt1.bindString(4, byy);
		this.insertStmt1.bindString(5, bhh);
		this.insertStmt1.bindString(6, bmn);
		this.insertStmt1.bindString(7, edd);
		this.insertStmt1.bindString(8, emm);
		this.insertStmt1.bindString(9, eyy);
		this.insertStmt1.bindString(10, ehh);
		this.insertStmt1.bindString(11, emn);
		this.insertStmt1.bindString(12, intentidb);
		this.insertStmt1.bindString(13, intentide);
		
		return this.insertStmt1.executeInsert();

	}
	
	public long addschedule(String name, String bhh, String bmn, String ehh, String emn, String intentidb, String intentide,  String mon, String tue, String wed,  String thu, String fri, String sat, String sun) {
		this.insertStmt2.bindString(1, name);
		this.insertStmt2.bindString(2, bhh);
		this.insertStmt2.bindString(3, bmn);
		this.insertStmt2.bindString(4, ehh);
		this.insertStmt2.bindString(5, emn);
		this.insertStmt2.bindString(6, intentidb);
		this.insertStmt2.bindString(7, intentide);
		this.insertStmt2.bindString(8, mon);
		this.insertStmt2.bindString(9, tue);
		this.insertStmt2.bindString(10, wed);
		this.insertStmt2.bindString(11, thu);
		this.insertStmt2.bindString(12, fri);
		this.insertStmt2.bindString(13, sat);
		this.insertStmt2.bindString(14, sun);
		
		return this.insertStmt2.executeInsert();
	}
	public int getintentidb(int id){
		
	
		Cursor cursor = db.query(table_name01, new String[]{"_id", "intentidb"}, null, null, null, null, null);
		if(cursor.moveToFirst()){
			do{
				String[] b1 = new String[]{cursor.getString(0), cursor.getString(1)};
				int eventid = Integer.parseInt(cursor.getString(0));
				int intentid = Integer.parseInt(cursor.getString(1));
				if(id == eventid){
					return intentid;
				}
			}while(cursor.moveToNext());
			
		}
		return 0;
	}
	public int getintentide(int id){
		
		
		Cursor cursor = db.query(table_name01, new String[]{"_id", "intentide"}, null, null, null, null, null);
		if(cursor.moveToFirst()){
			do{
				String[] b1 = new String[]{cursor.getString(0), cursor.getString(1)};
				int eventid = Integer.parseInt(cursor.getString(0));
				int intentid = Integer.parseInt(cursor.getString(1));
				if(id == eventid){
					return intentid;
				}
			}while(cursor.moveToNext());
			
		}
		return 0;
	}
	public int getintentidb2(long id){
		
		
		Cursor cursor = db.query(table_name02, new String[]{"_id", "intentidb"}, null, null, null, null, null);
		if(cursor.moveToFirst()){
			do{
				String[] b1 = new String[]{cursor.getString(0), cursor.getString(1)};
				int eventid = Integer.parseInt(cursor.getString(0));
				int intentid = Integer.parseInt(cursor.getString(1));
				if(id == eventid){
					return intentid;
				}
			}while(cursor.moveToNext());
			
		}
		return 0;
	}
	public int getintentide2(long id){
		
		
		Cursor cursor = db.query(table_name02, new String[]{"_id", "intentide"}, null, null, null, null, null);
		if(cursor.moveToFirst()){
			do{
				String[] b1 = new String[]{cursor.getString(0), cursor.getString(1)};
				int eventid = Integer.parseInt(cursor.getString(0));
				int intentid = Integer.parseInt(cursor.getString(1));
				if(id == eventid){
					return intentid;
				}
			}while(cursor.moveToNext());
			
		}
		return 0;
	}
	// -------------------------------------------------------------------------------
	
	public boolean deletesingle(int id){
			
		return db.delete(table_name01, KEY_ID + " = " + id, null) > 0; 
		// db.delete(table_name01,  + " = " + id1, null); 
	}
	
	public boolean deleteschedule(int id){
		
		return db.delete(table_name02, KEY_ID + " = " + id, null) > 0; 
		// db.delete(table_name01,  + " = " + id1, null); 
	}
	//____________________________________________________________________________
	
	
	public Cursor fetchAllevents() {
		 
		  Cursor mCursor = db.query(table_name01, new String[] {KEY_ID, "name", "bdd", "bmm", "byy","bhh", "bmn", "edd", "emm", "eyy","ehh", "emn", "intentidb", "intentide"}, 
		    null, null, null, null, null);
		 
		  if (mCursor != null) {
		   mCursor.moveToFirst();
		  }
		  return mCursor;
		 }
	
	public Cursor fetchAlleventss() {
		 
		  Cursor mCursor = db.query(table_name02, new String[] {KEY_ID, "name","bhh", "bmn", "ehh", "emn", "intentidb", "intentide", "mon", "tue", "wed", "thu", "fri", "sat", "sun" }, 
		    null, null, null, null, null);
		 
		  if (mCursor != null) {
		   mCursor.moveToFirst();
		  }
		  return mCursor;
		 }

	//-------------------------------------------------------------------------------
	
	public class OpenHelper extends SQLiteOpenHelper{
		
			
		
		OpenHelper(Context context) {
			super(context, db_name, null, db_version);
				               
	    }
	    
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			
			db.execSQL("CREATE TABLE " + table_name01 + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT NOT NULL,bdd INTEGER NOT NULL, bmm INTEGER NOT NULL, byy INTEGER NOT NULL, bhh INTEGER NOT NULL, bmn INTEGER NOT NULL, edd INTEGER NOT NULL, emm INTEGER NOT NULL, eyy INTEGER NOT NULL, ehh INTEGER NOT NULL, emn INTEGER NOT NULL, intentidb INTEGER NOT NULL, intentide INTEGER NOT NULL)");
			
			db.execSQL("CREATE TABLE " + table_name02 + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT NOT NULL, bhh INTEGER NOT NULL, bmn INTEGER NOT NULL, ehh INTEGER NOT NULL, emn INTEGER NOT NULL, intentidb INTEGER NOT NULL, intentide INTEGER NOT NULL, mon TEXT, tue TEXT, wed TEXT, thu TEXT, fri TEXT, sat TEXT, sun TEXT)");
			
		}
	    
	   
		@Override
	    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	   
	        db.execSQL("DROP TABLE IF EXISTS " + table_name01);
	        onCreate(db);
	        db.execSQL("DROP TABLE IF EXISTS " + table_name02);
	        onCreate(db);
	      }



		
	    
		
	}
}
