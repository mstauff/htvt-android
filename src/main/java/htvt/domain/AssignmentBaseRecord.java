package htvt.domain;

import android.content.ContentValues;
import android.database.Cursor;

public class AssignmentBaseRecord implements BaseRecord {
    public static final String TABLE_NAME = "assignment";
    public static final String ASSIGNMENT_ID = "assignment_id";
    public static final String COMPANIONSHIP_ID = "companionship_id";
    public static final String INDIVIDUAL_ID = "individual_id";
    public static final String CUSTOM_NAME = "custom_name";
    public static final String CUSTOM_CONTACT = "custom_contact";

    private long assignmentId = 0;
    private long companionshipId = 0;
    private long individualId = 0;
    private String customName = "";
    private String customContact = "";

    public static final String CREATE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
            + ASSIGNMENT_ID + " INTEGER PRIMARY KEY, "
            + COMPANIONSHIP_ID + " INTEGER, "
            + INDIVIDUAL_ID + " INTEGER, "
            + CUSTOM_NAME + " STRING, "
            + CUSTOM_CONTACT + " STRING, "
            + "FOREIGN KEY(" + COMPANIONSHIP_ID + ") REFERENCES "
            + CompanionshipBaseRecord.TABLE_NAME + "(" + CompanionshipBaseRecord.COMPANIONSHIP_ID + ") ON DELETE CASCADE"
            + ");";

    static final String[] ALL_KEYS = new String[] { ASSIGNMENT_ID, COMPANIONSHIP_ID, INDIVIDUAL_ID,
            CUSTOM_NAME, CUSTOM_CONTACT };

    public String[] getAllKeys() {
        return ALL_KEYS.clone();
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(ASSIGNMENT_ID, assignmentId);
        values.put(COMPANIONSHIP_ID, companionshipId);
        values.put(INDIVIDUAL_ID, individualId);
        values.put(CUSTOM_NAME, customName);
        values.put(CUSTOM_CONTACT, customContact);
        return values;
    }

    public void setContent(ContentValues values) {
        assignmentId = values.getAsLong(ASSIGNMENT_ID);
        companionshipId = values.getAsLong(COMPANIONSHIP_ID);
        individualId = values.getAsLong(INDIVIDUAL_ID);
        customName = values.getAsString(CUSTOM_NAME);
        customContact = values.getAsString(CUSTOM_CONTACT);
    }

    public void setContent(Cursor cursor) {
        assignmentId = cursor.getLong(cursor.getColumnIndex(ASSIGNMENT_ID));
        companionshipId = cursor.getLong(cursor.getColumnIndex(COMPANIONSHIP_ID));
        individualId = cursor.getLong(cursor.getColumnIndex(INDIVIDUAL_ID));
        customName = cursor.getString(cursor.getColumnIndex(CUSTOM_NAME));
        customContact = cursor.getString(cursor.getColumnIndex(CUSTOM_CONTACT));
    }

    public long getAssignmentId() { return assignmentId; }
    public void setAssignmentId(long assignmentId) { this.assignmentId = assignmentId; }

    public long getCompanionshipId() { return companionshipId; }
    public void setCompanionshipId(long companionshipId) { this.companionshipId = companionshipId; }

    public long getIndividualId() { return individualId; }
    public void setIndividualId(long individualId) { this.individualId = individualId; }

    public String getCustomName() { return customName; }
    public void setCustomName(String customName) { this.customName = customName; }

    public String getCustomContact() { return customContact; }
    public void setCustomContact(String customContact) { this.customContact = customContact; }
}
