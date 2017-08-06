package eu.inloop.viewmodel.sample.main;

import javax.inject.Inject;

import eu.inloop.viewmodel.AbstractViewModel;
import eu.inloop.viewmodel.sample.injection.annotation.scope.PerScreen;

@PerScreen
public class MainViewModel extends AbstractViewModel<MainView> {

    @Inject
    public MainViewModel() {
    }
}
