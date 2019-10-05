package android.task2.uttam.mycheckin;

import androidx.fragment.app.Fragment;
import android.content.Context;
import android.content.Intent;
;

import java.util.UUID;

public class MyCheckinActivity extends SingleFragmentActivity{
    public static final String EXTRA_MYCHECKIN_ID = "android.task2.uttam.mycheckin.MyCheckins.id";
    public static Intent newIntent(Context packageContext, UUID mycheckinId) {
        Intent intent = new Intent(packageContext, MyCheckinActivity.class);
        intent.putExtra(EXTRA_MYCHECKIN_ID, mycheckinId);
        return intent;
    }
    @Override
    public Fragment createFragment() {
        UUID mycheckinId = (UUID) getIntent().getSerializableExtra(EXTRA_MYCHECKIN_ID);
        return MyCheckinFrag.newInstance(mycheckinId);
    }


}