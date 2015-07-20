package com.android.tirukkural.adapter;

import android.content.*;
import android.database.*;
import android.widget.*;
import com.android.tirukkural.*;

public class SectionChapterAdapter extends SimpleCursorTreeAdapter {

    private DatabaseHelper databaseHelper;

    public SectionChapterAdapter(Cursor cursor, Context context, DatabaseHelper databaseHelper) {
        super(context, cursor, R.layout.sections, new String[]{"name"}, new int[]{R.id.section_name}, R.layout.chapters, new String[]{"name"}, new int[]{R.id.chapter_name});
        this.databaseHelper = databaseHelper;
    }

    @Override
    protected Cursor getChildrenCursor(Cursor groupCursor) {
        int sectionColumnIndex = groupCursor.getColumnIndex("_id");
        String section = groupCursor.getString(sectionColumnIndex);
        return databaseHelper.readChapters(section);
    }

}
