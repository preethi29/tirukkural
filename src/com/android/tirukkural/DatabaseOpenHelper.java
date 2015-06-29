package com.android.tirukkural;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
	
	final static String TABLE_NAME = "kural";
	final static String LINE_1 = "line1";
	final static String LINE_2 = "line2";
	final static String TRANSLATION = "translation";
	final static String _ID = "_id";
	final static String[] columns = { _ID, LINE_1 , LINE_2, TRANSLATION};

	final private static String CREATE_CMD =

	"CREATE TABLE kural (" + _ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ LINE_1 + " TEXT NOT NULL,"
	+ LINE_2 + " TEXT NOT NULL,"
	+ TRANSLATION + " TEXT )";

	final private static String NAME = "tirukkkural";
	final private static Integer VERSION = 1;
	final private Context mContext;

	public DatabaseOpenHelper(Context context) {
		super(context, NAME, null, VERSION);
		this.mContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_CMD);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// N/A
	}

	void deleteDatabase() {
		mContext.deleteDatabase(NAME);
	}
}
