package eu.inloop.viewmodel.sample.userlist.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;

import java.util.List;

import javax.inject.Inject;

import eu.inloop.viewmodel.sample.BR;
import eu.inloop.viewmodel.sample.SampleApplication;
import eu.inloop.viewmodel.sample.base.ComponentBaseActivity;
import eu.inloop.viewmodel.sample.databinding.ActivityUserListBinding;
import eu.inloop.viewmodel.sample.userlist.UserListAdapter;
import eu.inloop.viewmodel.sample.userlist.UserListView;
import eu.inloop.viewmodel.sample.userlist.UserListViewModel;

public class UserListActivity extends ComponentBaseActivity<UserListView, UserListViewModel, UserListActivtyComponent, ActivityUserListBinding> implements UserListView {

    @Inject
    UserListViewModel mViewModel;

    @Inject
    UserListAdapter mAdapter;

    @Override
    public ActivityUserListBinding inflateBindingLayout(LayoutInflater inflater) {
        return ActivityUserListBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getBinding().setVariable(BR.view, this);
        getBinding().userListRecyclerView.setAdapter(mAdapter);

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
