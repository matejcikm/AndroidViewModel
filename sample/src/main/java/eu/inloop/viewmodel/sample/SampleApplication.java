package eu.inloop.viewmodel.sample;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import eu.inloop.viewmodel.sample.injection.component.ApplicationComponent;
import eu.inloop.viewmodel.sample.injection.component.DaggerApplicationComponent;
import eu.inloop.viewmodel.sample.injection.module.ApplicationModule;

public class SampleApplication extends Application {

    private RefWatcher refWatcher;

    ApplicationComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        refWatcher = LeakCanary.install(this);
    }

    public static RefWatcher getRefWatcher(Context context) {
        SampleApplication application = (SampleApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    public static SampleApplication get(Context context) {
        return (SampleApplication) context.getApplicationContext();
    }

    public ApplicationComponent getApplicationComponent() {
        if (mComponent == null) {
            mComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }

        return mComponent;
    }
}
