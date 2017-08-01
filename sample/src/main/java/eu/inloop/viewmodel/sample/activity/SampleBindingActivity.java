package eu.inloop.viewmodel.sample.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import eu.inloop.viewmodel.sample.R;
import eu.inloop.viewmodel.sample.fragment.SampleBindingFragment;

public class SampleBindingActivity extends AppCompatActivity {

    public static Intent newIntent(Context context) {
        return new Intent(context, SampleBindingActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.root_content, new SampleBindingFragment(), "sample-binding-fragment").commit();
        }
    }
}
