package htvt.domain;

import android.content.ContentValues;
import android.database.Cursor;

public class TagBaseRecord implements BaseRecord {
    public static final String TABLE_NAME = "tag";
    public static final String MEMBER_ID = "member_id";
    public static final String TAG_ID = "tag_id";

    private long memberId = 0;
    private String tagId = "";

    public static final String CREATE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
            + MEMBER_ID + " INTEGER, "
            + TAG_ID + " STRING, "
            + "FOREIGN KEY(" + MEMBER_ID + ") REFERENCES "
            + MemberBaseRecord.TABLE_NAME + "(" + MemberBaseRecord.ID + ") ON DELETE CASCADE"
            + ");";

    static final String[] ALL_KEYS = new String[] { MEMBER_ID, TAG_ID };

    public String[] getAllKeys() {
        return ALL_KEYS.clone();
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(MEMBER_ID, memberId);
        values.put(TAG_ID, tagId);
        return values;
    }

    public void setContent(ContentValues values) {
        memberId = values.getAsLong(MEMBER_ID);
        tagId = values.getAsString(TAG_ID);
    }

    public void setContent(Cursor cursor) {
        memberId = cursor.getInt(cursor.getColumnIndex(MEMBER_ID));
        tagId = cursor.getString(cursor.getColumnIndex(TAG_ID));
    }

    public long getMemberId() { return memberId; }
    public void setMemberId(long memberId) { this.memberId = memberId; }

    public String getTagId() { return tagId; }
    public void setTagId(String tagId) { this.tagId = tagId; }
}
