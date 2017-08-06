package eu.inloop.viewmodel.sample.userlist.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import java.util.List;

import javax.inject.Inject;

import eu.inloop.viewmodel.sample.R;
import eu.inloop.viewmodel.sample.SampleApplication;
import eu.inloop.viewmodel.sample.base.ComponentBaseActivity;
import eu.inloop.viewmodel.sample.databinding.ActivityUserListBinding;
import eu.inloop.viewmodel.sample.userlist.UserListAdapter;
import eu.inloop.viewmodel.sample.userlist.UserListView;
import eu.inloop.viewmodel.sample.userlist.UserListViewModel;

public class UserListActivity extends ComponentBaseActivity<UserListView, UserListViewModel, UserListActivtyComponent> implements UserListView {

    @Inject
    UserListViewModel mViewModel;

    @Inject
    UserListAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUserListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_user_list);
        binding.setView(this); //TODO improve binding
        binding.setViewModel(mViewModel);
        binding.executePendingBindings();

        binding.userListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.userListRecyclerView.setAdapter(mAdapter);

        setModelView(this);
    }

    @Override
    public UserListActivtyComponent createComponent() {
        return SampleApplication.get(this).getApplicationComponent().plus(new UserListActivtyComponent.UserListModule(this));
    }

    @NonNull
    @Override
    public UserListViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    public void showUsers(List<String> users) {
        mAdapter.setItems(users);
    }

    @Override
    public void navigateToActivity() {
        //no op
    }
}
