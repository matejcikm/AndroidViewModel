package eu.inloop.viewmodel.sample.injection.component;

import eu.inloop.viewmodel.base.ViewModelBaseFragment;
import eu.inloop.viewmodel.sample.injection.ModuleContextHolder;

public interface BaseFragmentComponent<T extends ViewModelBaseFragment> {

    ModuleContextHolder<T> holder();

    void inject(T fragment);
}
