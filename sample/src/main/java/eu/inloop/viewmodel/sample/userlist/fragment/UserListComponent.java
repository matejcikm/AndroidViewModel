package eu.inloop.viewmodel.sample.userlist.fragment;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;
import eu.inloop.viewmodel.sample.injection.annotation.qualifier.ActivityContext;
import eu.inloop.viewmodel.sample.injection.annotation.scope.PerScreen;
import eu.inloop.viewmodel.sample.injection.component.BaseFragmentComponent;
import eu.inloop.viewmodel.sample.injection.module.BaseFragmentModule;

@PerScreen
@Subcomponent(modules = UserListComponent.UserListModule.class)
public interface UserListComponent extends BaseFragmentComponent<UserListFragment> {

    @Module
    class UserListModule extends BaseFragmentModule<UserListFragment> {

        public UserListModule(UserListFragment fragment) {
            super(fragment);
        }

        @Provides
        @PerScreen
        ArrayAdapter arrayAdapter(@ActivityContext Context context) {
            return new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, android.R.id.text1, new ArrayList<String>());
        }
    }
}
