// Sqlite version to create database
package android.task2.uttam.mycheckin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CheckinDetail {

    private static CheckinDetail sCheckinDetail;

    private Context uniContext;
    private SQLiteDatabase loginDatabase;
    public static CheckinDetail get(Context context) {
        if (sCheckinDetail == null) {
            sCheckinDetail = new CheckinDetail(context);
        }        return sCheckinDetail;
    }
    private CheckinDetail(Context context) {
        uniContext = context.getApplicationContext();
        loginDatabase = new CheckinBaseHelper(uniContext).getWritableDatabase();


    }
    public void newLogin(MyCheckin c) {
        ContentValues values = getContentValues(c);
        loginDatabase.insert(MyCheckinDatabase.MyCheckinTable.NAME, null, values);

    }

    public List<MyCheckin> getlogins() {

        List<MyCheckin> MyCheckins = new ArrayList<>();
        CheckinCrsrWrapper cursor = queryMyCheckins(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                MyCheckins.add(cursor.getMyCheckin());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return MyCheckins;
    }

    public MyCheckin getlogins(UUID id) {

        CheckinCrsrWrapper cursor = queryMyCheckins(
                MyCheckinDatabase.MyCheckinTable.Columns.UUID + " = ?",
                new String[] { id.toString() }
        );  try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getMyCheckin();
        } finally {
            cursor.close();    }
    }
    public File getPhotoFile(MyCheckin MyCheckin) {
        File filesDir = uniContext.getFilesDir();
        return new File(filesDir, MyCheckin.getImageFilename());
    }


    public void updateLogin(MyCheckin MyCheckin) {
        String uuidString = MyCheckin.getId().toString();
        ContentValues values = getContentValues(MyCheckin);
        loginDatabase.update(MyCheckinDatabase.MyCheckinTable.NAME, values,
                MyCheckinDatabase.MyCheckinTable.Columns.UUID + " = ?",
                new String[] { uuidString });
    }
    public void deleteLogin(MyCheckin myMyCheckin){
        String uuidString = myMyCheckin.getId().toString();
        loginDatabase.delete(MyCheckinDatabase.MyCheckinTable.NAME, MyCheckinDatabase.MyCheckinTable.Columns.UUID + " =?", new String[] { uuidString });
    }


    private CheckinCrsrWrapper queryMyCheckins(String whereClause, String[] whereArgs) {
        Cursor cursor = loginDatabase.query(            MyCheckinDatabase.MyCheckinTable.NAME,            null,
                // columns - null selects all columns
                whereClause,
                whereArgs,            null,
                // groupBy
                null,
                // having
                null
                // orderBy
        );
        return new CheckinCrsrWrapper(cursor);
    }
    private static ContentValues getContentValues(MyCheckin MyCheckin) {
        ContentValues values = new ContentValues();
        values.put(MyCheckinDatabase.MyCheckinTable.Columns.UUID, MyCheckin.getId().toString());
        values.put(MyCheckinDatabase.MyCheckinTable.Columns.TITLE, MyCheckin.getTitle());
        values.put(MyCheckinDatabase.MyCheckinTable.Columns.DATE, MyCheckin.getDate().getTime());
        values.put(MyCheckinDatabase.MyCheckinTable.Columns.DETAIL, MyCheckin.getDetail());
        values.put(MyCheckinDatabase.MyCheckinTable.Columns.RESTAURANT, MyCheckin.getRestaurantPlace());
        values.put(MyCheckinDatabase.MyCheckinTable.Columns.LOCATION,MyCheckin.getLocation());
        return values;    }

}

