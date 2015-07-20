package com.android.tirukkural;

import android.annotation.*;
import android.app.*;
import android.database.*;
import android.graphics.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import com.android.tirukkural.adapter.*;

import java.io.*;
import java.sql.SQLException;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends ExpandableListActivity {

    DatabaseHelper mDbHelper;
    ExpandableListAdapter expandableListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDbHelper = new DatabaseHelper(this);
        try {
            mDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }

        try {
            mDbHelper.openDataBase();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
//        Cursor kuralCursor = readKural();
//        mAdapter = new SimpleCursorAdapter(this, R.layout.couplets, kuralCursor, new String[]{"_id", "line1", "line2"},
//                new int[]{0, R.id.line1, R.id.line2}, 0);

        getExpandableListView().setGroupIndicator(null);
        getExpandableListView().setTextFilterEnabled(true);
        SectionChapterAdapter adapter = new SectionChapterAdapter(mDbHelper.readSections(), this, mDbHelper);

        adapter.setViewBinder(new SimpleCursorTreeAdapter.ViewBinder() {
            Typeface tamilFont = Typeface.createFromAsset(getAssets(), "fonts/tamil3.ttf");

            @Override
            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                TextView textView = (TextView) view;
                textView.setTypeface(tamilFont);
                return false;
            }
        });

        setListAdapter(adapter);
//
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
