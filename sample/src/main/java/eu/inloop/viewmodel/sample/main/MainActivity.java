package eu.inloop.viewmodel.sample.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;

import javax.inject.Inject;

import eu.inloop.viewmodel.sample.R;
import eu.inloop.viewmodel.sample.SampleApplication;
import eu.inloop.viewmodel.sample.base.ComponentBaseActivity;
import eu.inloop.viewmodel.sample.databinding.ActivityMainBinding;
import eu.inloop.viewmodel.sample.userlist.fragment.UserListFragment;


public class MainActivity extends ComponentBaseActivity<MainView, MainViewModel, MainActivityComponent, ActivityMainBinding> implements MainView {

    @Inject
    MainViewModel mViewModel;

    @Override
    public ActivityMainBinding inflateBindingLayout(LayoutInflater inflater) {
        return ActivityMainBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setModelView(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.root_content, new UserListFragment(), "user-list-fragment").commit();
        }
    }

    @NonNull
    @Override
    public MainViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    public MainActivityComponent createComponent() {
        return SampleApplication.get(this).getApplicationComponent().plus(new MainActivityComponent.MainActivityModule(this));
    }
}
