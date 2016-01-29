package com.android.tirukkural;

import android.app.ListActivity;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class CoupletActivity extends ListActivity {


    private DatabaseHelper mDbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDbHelper = new DatabaseHelper(this);
        long chapterIndex = getIntent().getExtras().getLong("chapter", 0);
        Cursor coupletsCursor = mDbHelper.readCouplets(String.valueOf(chapterIndex));

        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.couplets, coupletsCursor,
                new String[]{"line1", "line2","ta_meaning", "en_meaning"}, new int[]{R.id.line1, R.id.line2, R.id.ta_meaning, R.id.en_meaning}, 0);
        simpleCursorAdapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
            Typeface tamilFont = Typeface.createFromAsset(getAssets(), "fonts/tamil3.ttf");

            @Override
            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                TextView textView = (TextView) view;
                textView.setTypeface(tamilFont);
                textView.setTypeface(tamilFont);
                return false;
            }
        });

        setListAdapter(simpleCursorAdapter);
    }


}
