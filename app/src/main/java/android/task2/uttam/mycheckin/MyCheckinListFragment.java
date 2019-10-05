//uttam shrestha
// shows every checkin that has been created
package android.task2.uttam.mycheckin;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyCheckinListFragment extends Fragment {


    private Button checkin_HelpButton;
    private RecyclerView checkinMyCheckinRecyclerView;
    private MyCheckinAdapter checkinAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_checkin_list, container, false);

        checkinMyCheckinRecyclerView = (RecyclerView) view.findViewById(R.id.checkin_recycler_view);
        checkinMyCheckinRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;    }
    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.frag_checkin_list, menu);
        MenuItem help = menu.findItem(R.id.show_help);


    }
    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_checkin:
                MyCheckin MyCheckin = new MyCheckin();
                CheckinDetail.get(getActivity()).newLogin(MyCheckin);
                Intent intent = MyCheckinPagerActivity.newIntent(getActivity(), MyCheckin.getId());
                startActivity(intent);
                return true;
            case R.id.show_help:

                Intent helpIntent = Help.newIntent(getActivity());
                startActivity(helpIntent);



                return true;
            default:
                return super.onOptionsItemSelected(item);
        } }

    private void updateUI() {
        CheckinDetail MyCheckinDetail = CheckinDetail.get(getActivity());
        List<MyCheckin> MyCheckins = MyCheckinDetail.getlogins();
        if (checkinAdapter == null) {
            checkinAdapter = new MyCheckinAdapter(MyCheckins);
            checkinMyCheckinRecyclerView.setAdapter(checkinAdapter);
        } else {
            checkinAdapter.setMyCheckins(MyCheckins);
            checkinAdapter.notifyDataSetChanged();
        }

    }

    /**
     * A view holder class for recycler View
     */
    private class CheckinHolder extends RecyclerView.ViewHolder  implements View.OnClickListener  {
        private TextView uniTitleTextView;
        private TextView uniDateTextView;
        private TextView uniRestTextView;
        private MyCheckin uniMyCheckin;
        public CheckinHolder(LayoutInflater inflater,ViewGroup parent) {
            super(inflater.inflate(R.layout.checkin_list_item, parent, false));
            itemView.setOnClickListener(this);
            uniTitleTextView = (TextView) itemView.findViewById(R.id.checkin_title);
            uniDateTextView = (TextView) itemView.findViewById(R.id.checkin_date);
            uniRestTextView = (TextView) itemView.findViewById(R.id.checkin_restaurant);
        }
        @Override    public void onClick(View view) {
            Intent intent = MyCheckinPagerActivity.newIntent(getActivity(), uniMyCheckin.getId());
            startActivity(intent);    }
        public void bind(MyCheckin MyCheckin) {
            uniMyCheckin = MyCheckin;
            uniTitleTextView.setText(uniMyCheckin.getTitle());
            uniDateTextView.setText(uniMyCheckin.getDate().toString());
            uniRestTextView.setText(uniMyCheckin.getRestaurantPlace());
        }
    }

    /**
     * end of View Holder
     */

    /**
     *
     * An Adapter for RecyclerView
     */
    private class MyCheckinAdapter extends RecyclerView.Adapter<CheckinHolder> {
        private List<MyCheckin> uniMyCheckins;
        public MyCheckinAdapter(List<MyCheckin> MyCheckins) {
            uniMyCheckins = MyCheckins;        }
        @Override
        public CheckinHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new CheckinHolder(layoutInflater, parent);
        }
        @Override
        public void onBindViewHolder(CheckinHolder holder, int position) {
            MyCheckin MyCheckin = uniMyCheckins.get(position);
            holder.bind(MyCheckin);

        }
        @Override
        public int getItemCount() {
            return
                    uniMyCheckins.size();
        }
        public void setMyCheckins(List<MyCheckin> MyCheckins) {
            uniMyCheckins = MyCheckins;    }

    }
    /**
     * End of Adapter
     */

}




