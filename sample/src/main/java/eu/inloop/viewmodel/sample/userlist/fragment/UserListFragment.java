package eu.inloop.viewmodel.sample.userlist.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.leakcanary.RefWatcher;

import java.util.List;

import javax.inject.Inject;

import eu.inloop.viewmodel.sample.SampleApplication;
import eu.inloop.viewmodel.sample.base.ComponentBaseFragment;
import eu.inloop.viewmodel.sample.databinding.FragmentUserlistBinding;
import eu.inloop.viewmodel.sample.userlist.UserListAdapter;
import eu.inloop.viewmodel.sample.userlist.UserListView;
import eu.inloop.viewmodel.sample.userlist.UserListViewModel;
import eu.inloop.viewmodel.sample.userlist.activity.UserListActivity;

public class UserListFragment extends ComponentBaseFragment<UserListView, UserListViewModel, UserListComponent> implements UserListView {

    @Inject
    UserListAdapter mAdapter;

    @Inject
    UserListViewModel mViewModel;

    @Override
    public UserListComponent createComponent() {
        return SampleApplication.get(getContext()).getApplicationComponent().plus(new UserListComponent.UserListModule(this));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentUserlistBinding binding = FragmentUserlistBinding.inflate(inflater, container, false);
        binding.setView(this); //TODO improve binding
        binding.setViewModel(mViewModel);
        binding.executePendingBindings();

        binding.userListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.userListRecyclerView.setAdapter(mAdapter);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setModelView(this);
    }

    @Override
    public void showUsers(List<String> users) {
        mAdapter.setItems(users);
    }

    @Override
    public void navigateToActivity() {
        startActivity(new Intent(getContext(), UserListActivity.class));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // watch for memory leaks
        RefWatcher refWatcher = SampleApplication.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }

    @NonNull
    @Override
    public UserListViewModel getViewModel() {
        return mViewModel;
    }
}
