package htvt.domain;

import android.content.ContentValues;
import android.database.Cursor;

public class ChildBaseRecord implements BaseRecord {
    public static final String TABLE_NAME = "child";
    public static final String HEAD_OF_HOUSE_ID = "head_of_house_id";
    public static final String INDIVIDUAL_ID = "individual_id";

    private long headOfHouseId = 0;
    private long individualId = 0;

    public static final String CREATE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
            + HEAD_OF_HOUSE_ID + " INTEGER, "
            + INDIVIDUAL_ID + " INTEGER, "
            + "FOREIGN KEY(" + HEAD_OF_HOUSE_ID + ") REFERENCES "
            + FamilyBaseRecord.TABLE_NAME + "(" + FamilyBaseRecord.HEAD_OF_HOUSE_ID + ") ON DELETE CASCADE"
            + ");";

    static final String[] ALL_KEYS = new String[] { HEAD_OF_HOUSE_ID, INDIVIDUAL_ID };

    public String[] getAllKeys() {
        return ALL_KEYS.clone();
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(HEAD_OF_HOUSE_ID, headOfHouseId);
        values.put(INDIVIDUAL_ID, individualId);
        return values;
    }

    public void setContent(ContentValues values) {
        headOfHouseId = values.getAsLong(HEAD_OF_HOUSE_ID);
        individualId = values.getAsLong(INDIVIDUAL_ID);
    }

    public void setContent(Cursor cursor) {
        headOfHouseId = cursor.getInt(cursor.getColumnIndex(HEAD_OF_HOUSE_ID));
        individualId = cursor.getInt(cursor.getColumnIndex(INDIVIDUAL_ID));
    }

    public long getHeadOfHouseId() {
        return headOfHouseId;
    }
    public void setHeadOfHouseId(long headOfHouseId) {
        this.headOfHouseId = headOfHouseId;
    }

    public long getIndividualId() {
        return individualId;
    }
    public void setIndividualId(long individualId) {
        this.individualId = individualId;
    }
}
