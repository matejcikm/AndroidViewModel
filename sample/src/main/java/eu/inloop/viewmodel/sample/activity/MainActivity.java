package eu.inloop.viewmodel.sample.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import eu.inloop.viewmodel.sample.R;
import eu.inloop.viewmodel.sample.fragment.UserListFragment;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.root_content, new UserListFragment(), "user-list-fragment").commit();
        }
    }

}
