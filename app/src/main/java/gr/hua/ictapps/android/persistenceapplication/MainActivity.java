package gr.hua.ictapps.android.persistenceapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OpenHelper openHelper = new OpenHelper(this);

        openHelper.addRecord(3,"Jane","+30456");
/*        Cursor cursor = openHelper.getRecords();

        if (cursor.moveToFirst()){
            do {
                String name = cursor.getString(1);
                String phone = cursor.getString(2);
                Toast.makeText(this, "name:"+name+",phone:"+phone, Toast.LENGTH_SHORT).show();
            } while(cursor.moveToNext());
        }*/
        String result = openHelper.getPhone("Jane");
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }
}
