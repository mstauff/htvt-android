package htvt.domain;

import android.content.ContentValues;
import android.database.Cursor;

public class AuxiliaryBaseRecord implements BaseRecord {
    public static final String TABLE_NAME = "auxiliary";
    public static final String AUXILIARY_ID = "auxiliary_id";
    public static final String UNIT_ID = "unit_id";
    public static final String AUXILIARY_TYPE = "auxiliary_type";

    private long auxiliaryId = 0;
    private long unitId = 0;
    private String auxiliaryType = "";

    public static final String CREATE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
            + AUXILIARY_ID + " INTEGER PRIMARY KEY, "
            + UNIT_ID + " INTEGER, "
            + AUXILIARY_TYPE + " TEXT"
            + ");";

    static final String[] ALL_KEYS = new String[] { AUXILIARY_ID, UNIT_ID, AUXILIARY_TYPE };

    public String[] getAllKeys() {
        return ALL_KEYS.clone();
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(AUXILIARY_ID, auxiliaryId);
        values.put(UNIT_ID, unitId);
        values.put(AUXILIARY_TYPE, auxiliaryType);
        return values;
    }

    public void setContent(ContentValues values) {
        auxiliaryId = values.getAsLong(AUXILIARY_ID);
        unitId = values.getAsLong(UNIT_ID);
        auxiliaryType = values.getAsString(AUXILIARY_TYPE);
    }

    public void setContent(Cursor cursor) {
        auxiliaryId = cursor.getLong(cursor.getColumnIndex(AUXILIARY_ID));
        unitId = cursor.getLong(cursor.getColumnIndex(UNIT_ID));
        auxiliaryType = cursor.getString(cursor.getColumnIndex(AUXILIARY_TYPE));
    }

    public long getAuxiliaryId() { return auxiliaryId; }
    public void setAuxiliaryId(long auxiliaryId) { this.auxiliaryId = auxiliaryId; }

    public long getUnitId() { return unitId; }
    public void setUnitId(long unitId) { this.unitId = unitId; }

    public String getAuxiliaryType() { return auxiliaryType; }
    public void setAuxiliaryType(String auxiliaryType) { this.auxiliaryType = auxiliaryType; }
}
