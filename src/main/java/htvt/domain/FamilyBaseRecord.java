package htvt.domain;

import android.content.ContentValues;
import android.database.Cursor;

public class FamilyBaseRecord implements BaseRecord {
    public static final String TABLE_NAME = "family";
    public static final String ID = "id";
    public static final String UNIT_ID = "unit_id";
    public static final String HEAD_OF_HOUSE_ID = "head_of_house_id";
    public static final String SPOUSE_ID = "spouse_id";
    public static final String FORMATTED_COUPLE_NAME = "formatted_couple_name";
    public static final String PHONE = "phone";
    public static final String STREET = "streetAddress";
    public static final String CITY = "city";
    public static final String STATE = "state";
    public static final String POSTAL = "postal";
    public static final String EMAIL = "email";

    private long id = 0;
    private long unitId = 0;
    private long headOfHouseId = 0;
    private long spouseId = 0;
    private String formattedCoupleName = "";
    private String phone = "";
    private String street = "";
    private String city = "";
    private String state = "";
    private String postal = "";
    private String email = "";

    public static final String CREATE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
            + ID + " INTEGER PRIMARY KEY, "
            + UNIT_ID + " INTEGER, "
            + HEAD_OF_HOUSE_ID + " INTEGER, "
            + SPOUSE_ID + " INTEGER, "
            + FORMATTED_COUPLE_NAME + " STRING, "
            + PHONE + " STRING, "
            + STREET + " STRING, "
            + CITY + " STRING, "
            + STATE + " STRING, "
            + POSTAL + " STRING, "
            + EMAIL + " STRING, "
            + "FOREIGN KEY(" + HEAD_OF_HOUSE_ID + ") REFERENCES "
            + MemberBaseRecord.TABLE_NAME + "(" + MemberBaseRecord.ID + "), "
            + "FOREIGN KEY(" + SPOUSE_ID + ") REFERENCES "
            + MemberBaseRecord.TABLE_NAME + "(" + MemberBaseRecord.ID + ")"
            + ");";

    static final String[] ALL_KEYS = new String[] { ID, UNIT_ID, HEAD_OF_HOUSE_ID, SPOUSE_ID,
            FORMATTED_COUPLE_NAME, PHONE, STREET, CITY, STATE, POSTAL, EMAIL};

    public String[] getAllKeys() {
        return ALL_KEYS.clone();
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(ID, id);
        values.put(UNIT_ID, unitId);
        values.put(HEAD_OF_HOUSE_ID, headOfHouseId);
        values.put(SPOUSE_ID, spouseId);
        values.put(FORMATTED_COUPLE_NAME, formattedCoupleName);
        values.put(PHONE, phone);
        values.put(STREET, street);
        values.put(CITY, city);
        values.put(STATE, state);
        values.put(POSTAL, postal);
        values.put(EMAIL, email);
        return values;
    }

    public void setContent(ContentValues values) {
        id = values.getAsLong(ID);
        unitId = values.getAsLong(UNIT_ID);
        headOfHouseId = values.getAsLong(HEAD_OF_HOUSE_ID);
        spouseId = values.getAsLong(SPOUSE_ID);
        formattedCoupleName = values.getAsString(FORMATTED_COUPLE_NAME);
        phone = values.getAsString(PHONE);
        street = values.getAsString(STREET);
        city = values.getAsString(CITY);
        state = values.getAsString(STATE);
        postal = values.getAsString(POSTAL);
        email = values.getAsString(EMAIL);
    }

    public void setContent(Cursor cursor) {
        id = cursor.getLong(cursor.getColumnIndex(ID));
        unitId = cursor.getLong(cursor.getColumnIndex(UNIT_ID));
        headOfHouseId = cursor.getInt(cursor.getColumnIndex(HEAD_OF_HOUSE_ID));
        spouseId = cursor.getInt(cursor.getColumnIndex(SPOUSE_ID));
        formattedCoupleName = cursor.getString(cursor.getColumnIndex(FORMATTED_COUPLE_NAME));
        phone = cursor.getString(cursor.getColumnIndex(PHONE));
        street = cursor.getString(cursor.getColumnIndex(STREET));
        city = cursor.getString(cursor.getColumnIndex(CITY));
        state = cursor.getString(cursor.getColumnIndex(STATE));
        postal = cursor.getString(cursor.getColumnIndex(POSTAL));
        email = cursor.getString(cursor.getColumnIndex(EMAIL));
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public long getUnitId() { return unitId; }
    public void setUnitId(long unitId) { this.unitId = unitId; }

    public long getHeadOfHouseId() {
        return headOfHouseId;
    }
    public void setHeadOfHouseId(long headOfHouseId) {
        this.headOfHouseId = headOfHouseId;
    }

    public long getSpouseId() {
        return spouseId;
    }
    public void setSpouseId(long spouseId) {
        this.spouseId = spouseId;
    }

    public String getFormattedCoupleName() {
        return formattedCoupleName;
    }
    public void setFormattedCoupleName(String formattedCoupleName) {
        this.formattedCoupleName = formattedCoupleName;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    public String getPostal() {
        return postal;
    }
    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}