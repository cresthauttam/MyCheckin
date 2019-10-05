// Updating fragment
package android.task2.uttam.mycheckin;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import java.util.UUID;

public class CheckinUpdateFrag extends Fragment {
    private MyCheckin uniMyCheckin;
    private TextView uniTitleField, uniDateField, uniDestinationField, uniDetailField, uniLocationField;
    private ImageView uniImageView;

    private Button uniDeletebut, uniMapBut;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String activityID = "";


        Bundle bundle = this.getArguments();

        if (bundle != null) {

            activityID = bundle.getString(MyCheckinActivity.EXTRA_MYCHECKIN_ID, "");

        }

        uniMyCheckin = CheckinDetail.get(getActivity()).getlogins(UUID.fromString(activityID));


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.update_checkin_frag, container, false);

        uniTitleField = (TextView) v.findViewById(R.id.title);

        uniDetailField = (TextView) v.findViewById(R.id.comment);

        uniLocationField = (TextView) v.findViewById(R.id.location);

        uniDestinationField = (TextView) v.findViewById(R.id.destination);


        uniDateField.setText(uniMyCheckin.getDate().toString());


        uniTitleField.setText(uniMyCheckin.getTitle());

        uniDetailField.setText(uniMyCheckin.getDetail());

        uniLocationField.setText(uniMyCheckin.getLocation());

        uniDestinationField.setText(uniMyCheckin.getRestaurantPlace());


        uniMapBut = (Button) v.findViewById(R.id.checkin_map);

        uniDeletebut = (Button) v.findViewById(R.id.checkin_delete);

        uniImageView = (ImageView) v.findViewById(R.id.imageView);


        uniDeletebut.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                CheckinDetail.get(getActivity()).deleteLogin(uniMyCheckin);

                startActivity(SingleFragmentActivity.newIntent(getActivity()));

            }

        });

        uniMapBut.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {
                Toast.makeText(getContext(), "Show map", Toast.LENGTH_SHORT).show();


            }
        });


        return v;
    }
}
