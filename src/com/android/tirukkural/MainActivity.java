package com.android.tirukkural;

import android.annotation.TargetApi;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends ListActivity {

    DatabaseOpenHelper mDbHelper;
    SimpleCursorAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	mDbHelper = new DatabaseOpenHelper(this);
	Cursor kuralCursor = readKural();
	mAdapter = new SimpleCursorAdapter(this, R.layout.list_layout, kuralCursor, new String[]{DatabaseOpenHelper.LINE_1, DatabaseOpenHelper.LINE_2},
		new int[] {
		R.id.line1, R.id.line2 }, 0);

	setListAdapter(mAdapter);

    }

    private Cursor readKural() {
	return mDbHelper.getWritableDatabase().query(DatabaseOpenHelper.TABLE_NAME, DatabaseOpenHelper.columns, null, new String[] {},
		null, null, null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	getMenuInflater().inflate(R.menu.main, menu);
	return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
	int id = item.getItemId();
	if (id == R.id.action_settings) {
	    return true;
	}
	return super.onOptionsItemSelected(item);
    }
}
