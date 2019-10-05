// Base helper to manage reccorded Checkin data
package android.task2.uttam.mycheckin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CheckinBaseHelper  extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "MyCheckinBase.db";

    public CheckinBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + MyCheckinDatabase.MyCheckinTable.NAME + "("+" _id integer primary key autoincrement," +
                MyCheckinDatabase.MyCheckinTable.Columns.UUID + "," +
                MyCheckinDatabase.MyCheckinTable.Columns.TITLE + "," +
                MyCheckinDatabase.MyCheckinTable.Columns.DATE + "," +
                MyCheckinDatabase.MyCheckinTable.Columns.DETAIL + "," +
                MyCheckinDatabase.MyCheckinTable.Columns.RESTAURANT + "," +
                MyCheckinDatabase.MyCheckinTable.Columns.LOCATION  +
                ")");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
// completion of BaseHelper part
