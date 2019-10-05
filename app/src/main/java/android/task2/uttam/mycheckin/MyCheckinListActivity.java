package android.task2.uttam.mycheckin;


import androidx.fragment.app.Fragment;

public class MyCheckinListActivity extends SingleFragmentActivity{
    @Override
    protected Fragment createFragment() {

        return new MyCheckinListFragment();
    }
}
