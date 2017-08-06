package eu.inloop.viewmodel.sample.injection.component;

import eu.inloop.viewmodel.sample.base.ComponentBaseActivity;
import eu.inloop.viewmodel.sample.injection.ModuleContextHolder;

public interface BaseActivityComponent<T extends ComponentBaseActivity> {

    ModuleContextHolder<T> activityHolder();

    void inject(T activity);
}
