package htvt.domain;

import android.content.ContentValues;
import android.database.Cursor;

public class VisitBaseRecord implements BaseRecord {
    public static final String TABLE_NAME = "visit";
    public static final String VISIT_ID = "visit_id";
    public static final String ASSIGNMENT_ID = "assignment_id";
    public static final String VISITED = "visited";
    public static final String YEAR = "year";
    public static final String MONTH = "month";

    private Long visitId;
    private Long assignmentId = 0L;
    private Long visited;
    private Long year = 0L;
    private Long month = 0L;

    public static final String CREATE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
            + VISIT_ID + " INTEGER PRIMARY KEY, "
            + ASSIGNMENT_ID + " INTEGER, "
            + VISITED + " INTEGER, "
            + YEAR + " INTEGER, "
            + MONTH + " INTEGER, "
            + "FOREIGN KEY(" + ASSIGNMENT_ID + ") REFERENCES "
            + AssignmentBaseRecord.TABLE_NAME + "(" + AssignmentBaseRecord.ASSIGNMENT_ID + ") ON DELETE CASCADE"
            + ");";

    static final String[] ALL_KEYS = new String[] { VISIT_ID, ASSIGNMENT_ID, VISITED, YEAR, MONTH};

    public String[] getAllKeys() {
        return ALL_KEYS.clone();
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(VISIT_ID, visitId);
        values.put(ASSIGNMENT_ID, assignmentId);
        values.put(VISITED, visited);
        values.put(YEAR, year);
        values.put(MONTH, month);
        return values;
    }

    public void setContent(ContentValues values) {
        visitId = values.getAsLong(VISIT_ID);
        assignmentId = values.getAsLong(ASSIGNMENT_ID);
        visited = values.getAsLong(VISITED);
        year = values.getAsLong(YEAR);
        month = values.getAsLong(MONTH);
    }

    public void setContent(Cursor cursor) {
        visitId = cursor.getLong(cursor.getColumnIndex(VISIT_ID));
        assignmentId = cursor.getLong(cursor.getColumnIndex(ASSIGNMENT_ID));
        visited = cursor.getLong(cursor.getColumnIndex(VISITED));
        year = cursor.getLong(cursor.getColumnIndex(YEAR));
        month = cursor.getLong(cursor.getColumnIndex(MONTH));
    }

    public Long getVisitId() { return visitId; }
    public void setVisitId(Long visitId) { this.visitId = visitId; }

    public Long getAssignmentId() { return assignmentId; }
    public void setAssignmentId(Long assignmentId) { this.assignmentId = assignmentId; }

    public Long getVisited() { return visited; }
    public void setVisited(Long visited) { this.visited = visited; }

    public Long getYear() { return year; }
    public void setYear(Long year) { this.year = year; }

    public Long getMonth() { return month; }
    public void setMonth(Long month) { this.month = month; }
}