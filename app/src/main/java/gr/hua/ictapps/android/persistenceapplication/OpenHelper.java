package gr.hua.ictapps.android.persistenceapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tserpes on 07/05/18.
 */

public class OpenHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "CONTACTS";
    public static final int VERSION = 1;
    public static final String KEY_ID = "ID";
    public static final String KEY_NAME = "NAME";
    public static final String KEY_PHONE = "PHONE";

    public OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public OpenHelper(Context context){
        super(context, TABLE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_NAME +" ("+KEY_ID+" INT, "+KEY_NAME+" TEXT, "+KEY_PHONE+" TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addRecord(int id, String name, String phone){
        ContentValues values = new ContentValues();
        values.put(KEY_ID,id);
        values.put(KEY_NAME,name);
        values.put(KEY_PHONE,phone);
        SQLiteDatabase database = this.getWritableDatabase();
        database.insert(TABLE_NAME,null,values);
    }

    public Cursor getRecords(){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME,null,null,null,null,null,null);
        return cursor;
    }

    public String getPhone(String name){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME,new String[]{KEY_PHONE},KEY_NAME+"=?",new String[]{name},null,null,null);
        if (cursor.moveToFirst()){
            return cursor.getString(0);
        }
        return null;
    }
}







