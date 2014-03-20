package htvt.domain;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class HTVTDB {
    /* Used for debugging and logging */
    private static final String TAG = "HTVTDB";

    /**
     * The database that the provider uses as its underlying data store
     */
    private static final String DATABASE_NAME = "htvt.db";

    /**
     * The database version
     */
    private static final int DATABASE_VERSION = 2;

    private DatabaseHelper dbHelper;

    @Inject
    public HTVTDB(Context context) {
        dbHelper = new DatabaseHelper( context );
    }

    /*
     * /wardlist
     */
    public List<Member> getWardList() {
        return getData( Member.TABLE_NAME, Member.class );
    }

    public void updateWardList(List<Family> families) {
        //updateData( Family.TABLE_NAME, families );
    }

    // generic/helper methods
    private <T extends BaseRecord>List<T> getData(String tableName, Class<T> clazz ) {
        List<T> resultList = new ArrayList<T>( );
        Cursor results = null;
        try {
            SQLiteDatabase db = dbHelper.getDb();
            results = db.query(tableName, null, null, null, null, null, null);
            results.moveToFirst();
            while( !results.isAfterLast() ) {
                T record = clazz.newInstance();
                record.setContent(results);
                resultList.add(record);
                results.moveToNext();
            }
        } catch (Exception e) {
            Log.w(TAG, "getData() Exception: " + e.toString());
        } finally {
            closeCursor( results );
        }
        return resultList;
    }

    private void updateData( String tableName, List<? extends BaseRecord> data ) {
        SQLiteDatabase db = null;
        try {
            db = dbHelper.getDb();
            db.beginTransaction();
            db.delete(tableName, null, null);
//            DatabaseUtils.InsertHelper insertHelper = new DatabaseUtils.InsertHelper( db, tableName);

            for( BaseRecord dataRow : data ) {
//                insertHelper.insert( dataRow.getContentValues() );
                db.insert( tableName, null, dataRow.getContentValues() );
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();

        }
    }

    public void closeDB() {
        SQLiteDatabase db = dbHelper.getDb();
        if( db != null && db.isOpen() ) {
            db.close();
        }
    }

    private void closeCursor(Cursor results) {
        if( results != null ) {
            results.close();
        }
    }

    public static String getWhereForColumns(String... columnNames) {
        final String AND = " AND ";
        StringBuilder whereClause = new StringBuilder();
        for( String columnName : columnNames ) {
            whereClause.append( columnName + "=?" ).append( AND );
        }
        // remove extra ", " from the last element
        if( whereClause.length() > AND.length() ) {
            whereClause.delete( whereClause.length() - AND.length(), whereClause.length() );
        }
        return whereClause.toString();
    }

    /**
     * This method is only here to allow testing to run sql against the db. Application code should use the
     * other methods to get the data they need.
     *
     * @return
     */
    public SQLiteDatabase getDbReference() {
        return dbHelper.getDb();
    }


    public boolean hasData( String tableName ) {
        return DatabaseUtils.queryNumEntries(dbHelper.getDb(), tableName) > 0;
    }

    static class DatabaseHelper extends SQLiteOpenHelper {

        private SQLiteDatabase db;
        DatabaseHelper(Context context) {

            /* Calls the super constructor, requesting the default cursor factory. */
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            db = super.getWritableDatabase();
        }

        /**
         * Creates the underlying database with table name and column names taken from the
         * NotePad class.
         */
        @Override
        public void onCreate(SQLiteDatabase db) {
            // todo - setup INDEXES
            this.db = db;
            db.execSQL( MemberBaseRecord.CREATE_SQL );
            db.execSQL("PRAGMA foreign_keys = ON;");
        }

        public SQLiteDatabase getDb() {
            if (db == null || !db.isOpen()) {
                db = this.getWritableDatabase();
            }
            return db;
        }

        public void close() {
            if( db != null && db.isOpen() ) {
                db.close();
            }
        }

        @Override
        public void onOpen(SQLiteDatabase db) {
            super.onOpen(db);
            if (!db.isReadOnly()) {
	            /* Enable foreign key constraints */
                db.execSQL("PRAGMA foreign_keys=ON;");
            }
        }

        /**
         *
         * Demonstrates that the provider must consider what happens when the
         * underlying datastore is changed. In this sample, the database is upgraded the database
         * by destroying the existing data.
         * A real application should upgrade the database in place.
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // for now - do nothing
        }
    }
}