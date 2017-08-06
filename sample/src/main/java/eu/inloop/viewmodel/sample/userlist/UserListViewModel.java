package eu.inloop.viewmodel.sample.userlist;

import android.annotation.SuppressLint;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import eu.inloop.viewmodel.AbstractViewModel;
import eu.inloop.viewmodel.sample.injection.annotation.scope.PerScreen;

@PerScreen
public class UserListViewModel extends AbstractViewModel<UserListView> {

    private static final int TOTAL_USERS = 7;
    private List<String> mLoadedUsers;

    private float mCurrentLoadingProgress = 0;

    public ObservableInt loadingVisibility = new ObservableInt(View.GONE);
    public ObservableField<String> progressText = new ObservableField<>();

    @Inject
    public UserListViewModel() {
    }

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);

        //this will be only not null in case the application was killed due to low memory
        if (savedInstanceState != null) {
            mLoadedUsers = savedInstanceState.getStringArrayList("userlist");
        }
    }

    @Override
    public void onBindView(@NonNull UserListView view) {
        super.onBindView(view);

        //downloading list of users
        if (mLoadedUsers != null) {
            view.showUsers(mLoadedUsers);
        } else {
            loadUsers();
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void loadUsers() {
        mCurrentLoadingProgress = 0;
        loadingVisibility.set(View.VISIBLE);
        new AsyncTask<Void, Float, List<String>>() {

            @Override
            protected List<String> doInBackground(Void... voids) {
                final List<String> list = new ArrayList<>();
                for (int i = 0; i < TOTAL_USERS; i++) {
                    list.add("User " + i);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        //
                    }
                    publishProgress((i+1) / (float)TOTAL_USERS);
                }

                return list;
            }

            @Override
            protected void onProgressUpdate(Float... values) {
                super.onProgressUpdate(values);
                mCurrentLoadingProgress = values[0];
                progressText.set((int) (mCurrentLoadingProgress * 100) + "%");
            }

            @Override
            protected void onPostExecute(List<String> s) {
                super.onPostExecute(s);
                mLoadedUsers = s;
                loadingVisibility.set(View.GONE);
                getViewOptional().showUsers(s);
            }
        }.execute();
    }

    @Override
    public void onSaveInstanceState(@NonNull final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (mLoadedUsers != null) {
            bundle.putStringArrayList("userlist", new ArrayList<>(mLoadedUsers));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //use this to cancel any planned requests
    }
}
