package htvt.domain;

import android.content.ContentValues;
import android.database.Cursor;

public class TeacherBaseRecord implements BaseRecord {
    public static final String TABLE_NAME = "teacher";
    public static final String TEACHER_ID = "teacher_id";
    public static final String COMPANIONSHIP_ID = "companionship_id";
    public static final String INDIVIDUAL_ID = "individual_id";
    public static final String CUSTOM_NAME = "custom_name";
    public static final String CUSTOM_CONTACT = "custom_contact";

    private long teacherId = 0;
    private long companionshipId = 0;
    private long individualId = 0;
    private String customName = "";
    private String customContact = "";

    public static final String CREATE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
            + TEACHER_ID + " INTEGER PRIMARY KEY, "
            + COMPANIONSHIP_ID + " INTEGER, "
            + INDIVIDUAL_ID + " INTEGER, "
            + CUSTOM_NAME + " STRING, "
            + CUSTOM_CONTACT + " STRING, "
            + "FOREIGN KEY(" + COMPANIONSHIP_ID + ") REFERENCES "
            + CompanionshipBaseRecord.TABLE_NAME + "(" + CompanionshipBaseRecord.COMPANIONSHIP_ID + ") ON DELETE CASCADE"
            + ");";

    static final String[] ALL_KEYS = new String[] { TEACHER_ID, COMPANIONSHIP_ID, INDIVIDUAL_ID, CUSTOM_NAME, CUSTOM_CONTACT };

    public String[] getAllKeys() {
        return ALL_KEYS.clone();
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(TEACHER_ID, teacherId);
        values.put(COMPANIONSHIP_ID, companionshipId);
        values.put(INDIVIDUAL_ID, individualId);
        values.put(CUSTOM_NAME, customName);
        values.put(CUSTOM_CONTACT, customContact);
        return values;
    }

    public void setContent(ContentValues values) {
        teacherId = values.getAsLong(TEACHER_ID);
        companionshipId = values.getAsLong(COMPANIONSHIP_ID);
        individualId = values.getAsLong(INDIVIDUAL_ID);
        customName = values.getAsString(CUSTOM_NAME);
        customContact = values.getAsString(CUSTOM_CONTACT);
    }

    public void setContent(Cursor cursor) {
        teacherId = cursor.getLong(cursor.getColumnIndex(TEACHER_ID));
        companionshipId = cursor.getLong(cursor.getColumnIndex(COMPANIONSHIP_ID));
        individualId = cursor.getLong(cursor.getColumnIndex(INDIVIDUAL_ID));
        customName = cursor.getString(cursor.getColumnIndex(CUSTOM_NAME));
        customContact = cursor.getString(cursor.getColumnIndex(CUSTOM_CONTACT));
    }

    public long getTeacherId() { return teacherId; }
    public void setTeacherId(long teacherId) { this.teacherId = teacherId; }

    public long getCompanionshipId() { return companionshipId; }
    public void setCompanionshipId(long companionshipId) { this.companionshipId = companionshipId; }

    public long getIndividualId() { return individualId; }
    public void setIndividualId(long individualId) { this.individualId = individualId; }

    public String getCustomName() { return customName; }
    public void setCustomName(String customName) { this.customName = customName; }

    public String getCustomContact() { return customContact; }
    public void setCustomContact(String customContact) { this.customContact = customContact; }
}
