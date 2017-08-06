package eu.inloop.viewmodel.sample.userlist.activity;

import dagger.Module;
import dagger.Subcomponent;
import eu.inloop.viewmodel.sample.injection.annotation.scope.PerScreen;
import eu.inloop.viewmodel.sample.injection.component.BaseActivityComponent;
import eu.inloop.viewmodel.sample.injection.module.BaseActivityModule;

@PerScreen
@Subcomponent(modules = UserListActivtyComponent.UserListModule.class)
public interface UserListActivtyComponent extends BaseActivityComponent<UserListActivity> {

    @Module
    class UserListModule extends BaseActivityModule<UserListActivity> {

        public UserListModule(UserListActivity activity) {
            super(activity);
        }
    }
}
