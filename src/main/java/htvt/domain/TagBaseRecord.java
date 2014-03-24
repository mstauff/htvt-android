package htvt.domain;

import android.content.ContentValues;
import android.database.Cursor;

public class TagBaseRecord implements BaseRecord {
    public static final String TABLE_NAME = "tag";
    public static final String INDIVIDUAL_ID = "individual_id";
    public static final String TAG_ID = "tag_id";

    private long individualId = 0;
    private String tagId = "";

    public static final String CREATE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
            + INDIVIDUAL_ID + " INTEGER, "
            + TAG_ID + " STRING, "
            + "FOREIGN KEY(" + INDIVIDUAL_ID + ") REFERENCES "
            + MemberBaseRecord.TABLE_NAME + "(" + MemberBaseRecord.INDIVIDUAL_ID + ") ON DELETE CASCADE"
            + ");";

    static final String[] ALL_KEYS = new String[] { INDIVIDUAL_ID, TAG_ID };

    public String[] getAllKeys() {
        return ALL_KEYS.clone();
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(INDIVIDUAL_ID, individualId);
        values.put(TAG_ID, tagId);
        return values;
    }

    public void setContent(ContentValues values) {
        individualId = values.getAsLong(INDIVIDUAL_ID);
        tagId = values.getAsString(TAG_ID);
    }

    public void setContent(Cursor cursor) {
        individualId = cursor.getInt(cursor.getColumnIndex(INDIVIDUAL_ID));
        tagId = cursor.getString(cursor.getColumnIndex(TAG_ID));
    }

    public long getIndividualId() { return individualId; }
    public void setIndividualId(long individualId) { this.individualId = individualId; }

    public String getTagId() { return tagId; }
    public void setTagId(String tagId) { this.tagId = tagId; }
}
