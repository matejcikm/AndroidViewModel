package eu.inloop.viewmodel.sample.injection.module;

import android.support.v4.app.Fragment;

import dagger.Module;
import dagger.Provides;
import eu.inloop.viewmodel.base.ViewModelBaseFragment;
import eu.inloop.viewmodel.sample.injection.ModuleContextHolder;
import eu.inloop.viewmodel.sample.injection.annotation.scope.PerScreen;

@Module
public abstract class BaseFragmentModule<T extends ViewModelBaseFragment> {

    private ModuleContextHolder<T> mFragmentHolder;

    public BaseFragmentModule(T fragment) {
        mFragmentHolder = new ModuleContextHolder<>(fragment);
    }

    @Provides
    @PerScreen
    public T fragmentT() {
        return mFragmentHolder.get();
    }

    @Provides
    @PerScreen
    public Fragment fragment() {
        return mFragmentHolder.get();
    }

    @Provides
    @PerScreen
    public ModuleContextHolder<T> holder() {
        return mFragmentHolder;
    }
}
