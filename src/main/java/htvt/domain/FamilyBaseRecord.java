package htvt.domain;

import android.content.ContentValues;
import android.database.Cursor;

public class FamilyBaseRecord implements BaseRecord {
    public static final String TABLE_NAME = "family";
    public static final String HEAD_OF_HOUSE_ID = "head_of_house_id";
    public static final String SPOUSE_ID = "spouse_id";
    public static final String FORMATTED_COUPLE_NAME = "formatted_couple_name";
    public static final String PHONE = "phone";
    public static final String HOME_ADDRESS = "home_address";
    public static final String EMAIL = "email";

    private long headOfHouseId = 0;
    private long spouseId = 0;
    private String formattedCoupleName = "";
    private String phone = "";
    private String homeAddress = "";
    private String email = "";

    public static final String CREATE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
            + HEAD_OF_HOUSE_ID + " INTEGER PRIMARY KEY, "
            + SPOUSE_ID + " INTEGER, "
            + FORMATTED_COUPLE_NAME + " STRING, "
            + PHONE + " STRING, "
            + HOME_ADDRESS + " STRING, "
            + EMAIL + " STRING, "
            + "FOREIGN KEY(" + HEAD_OF_HOUSE_ID + ") REFERENCES "
            + MemberBaseRecord.TABLE_NAME + "(" + MemberBaseRecord.INDIVIDUAL_ID + "), "
            + "FOREIGN KEY(" + SPOUSE_ID + ") REFERENCES "
            + MemberBaseRecord.TABLE_NAME + "(" + MemberBaseRecord.INDIVIDUAL_ID + ")"
            + ");";

    static final String[] ALL_KEYS = new String[] { HEAD_OF_HOUSE_ID, SPOUSE_ID,
            FORMATTED_COUPLE_NAME, PHONE, HOME_ADDRESS, EMAIL};

    public String[] getAllKeys() {
        return ALL_KEYS.clone();
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(HEAD_OF_HOUSE_ID, headOfHouseId);
        values.put(SPOUSE_ID, spouseId);
        values.put(FORMATTED_COUPLE_NAME, formattedCoupleName);
        values.put(PHONE, phone);
        values.put(HOME_ADDRESS, homeAddress);
        values.put(EMAIL, email);
        return values;
    }

    public void setContent(ContentValues values) {
        headOfHouseId = values.getAsLong(HEAD_OF_HOUSE_ID);
        spouseId = values.getAsLong(SPOUSE_ID);
        formattedCoupleName = values.getAsString(FORMATTED_COUPLE_NAME);
        phone = values.getAsString(PHONE);
        homeAddress = values.getAsString(HOME_ADDRESS);
        email = values.getAsString(EMAIL);
    }

    public void setContent(Cursor cursor) {
        headOfHouseId = cursor.getInt(cursor.getColumnIndex(HEAD_OF_HOUSE_ID));
        spouseId = cursor.getInt(cursor.getColumnIndex(SPOUSE_ID));
        formattedCoupleName = cursor.getString(cursor.getColumnIndex(FORMATTED_COUPLE_NAME));
        phone = cursor.getString(cursor.getColumnIndex(PHONE));
        homeAddress = cursor.getString(cursor.getColumnIndex(HOME_ADDRESS));
        email = cursor.getString(cursor.getColumnIndex(EMAIL));
    }

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

    public String getHomeAddress() {
        return homeAddress;
    }
    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
