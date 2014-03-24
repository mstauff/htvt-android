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
        private static final String DATABASE_NAME = "HTVT.db";
        private static final int DATABASE_VERSION = 1;
        private SQLiteDatabase db;

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            db = super.getWritableDatabase();
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            db = sqLiteDatabase;

            db.execSQL( MemberBaseRecord.CREATE_SQL);
            db.execSQL( FamilyBaseRecord.CREATE_SQL );
            db.execSQL( ChildBaseRecord.CREATE_SQL );
            db.execSQL( TagBaseRecord.CREATE_SQL );

            db.execSQL( DistrictBaseRecord.CREATE_SQL );
            db.execSQL( CompanionshipBaseRecord.CREATE_SQL );
            db.execSQL( AssignmentBaseRecord.CREATE_SQL );
            db.execSQL( VisitBaseRecord.CREATE_SQL );
            db.execSQL( TagBaseRecord.CREATE_SQL );
        }

        public SQLiteDatabase getDb() {
            if(db == null || !db.isOpen()) {
                db = this.getWritableDatabase();
            }
            return db;
        }

        public void close() {
            if( db != null && db.isOpen()) {
                db.close();
            }
        }

        @Override
        public void onOpen(SQLiteDatabase sqLiteDatabase) {
            super.onOpen(sqLiteDatabase);
            if(!db.isReadOnly()) {
                db.execSQL("PRAGMA foreign_keys=ON;");
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

        }
    }
}
