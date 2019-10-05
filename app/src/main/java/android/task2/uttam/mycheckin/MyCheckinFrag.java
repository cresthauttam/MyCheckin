package android.task2.uttam.mycheckin;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import java.io.File;
import java.util.Date;
import java.util.UUID;



public class MyCheckinFrag  extends Fragment {
    private static final int REQUEST_DATE = 0;
    static final int REQUEST_PHOTO = 1;

    private static final String DIALOG_DATE = "DialogDate";
    private static final String ARG_MYCHECKIN_ID = "checkin_id";
    private MyCheckin uniCheckin;
    private EditText uniTField;
    private EditText uniRestaurantPlace;
    private EditText uniDetail;
    private Button uniLocation;
    private Button uniDateBut;
    private Button uniDeletebut;
    private ImageButton uniPhotoBut;
    private ImageView uniPhotoView;
    private Button uniSave;
    private File uniPFile;
    private PackageManager packageManager;
    private Spinner uniType;





    public static MyCheckinFrag newInstance(UUID mycheckinid) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_MYCHECKIN_ID, mycheckinid);
        MyCheckinFrag fragment = new MyCheckinFrag();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID mycheckinid = (UUID) getArguments().getSerializable(ARG_MYCHECKIN_ID);
        uniCheckin = CheckinDetail.get(getActivity()).getlogins(mycheckinid);
        uniPFile = CheckinDetail.get(getActivity()).getPhotoFile(uniCheckin);
    }
    @Override public void onPause() {
        super.onPause();
        CheckinDetail.get(getActivity()).updateLogin(uniCheckin);}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vi = inflater.inflate(R.layout.frag_checkin, container, false);

         // For the title field

        uniTField = (EditText) vi.findViewById(R.id.checkin_title);
        uniTField.setText(uniCheckin.getTitle());
        uniTField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                uniCheckin.setTitle(s.toString());            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        uniDeletebut = (Button) vi.findViewById(R.id.checkin_delete);
        uniDeletebut.setOnClickListener(new View.OnClickListener()
        {
        @Override
        public void onClick(View v){
            CheckinDetail.get(getActivity()).deleteLogin(uniCheckin);
            startActivity(MyCheckinListActivity.newIntent(getActivity())); }
        });


        /**
         * for mycheckin detail
         */
        uniDetail = (EditText) vi.findViewById(R.id.checkin_cmnt);
        uniDetail.setText(uniCheckin.getDetail());
        uniDetail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                uniCheckin.setDetail(s.toString());            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        /**
         * for mycheckin location

        uniLocation = (Button) vi.findViewById(R.id.checkin_map);
        uniLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = Location.newIntent(getActivity());
                startActivity(intent);

            }
        });
         */
/*


        /**
         * for Date Button
         */
        uniDateBut = (Button) vi.findViewById(R.id.checkin_date);
        updateDate();
        uniDateBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                Checkin_DatePickerFrag dialog = Checkin_DatePickerFrag.newInstance(uniCheckin.getDate());
                dialog.setTargetFragment(MyCheckinFrag.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });


        /**
         * for save button
         *
         */
        uniSave = (Button) vi.findViewById(R.id.save_button);
        uniSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CheckinDetail.get(getActivity()).updateLogin(uniCheckin);
                startActivity(SingleFragmentActivity.newIntent(getActivity()));
            }
        });
        uniPhotoBut = (ImageButton) vi.findViewById(R.id.checkin_camera);

        // tried to input image - But didn't work properly
        /** final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
         boolean canTakePhoto = uniPFile != null &&
         captureImage.resolveActivity(packageManager) != null;    uniButtonPhoto.setEnabled(canTakePhoto);
         uniButtonPhoto.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        Uri uri = FileProvider.getUriForFile(getActivity(),
        "com.usc.task2.mycheckin.fileprovider",
        uniPFile);s
        captureImage.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        List<ResolveInfo> cameraActivities = getActivity()
        .getPackageManager().queryIntentActivities(captureImage,
        PackageManager.MATCH_DEFAULT_ONLY);
        for (ResolveInfo activity : cameraActivities) {
        getActivity().grantUriPermission(activity.activityInfo.packageName,
        uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        }
        startActivityForResult(captureImage, REQUEST_PHOTO);        }    });**/

        uniPhotoView = (ImageView) vi.findViewById(R.id.checkin_photo);

        return vi;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            /**Bundle extras = data.getExtras();

             Bitmap imageBitmap = (Bitmap) extras.get("data");

             uniViewPhoto.setImageBitmap(imageBitmap);
             **/

        }
        if (requestCode == REQUEST_DATE) {        Date date = (Date) data
                .getSerializableExtra(Checkin_DatePickerFrag.EXTRA_DATE);
            uniCheckin.setDate(date);
            updateDate();    }



    }





    private void updateDate() {

        uniDateBut.setText(uniCheckin.getDate().toString());
    }


}
