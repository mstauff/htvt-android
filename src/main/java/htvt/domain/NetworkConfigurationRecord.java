package htvt.domain;

import android.content.ContentValues;
import android.database.Cursor;

public class NetworkConfigurationRecord implements BaseRecord {

    public static final String TABLE_NAME = "network_configuration";
    public static final String URL_KEY = "key";
    public static final String URL = "url";

    private String urlKey = "";
    private String Url = "";

    public static final String CREATE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
            + URL_KEY + " TEXT, "
            + URL + " TEXT"
            + ");";

    static final String[] ALL_KEYS = new String[] {URL_KEY, URL};

    public String[] getAllKeys() {
        return ALL_KEYS.clone();
    }

    @Override
    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(URL_KEY, urlKey);
        values.put(URL, Url);
        return values;
    }

    @Override
    public void setContent(ContentValues values) {
        urlKey = values.getAsString(URL_KEY);
        Url = values.getAsString(URL);
    }

    @Override
    public void setContent(Cursor cursor) {
        urlKey = cursor.getString(cursor.getColumnIndex(URL_KEY));
        Url = cursor.getString(cursor.getColumnIndex(URL));
    }

    public String getUrlKey() { return this.urlKey; }
    public void setUrlKey(String urlKey) { this.urlKey = urlKey; }
}