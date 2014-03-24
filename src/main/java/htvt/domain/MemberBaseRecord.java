package htvt.domain;

import android.content.ContentValues;
import android.database.Cursor;

public class MemberBaseRecord implements BaseRecord {
    public static final String TABLE_NAME = "member";
    public static final String DEFAULT_SORT_ORDER = "last_name DESC";
    public static final String INDIVIDUAL_ID = "individual_id";
    public static final String FORMATTED_NAME = "formatted_name";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String PRIESTHOOD_OFFICE = "priesthood_office";
    public static final String EMAIL = "email";
    public static final String GENDER = "gender";
    public static final String PHOTO_URL = "photo_url";
    public static final String PHONE = "phone";
    public static final String ISADULT = "is_adult";

    private long individualId = 0;
    private String formattedName = "";
    private String firstName = "";
    private String lastName = "";
    private String priesthoodOffice = "";
    private String email = "";
    private String gender = "";
    private String photoUrl = "";
    private String phone = "";
    private long isAdult = 0;

    public static final String CREATE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
            + INDIVIDUAL_ID + " INTEGER PRIMARY KEY, "
            + FORMATTED_NAME + " TEXT, "
            + FIRST_NAME + " TEXT, "
            + LAST_NAME + " TEXT,"
            + PRIESTHOOD_OFFICE + " TEXT, "
            + EMAIL + " TEXT, "
            + GENDER + " TEXT, "
            + PHOTO_URL + " TEXT, "
            + PHONE + " TEXT, "
            + ISADULT + " INTEGER"
            + ");";

    static final String[] ALL_KEYS = new String[] { INDIVIDUAL_ID, FORMATTED_NAME, FIRST_NAME, LAST_NAME,
            PRIESTHOOD_OFFICE, EMAIL, GENDER, PHOTO_URL, PHONE, ISADULT };

    public String[] getAllKeys() {
        return ALL_KEYS.clone();
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(INDIVIDUAL_ID, individualId);
        values.put(FORMATTED_NAME, formattedName);
        values.put(FIRST_NAME, firstName);
        values.put(LAST_NAME, lastName);
        values.put(PRIESTHOOD_OFFICE, priesthoodOffice);
        values.put(EMAIL, email);
        values.put(GENDER, gender);
        values.put(PHOTO_URL, photoUrl);
        values.put(PHONE, phone);
        values.put(ISADULT, isAdult);
        return values;
    }

    public void setContent(ContentValues values) {
        individualId = values.getAsLong(INDIVIDUAL_ID);
        formattedName = values.getAsString(FORMATTED_NAME);
        firstName = values.getAsString(FIRST_NAME);
        lastName = values.getAsString(LAST_NAME);
        priesthoodOffice = values.getAsString(PRIESTHOOD_OFFICE);
        email = values.getAsString(EMAIL);
        gender = values.getAsString(GENDER);
        photoUrl = values.getAsString(PHOTO_URL);
        phone = values.getAsString(PHONE);
        isAdult = values.getAsLong(ISADULT);
    }

    public void setContent(Cursor cursor) {
        individualId = cursor.getLong(cursor.getColumnIndex(INDIVIDUAL_ID));
        formattedName = cursor.getString(cursor.getColumnIndex(FORMATTED_NAME));
        firstName = cursor.getString(cursor.getColumnIndex(FIRST_NAME));
        lastName = cursor.getString(cursor.getColumnIndex(LAST_NAME));
        priesthoodOffice = cursor.getString(cursor.getColumnIndex(PRIESTHOOD_OFFICE));
        email = cursor.getString(cursor.getColumnIndex(EMAIL));
        gender = cursor.getString(cursor.getColumnIndex(GENDER));
        photoUrl = cursor.getString(cursor.getColumnIndex(PHOTO_URL));
        phone = cursor.getString(cursor.getColumnIndex(PHONE));
        isAdult = cursor.getLong(cursor.getColumnIndex(ISADULT));
    }

    public long getIndividualId() {
        return individualId;
    }
    public void setIndividualId(long individualId) {
        this.individualId = individualId;
    }

    public String getFormattedName() {
        return formattedName;
    }
    public void setFormattedName(String formattedName) {
        this.formattedName = formattedName;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPriesthoodOffice() {
        return priesthoodOffice;
    }
    public void setPriesthoodOffice(String priesthoodOffice) {
        this.priesthoodOffice = PRIESTHOOD_OFFICE;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getIsAdult() {
        return isAdult;
    }
    public void setIsAdult(long isAdult) {
        this.isAdult = isAdult;
    }
}
