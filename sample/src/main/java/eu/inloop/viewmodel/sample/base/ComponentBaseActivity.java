package eu.inloop.viewmodel.sample.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

import eu.inloop.viewmodel.AbstractViewModel;
import eu.inloop.viewmodel.IView;
import eu.inloop.viewmodel.base.ViewModelBaseActivity;
import eu.inloop.viewmodel.sample.injection.ComponentsHolder;
import eu.inloop.viewmodel.sample.injection.component.BaseActivityComponent;

public abstract class ComponentBaseActivity<V extends IView, VM extends AbstractViewModel<V>, C extends BaseActivityComponent> extends ViewModelBaseActivity<V, VM> {

    private ComponentsHolder mComponentsHolder;

    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        inject();
        super.onCreate(savedInstanceState);
    }

    public abstract C createComponent();

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return mComponentsHolder;
    }

    public ComponentsHolder getComponentsHolder() {
        return mComponentsHolder;
    }

    @SuppressWarnings("unchecked")
    private void inject() {
        mComponentsHolder = (ComponentsHolder) getLastCustomNonConfigurationInstance();

        C activityComponent;
        if (mComponentsHolder == null) {
            activityComponent = createComponent();
            mComponentsHolder = new ComponentsHolder(activityComponent);
        }

        mComponentsHolder.getActivityComponent().activityHolder().set(this);
        mComponentsHolder.getActivityComponent().inject(this);
    }
}
