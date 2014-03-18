package htvt.api;

import android.database.Cursor;

public class DBUtil {
    
    public static String getDbStringIfPresent( String columnName, Cursor cursor, String defaultVal) {
        int columnIndex = cursor.getColumnIndex(columnName);
        String result = defaultVal;
        if( columnIndex > -1 ) {
            result = cursor.getString(columnIndex);
        }
        
        return result;
    }

    public static long getDbLongIfPresent( String columnName, Cursor cursor, long defaultVal) {
        int columnIndex = cursor.getColumnIndex(columnName);
        long result = defaultVal;
        if( columnIndex > -1 ) {
            result = cursor.getLong(columnIndex);
        }
        
        return result;
    }

    public static int getDbIntIfPresent( String columnName, Cursor cursor, int defaultVal) {
        int columnIndex = cursor.getColumnIndex(columnName);
        int result = defaultVal;
        if( columnIndex > -1 ) {
            result = cursor.getInt(columnIndex);
        }
        
        return result;
    }
}
