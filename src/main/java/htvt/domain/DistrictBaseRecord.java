package htvt.domain;

import android.content.ContentValues;
import android.database.Cursor;

public class DistrictBaseRecord implements BaseRecord {
    public static final String TABLE_NAME = "district";
    public static final String DISTRICT_ID = "district_id";
    public static final String NAME = "name";
    public static final String AUXILIARY_ID = "auxiliary_id";
    public static final String DISTRICT_LEADER_ID = "district_leader_id";

    private long districtId = 0;
    private String name = "";
    private long auxiliaryId = 0;
    private long districtLeaderId = 0;

    public static final String CREATE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
            + DISTRICT_ID + " INTEGER PRIMARY KEY, "
            + NAME + " STRING, "
            + AUXILIARY_ID + " INTEGER, "
            + DISTRICT_LEADER_ID + " INTEGER, "
            + "FOREIGN KEY(" + AUXILIARY_ID + ") REFERENCES "
            + AuxiliaryBaseRecord.TABLE_NAME + "(" + AuxiliaryBaseRecord.AUXILIARY_ID + ") ON DELETE CASCADE"
            + ");";

    static final String[] ALL_KEYS = new String[] { DISTRICT_ID, NAME, AUXILIARY_ID, DISTRICT_LEADER_ID };

    public String[] getAllKeys() {
        return ALL_KEYS.clone();
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(DISTRICT_ID, districtId);
        values.put(NAME, name);
        values.put(AUXILIARY_ID, auxiliaryId);
        values.put(DISTRICT_LEADER_ID, districtLeaderId);
        return values;
    }

    public void setContent(ContentValues values) {
        districtId = values.getAsLong(DISTRICT_ID);
        name = values.getAsString(NAME);
        auxiliaryId = values.getAsLong(AUXILIARY_ID);
        districtLeaderId = values.getAsLong(DISTRICT_LEADER_ID);
    }

    public void setContent(Cursor cursor) {
        districtId = cursor.getLong(cursor.getColumnIndex(DISTRICT_ID));
        name = cursor.getString(cursor.getColumnIndex(NAME));
        auxiliaryId = cursor.getLong(cursor.getColumnIndex(AUXILIARY_ID));
        districtLeaderId = cursor.getLong(cursor.getColumnIndex(DISTRICT_LEADER_ID));
    }

    public long getDistrictId() { return districtId; }
    public void setDistrictId(long districtId) { this.districtId = districtId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public long getAuxiliaryId() { return auxiliaryId; }
    public void setAuxiliaryId(long auxiliaryId) { this.auxiliaryId = auxiliaryId; }

    public long getDistrictLeaderId() { return districtLeaderId; }
    public void setDistrictLeaderId(long districtLeaderId) { this.districtLeaderId = districtLeaderId; }
}
