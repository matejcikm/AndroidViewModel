package eu.inloop.viewmodel.sample.injection.module;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import eu.inloop.viewmodel.sample.base.ComponentBaseActivity;
import eu.inloop.viewmodel.sample.injection.ModuleContextHolder;
import eu.inloop.viewmodel.sample.injection.annotation.qualifier.ActivityContext;
import eu.inloop.viewmodel.sample.injection.annotation.scope.PerScreen;

@Module
public abstract class BaseActivityModule<T extends ComponentBaseActivity> {

    private ModuleContextHolder<T> mActivityHolder;

    public BaseActivityModule(T activity) {
        mActivityHolder = new ModuleContextHolder<>(activity);
    }

    @Provides
    @PerScreen
    public T providesActivityT() {
        return mActivityHolder.get();
    }

    @Provides
    @PerScreen
    public Activity activity() {
        return mActivityHolder.get();
    }

    @Provides
    @ActivityContext
    public Context context() {
        return mActivityHolder.get();
    }

    @Provides
    @PerScreen
    public ModuleContextHolder<T> holder() {
        return mActivityHolder;
    }
}
