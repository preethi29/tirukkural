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

        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this,
                R.layout.couplets,
                coupletsCursor,
                new String[]{"line1", "line2", "ta_meaning", "en_meaning"},
                new int[]{R.id.line1, R.id.line2, R.id.ta_meaning, R.id.en_meaning},
                0);

        simpleCursorAdapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
            Typeface boldFont = Typeface.createFromAsset(getAssets(), "fonts/TAC-Kambar-B.ttf");
            Typeface normalFont = Typeface.createFromAsset(getAssets(), "fonts/TAC-Kambar.ttf");

            @Override
            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                TextView textView = (TextView) view;
                switch(textView.getId()){
                    case R.id.line1:
                    case R.id.line2:
                        textView.setTypeface(boldFont);
                        break;
                    case R.id.ta_meaning:
                    case R.id.en_meaning:
                        textView.setTypeface(normalFont);
                        break;
                    default:
                        textView.setTypeface(normalFont);
                }
                return false;
            }
        });
        setListAdapter(simpleCursorAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("destroying");
    }
}
