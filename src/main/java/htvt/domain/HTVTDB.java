package htvt.domain;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.google.inject.Inject;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<Family> getWardList() {
        List<Member> members = getData(Member.TABLE_NAME, Member.class);
        Map<Long, Member> memberMap = new HashMap<Long, Member>(members.size());
        for(Member member: members) {
            memberMap.put(member.getId(), member);
        }

        List<Family> families = getData(Family.TABLE_NAME, Family.class);
        Map<Long, Family> familyMap = new HashMap<Long, Family>();
        for(Family family: families) {
            Member parent = memberMap.get(family.getFatherId());
            if(parent != null) {
                parent.setFamily(family);
                family.setFather(parent);
            }
            parent = memberMap.get(family.getMotherId());
            if(parent != null) {
                parent.setFamily(family);
                family.setMother(parent);
            }
            familyMap.put(family.getId(), family);
        }

        List<ChildBaseRecord> childRecords = getData(ChildBaseRecord.TABLE_NAME, ChildBaseRecord.class);
        for(ChildBaseRecord childRecord: childRecords) {
            Member child = memberMap.get(childRecord.getMemberId());
            Family family = familyMap.get(childRecord.getFamilyId());
            child.setFamily(family);
            family.addChild(child);
        }

        return families;
    }

    public void updateWardList(List<Family> families) {
        long memberIdCounter = 1;
        long familyIdCounter = 1;
        List<Member> members = new ArrayList<Member>();
        List<ChildBaseRecord> childRecords = new ArrayList<ChildBaseRecord>();
        for(Family family: families) {
            family.setId(familyIdCounter++);
            Member parent = family.getFather();
            if(parent != null) {
                parent.setId(memberIdCounter++);
                family.setFatherId(parent.getId());
                members.add(parent);
            }
            parent = family.getMother();
            if(parent != null) {
                parent.setId(memberIdCounter++);
                family.setMotherId(parent.getId());
                members.add(parent);
            }
            for(Member child: family.getChildren()) {
                child.setId(memberIdCounter++);
                ChildBaseRecord childRecord = new ChildBaseRecord();
                childRecord.setMemberId(child.getId());
                childRecord.setFamilyId(family.getId());
                childRecords.add(childRecord);
                members.add(child);
            }
        }
        updateData(Member.TABLE_NAME, members);
        updateData(Family.TABLE_NAME, families);
        updateData(ChildBaseRecord.TABLE_NAME, childRecords);
    }

    // Districts
    public List<Auxiliary> getAssignmentInfo() {
        return new ArrayList<Auxiliary>();
    }

    public void updateDistricts(List<Auxiliary> auxiliaries) {

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
                if(tableName == Family.TABLE_NAME){
                    Log.i(TAG,"Inserting family row");
                }
                db.insert(tableName, null, dataRow.getContentValues());
                if(tableName == Family.TABLE_NAME){
                    Log.i(TAG,"Finished family row");
                }
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
            int ver = DATABASE_VERSION;
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            db = sqLiteDatabase;

            db.execSQL( MemberBaseRecord.CREATE_SQL);
            db.execSQL( FamilyBaseRecord.CREATE_SQL );
            db.execSQL( ChildBaseRecord.CREATE_SQL );
            db.execSQL( TagBaseRecord.CREATE_SQL );

            db.execSQL( AuxiliaryBaseRecord.CREATE_SQL );
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
            if(!sqLiteDatabase.isReadOnly()) {
                sqLiteDatabase.execSQL("PRAGMA foreign_keys=ON;");
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

        }
    }
}
