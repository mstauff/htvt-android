package htvt.domain;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * MemberBaseRecord table contract
 */
public class MemberBaseRecord implements BaseRecord {

    /**
     * The table name offered by this provider
     */
    public static final String TABLE_NAME = "member";

    /**
     * The default sort order for this table
     */
    public static final String DEFAULT_SORT_ORDER = "formattedName DESC";

    /**
     * Column name for the lds.org individual id
     * <P>Type: INTEGER (long from System.curentTimeMillis())</P>
     */
    public static final String INDIVIDUAL_ID = "individual_id";
    private long individualId = 0;

    public static final String FAMILY_ID = "family_id";
    private long familyId = 0;

    public static final String FAMILY_MEMBER_TYPE = "family_member_type";
    private String getFamilyMemberType = "";

    public static final String FORMATTED_NAME = "formatted_name";
    private String formattedName = "";

    public static final String SUR_NAME = "sur_name";
    private String surName = "";

    public static final String GIVEN_NAME = "given_Name";
    private String givenName = "";

    public static final String PRIESTHOOD_OFFICE = "priesthood_office";
    private String priesthoodOffice = "";

    public static final String EMAIL = "email";
    private String email = "";

    public static final String PHOTO_URL = "photo_url";
    private String photoUrl = "";

    public static final String IMAGE_ID = "image_id";
    private String imageId = "";

    public static final String GENDER = "gender";
    private String gender = "";

    public static final String NOTES = "notes";
    private String notes = "";

    public static final String BIRTH_DATE = "birth_date";
    private String birthDate = "";

    public static final String PHONE = "phone";
    private String phone = "";

    public static final String CREATE_SQL =
        "CREATE TABLE IF NOT EXISTS "
        + MemberBaseRecord.TABLE_NAME + " ("
        + MemberBaseRecord.INDIVIDUAL_ID + " INTEGER PRIMARY KEY,"
        + MemberBaseRecord.FAMILY_ID + " INTEGER,"
        + MemberBaseRecord.FORMATTED_NAME + " TEXT,"
        + MemberBaseRecord.SUR_NAME + " TEXT,"
        + MemberBaseRecord.GIVEN_NAME + " TEXT,"
        + MemberBaseRecord.PRIESTHOOD_OFFICE + " TEXT,"
        + MemberBaseRecord.EMAIL + " TEXT,"
        + MemberBaseRecord.PHOTO_URL + " TEXT,"
        + MemberBaseRecord.IMAGE_ID + " TEXT,"
        + MemberBaseRecord.GENDER + " TEXT,"
        + MemberBaseRecord.NOTES + " TEXT,"
        + MemberBaseRecord.BIRTH_DATE + " TEXT,"
        + MemberBaseRecord.PHONE + " TEXT,"
        + "FOREIGN KEY(" + MemberBaseRecord.INDIVIDUAL_ID + ") REFERENCES "
        + FamilyBaseRecord.TABLE_NAME + "(" + FamilyBaseRecord.FAMILY_ID + ") "
        + ");";

    static final String[] ALL_KEYS = new String[] {
        INDIVIDUAL_ID,
        FAMILY_ID,
        FAMILY_MEMBER_TYPE,
        FORMATTED_NAME,
        SUR_NAME,
        GIVEN_NAME,
        PRIESTHOOD_OFFICE,
        EMAIL,
        PHOTO_URL,
        IMAGE_ID,
        GENDER,
        NOTES,
        BIRTH_DATE,
        PHONE
    };

    public String[] getAllKeys() {
        return ALL_KEYS.clone();
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(INDIVIDUAL_ID, individualId);
        values.put(FAMILY_ID, familyId);
        values.put(FAMILY_MEMBER_TYPE, getFamilyMemberType);
        values.put(FORMATTED_NAME, formattedName);
        values.put(SUR_NAME, surName);
        values.put(GIVEN_NAME, givenName);
        values.put(PRIESTHOOD_OFFICE, priesthoodOffice);
        values.put(EMAIL, email);
        values.put(PHOTO_URL, photoUrl);
        values.put(IMAGE_ID, imageId);
        values.put(GENDER, gender);
        values.put(NOTES, notes);
        values.put(BIRTH_DATE, birthDate);
        values.put(PHONE, phone);
        return values;
    }

    public void setContent(ContentValues values) {
        individualId = values.getAsLong(INDIVIDUAL_ID);
        familyId = values.getAsLong(FAMILY_ID);
        getFamilyMemberType = values.getAsString(FAMILY_MEMBER_TYPE);
        formattedName = values.getAsString(FORMATTED_NAME);
        surName = values.getAsString(SUR_NAME);
        givenName = values.getAsString(GIVEN_NAME);
        priesthoodOffice = values.getAsString(PRIESTHOOD_OFFICE);
        email = values.getAsString(EMAIL);
        photoUrl = values.getAsString(PHOTO_URL);
        imageId = values.getAsString(IMAGE_ID);
        gender = values.getAsString(GENDER);
        notes = values.getAsString(NOTES);
        birthDate = values.getAsString(BIRTH_DATE);
        phone = values.getAsString(PHONE);
    }

    public void setContent(Cursor cursor) {
        individualId = cursor.getLong(cursor.getColumnIndex(INDIVIDUAL_ID));
        familyId = cursor.getLong(cursor.getColumnIndex(FAMILY_ID));
        getFamilyMemberType = cursor.getString(cursor.getColumnIndex(FAMILY_MEMBER_TYPE));
        formattedName = cursor.getString(cursor.getColumnIndex(FORMATTED_NAME));
        surName = cursor.getString(cursor.getColumnIndex(SUR_NAME));
        givenName = cursor.getString(cursor.getColumnIndex(GIVEN_NAME));
        priesthoodOffice = cursor.getString(cursor.getColumnIndex(PRIESTHOOD_OFFICE));
        email = cursor.getString(cursor.getColumnIndex(EMAIL));
        photoUrl = cursor.getString(cursor.getColumnIndex(PHOTO_URL));
        imageId = cursor.getString(cursor.getColumnIndex(IMAGE_ID));
        gender = cursor.getString(cursor.getColumnIndex(GENDER));
        notes = cursor.getString(cursor.getColumnIndex(NOTES));
        birthDate = cursor.getString(cursor.getColumnIndex(BIRTH_DATE));
        phone = cursor.getString(cursor.getColumnIndex(PHONE));
    }

    public long getIndividualId() {
        return individualId;
    }
    public void setIndividualId(long individualId) {
        this.individualId = individualId;
    }

    public long getFamilyId() { return familyId; }
    public void setFamilyId(long familyId) { this.familyId = familyId; }

    public String getFamilyMemberType() { return getFamilyMemberType; }
    public void setFamilyMemberType(String familyMemberType) { this.getFamilyMemberType = familyMemberType; }

    public String getFormattedName() { return formattedName; }
    public void setFormattedName(String formattedName) { this.formattedName = formattedName; }

    public String getSurName() { return surName; }
    public void setSurName(String surName) { this.surName = surName; }

    public String getGivenName() { return givenName; }
    public void setGivenName(String givenName) { this.givenName = givenName; }

    public String getPriesthoodOffice() { return priesthoodOffice; }
    public void setPriesthoodOffice(String priesthoodOffice) { this.priesthoodOffice = priesthoodOffice; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhotoUrl() { return photoUrl; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }

    public String getImageId() { return imageId; }
    public void setImageId(String imageId) { this.imageId = imageId; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public String getBirthDate() { return birthDate; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}