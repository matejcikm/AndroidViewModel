package eu.inloop.viewmodel.sample.main;

import dagger.Module;
import dagger.Subcomponent;
import eu.inloop.viewmodel.sample.injection.annotation.scope.PerScreen;
import eu.inloop.viewmodel.sample.injection.component.BaseActivityComponent;
import eu.inloop.viewmodel.sample.injection.module.BaseActivityModule;

@PerScreen
@Subcomponent(modules = MainActivityComponent.MainActivityModule.class)
public interface MainActivityComponent extends BaseActivityComponent<MainActivity> {

    @Module
    class MainActivityModule extends BaseActivityModule<MainActivity> {

        public MainActivityModule(MainActivity activity) {
            super(activity);
        }
    }
}
