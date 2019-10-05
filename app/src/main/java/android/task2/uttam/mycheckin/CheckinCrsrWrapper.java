// Cursor Wrapper part to wrap up the details that had been inputted
package android.task2.uttam.mycheckin;

import android.database.Cursor;
import android.database.CursorWrapper;
import java.util.Date;
import java.util.UUID;

public class CheckinCrsrWrapper  extends CursorWrapper {
    public CheckinCrsrWrapper(Cursor cursor) {
        super(cursor);    }
    public MyCheckin getMyCheckin() {
        String uuidString = getString(getColumnIndex(MyCheckinDatabase.MyCheckinTable.Columns.UUID));
        String title = getString(getColumnIndex(MyCheckinDatabase.MyCheckinTable.Columns.TITLE));
        String detail = getString(getColumnIndex(MyCheckinDatabase.MyCheckinTable.Columns.DETAIL));
        String place = getString(getColumnIndex(MyCheckinDatabase.MyCheckinTable.Columns.RESTAURANT));
        long date = getLong(getColumnIndex(MyCheckinDatabase.MyCheckinTable.Columns.DATE));
        String location = getString(getColumnIndex(MyCheckinDatabase.MyCheckinTable.Columns.LOCATION));



        MyCheckin MyCheckin = new MyCheckin(UUID.fromString(uuidString));
        MyCheckin.setTitle(title);
        MyCheckin.setDetail(detail);
        MyCheckin.setRestaurantPlace(place);
        MyCheckin.setLocation(location);


        MyCheckin.setDate(new Date(date));
        return MyCheckin;     }
}

// completion of Cursor Wrapper