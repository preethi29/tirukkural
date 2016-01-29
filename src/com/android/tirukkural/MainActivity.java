package com.android.tirukkural;

import android.annotation.*;
import android.app.*;
import android.content.Intent;
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

        getExpandableListView().setGroupIndicator(null);
        getExpandableListView().setTextFilterEnabled(true);
        getExpandableListView().setOnChildClickListener(this);

        SectionChapterAdapter adapter = new SectionChapterAdapter(mDbHelper.readSections(), this, mDbHelper);

        adapter.setViewBinder(new SimpleCursorTreeAdapter.ViewBinder() {
            Typeface tamilFont = Typeface.createFromAsset(getAssets(), "fonts/tamil3.ttf");

            @Override
            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                TextView textView = (TextView) view;
                textView.setTypeface(tamilFont);
                textView.setTypeface(tamilFont);
                return false;
            }
        });

        setListAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition,
                                int childPosition, long id) {
        Intent i = new Intent(this,CoupletActivity.class);
        i.putExtra("chapter", id);
        startActivity(i);
        return  true;
    }
}
