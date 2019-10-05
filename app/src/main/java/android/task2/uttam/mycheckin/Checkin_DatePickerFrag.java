// a fragment to pick a data
package android.task2.uttam.mycheckin;
import android.app.Activity;
        import android.app.Dialog;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.widget.DatePicker;
        import androidx.appcompat.app.AlertDialog;
        import androidx.fragment.app.DialogFragment;
        import java.util.Calendar;
        import java.util.Date;
        import java.util.GregorianCalendar;

public class Checkin_DatePickerFrag extends DialogFragment {
    public static final String EXTRA_DATE = "android.task2.uttam.mycheckin.MyCheckins.date";

    private static final String ARG_DATE = "date";
    private DatePicker uniDatePick;
    public static Checkin_DatePickerFrag newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);
        Checkin_DatePickerFrag fragment = new Checkin_DatePickerFrag();
        fragment.setArguments(args);
        return fragment;    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Date date = (Date) getArguments().getSerializable(ARG_DATE);
        Calendar calen = Calendar.getInstance();
        calen.setTime(date);
        int year = calen.get(Calendar.YEAR);
        int month = calen.get(Calendar.MONTH);
        int day = calen.get(Calendar.DAY_OF_MONTH);
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.checkin_data_dialog, null);
        uniDatePick = (DatePicker) v.findViewById(R.id.checkin_dialog_date_picker);
        uniDatePick.init(year, month, day, null);
        return new AlertDialog.Builder(getActivity()).setView(v).setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int year = uniDatePick.getYear();
                                int month = uniDatePick.getMonth();
                                int day = uniDatePick.getDayOfMonth();
                                Date date = new GregorianCalendar(year, month, day).getTime();
                                sendResult(Activity.RESULT_OK, date);
                            }
                        }).create();
    }
    private void sendResult(int resultCode, Date date) {
        if (getTargetFragment() == null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE, date);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}

// completion of Data fragment