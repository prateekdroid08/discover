package softradix.discover.com.view.activities;

import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import softradix.discover.com.R;
import softradix.discover.com.view.fragments.FragmentMain;

public class ActivityMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fragment_container);

        FragmentMain fragmentMain = FragmentMain.newInstance();
        FragmentTransaction fts = this.getSupportFragmentManager().beginTransaction();
        fts.add(R.id.fragmentHolder, fragmentMain);
        fts.addToBackStack(fragmentMain.getClass().getSimpleName());
        fts.commit();
    }

    @Override
    public void onBackPressed() {
        FragmentTransaction fts = this.getSupportFragmentManager().beginTransaction();
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 1) {
            fragmentManager.popBackStackImmediate();
            fts.commit();
        } else {
            finish();
        }

    }
}
