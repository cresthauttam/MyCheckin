package android.task2.uttam.mycheckin;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;
import java.util.UUID;


public class MyCheckinPagerActivity extends AppCompatActivity {
    private static final String EXTRA_MYCHECKIN_ID = "com.bignerdranch.android.uttam.task2.MyCheckin_id";
    private ViewPager checkin_ViewPager;
    private List<MyCheckin> uniMyCheckins;
    public static Intent newIntent(Context packageContext, UUID MyCheckinId) {
        Intent intent = new Intent(packageContext, MyCheckinPagerActivity.class);
        intent.putExtra(EXTRA_MYCHECKIN_ID, MyCheckinId);        return intent;
    }
    @Override    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin_pager);
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_MYCHECKIN_ID);
        checkin_ViewPager = (ViewPager) findViewById(R.id.checkin_view_pager);
        uniMyCheckins = CheckinDetail.get(this).getlogins();
        FragmentManager fragmentManager = getSupportFragmentManager();
        checkin_ViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                MyCheckin MyCheckin = uniMyCheckins.get(position);
                return MyCheckinFrag.newInstance(MyCheckin.getId());
            }
            @Override

            public int getCount() {
                return uniMyCheckins.size();
            }
        });

        for (int i = 0; i < uniMyCheckins.size(); i++) {
            if (uniMyCheckins.get(i).getId().equals(crimeId)) {
                checkin_ViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
