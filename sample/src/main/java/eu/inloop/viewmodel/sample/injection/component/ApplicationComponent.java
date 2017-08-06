package eu.inloop.viewmodel.sample.injection.component;

import javax.inject.Singleton;

import dagger.Component;
import eu.inloop.viewmodel.sample.injection.module.ApplicationModule;
import eu.inloop.viewmodel.sample.main.MainActivityComponent;
import eu.inloop.viewmodel.sample.userlist.activity.UserListActivtyComponent;
import eu.inloop.viewmodel.sample.userlist.fragment.UserListComponent;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    MainActivityComponent plus(MainActivityComponent.MainActivityModule mainActivityModule);

    UserListComponent plus(UserListComponent.UserListModule  userListModule);

    UserListActivtyComponent plus(UserListActivtyComponent.UserListModule userListModule);

}
