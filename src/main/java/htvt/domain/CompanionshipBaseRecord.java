package htvt.domain;

import android.content.ContentValues;
import android.database.Cursor;

public class CompanionshipBaseRecord implements BaseRecord {
    public static final String TABLE_NAME = "companionship";
    public static final String COMPANIONSHIP_ID = "companionship_id";
    public static final String DISTRICT_ID = "district_id";
    public static final String START_DATE = "start_date";

    private long companionshipId = 0;
    private long districtId = 0;
    private long startDate = 0;

    public static final String CREATE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
            + COMPANIONSHIP_ID + " INTEGER PRIMARY KEY, "
            + DISTRICT_ID + " INTEGER, "
            + START_DATE + " INTEGER, "
            + "FOREIGN KEY(" + DISTRICT_ID + ") REFERENCES "
            + DistrictBaseRecord.TABLE_NAME + "(" + DistrictBaseRecord.DISTRICT_ID + ") ON DELETE CASCADE"
            + ");";

    static final String[] ALL_KEYS = new String[] { COMPANIONSHIP_ID, DISTRICT_ID, START_DATE };

    public String[] getAllKeys() {
        return ALL_KEYS.clone();
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(COMPANIONSHIP_ID, companionshipId);
        values.put(DISTRICT_ID, districtId);
        values.put(START_DATE, startDate);
        return values;
    }

    public void setContent(ContentValues values) {
        companionshipId = values.getAsLong(COMPANIONSHIP_ID);
        districtId = values.getAsLong(DISTRICT_ID);
        startDate = values.getAsLong(START_DATE);
    }

    public void setContent(Cursor cursor) {
        companionshipId = cursor.getLong(cursor.getColumnIndex(COMPANIONSHIP_ID));
        districtId = cursor.getLong(cursor.getColumnIndex(DISTRICT_ID));
        startDate = cursor.getLong(cursor.getColumnIndex(START_DATE));
    }

    public long getCompanionshipId() { return companionshipId; }
    public void setCompanionshipId(long companionshipId) { this.companionshipId = companionshipId; }

    public long getDistrictId() { return districtId; }
    public void setDistrictId(long districtId) { this.districtId = districtId; }

    public long getStartDate() { return startDate; }
    public void setStartDate(long startDate) { this.startDate = startDate; }
}
