package htvt.domain;

import android.content.ContentValues;
import android.database.Cursor;

public interface BaseRecord {
    public ContentValues getContentValues();
      public void setContent(ContentValues values);
      public void setContent(Cursor cursor);
}
