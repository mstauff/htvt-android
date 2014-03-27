package htvt.domain;

import android.content.ContentValues;
import android.database.Cursor;

public class ChildBaseRecord implements BaseRecord {
    public static final String TABLE_NAME = "child";
    public static final String FAMILY_ID = "family_id";
    public static final String MEMBER_ID = "member_id";

    private long familyId = 0;
    private long memberId = 0;

    public static final String CREATE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
            + FAMILY_ID + " INTEGER, "
            + MEMBER_ID + " INTEGER, "
            + "FOREIGN KEY(" + FAMILY_ID + ") REFERENCES "
            + FamilyBaseRecord.TABLE_NAME + "(" + FamilyBaseRecord.ID + ") ON DELETE CASCADE, "
            + "FOREIGN KEY(" + MEMBER_ID + ") REFERENCES "
            + MemberBaseRecord.TABLE_NAME + "(" + MemberBaseRecord.ID + ") ON DELETE CASCADE"
            + ");";

    static final String[] ALL_KEYS = new String[] { FAMILY_ID, MEMBER_ID };

    public String[] getAllKeys() {
        return ALL_KEYS.clone();
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(FAMILY_ID, familyId);
        values.put(MEMBER_ID, memberId);
        return values;
    }

    public void setContent(ContentValues values) {
        familyId = values.getAsLong(FAMILY_ID);
        memberId = values.getAsLong(MEMBER_ID);
    }

    public void setContent(Cursor cursor) {
        familyId = cursor.getInt(cursor.getColumnIndex(FAMILY_ID));
        memberId = cursor.getInt(cursor.getColumnIndex(MEMBER_ID));
    }

    public long getFamilyId() {
        return familyId;
    }
    public void setFamilyId(long familyId) {
        this.familyId = familyId;
    }

    public long getMemberId() {
        return memberId;
    }
    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }
}
