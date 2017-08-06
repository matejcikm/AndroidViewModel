package eu.inloop.viewmodel.sample.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.UUID;

import eu.inloop.viewmodel.AbstractViewModel;
import eu.inloop.viewmodel.IView;
import eu.inloop.viewmodel.base.ViewModelBaseFragment;
import eu.inloop.viewmodel.sample.injection.ComponentsHolder;
import eu.inloop.viewmodel.sample.injection.component.BaseFragmentComponent;

public abstract class ComponentBaseFragment<V extends IView, VM extends AbstractViewModel<V>, C extends BaseFragmentComponent> extends ViewModelBaseFragment<V, VM> {
    public static final String ARG_FRAGMENT_ID = "fragment_id";

    private String mFragmentId;

    public abstract C createComponent();

    @CallSuper
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            mFragmentId = UUID.randomUUID().toString();
        } else {
            mFragmentId = savedInstanceState.getString(ARG_FRAGMENT_ID);
        }
        inject();
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(ARG_FRAGMENT_ID, mFragmentId);
    }

    @Override
    public void onDestroy() {
        if (isRemoving()) {
            ComponentBaseActivity activity = (ComponentBaseActivity) getActivity();
            ComponentsHolder componentsHolder = activity.getComponentsHolder();
            componentsHolder.removeFragmentComponent(mFragmentId);
        }
        super.onDestroy();
    }

    @SuppressWarnings("unchecked")
    private void inject() {
        try {
            ComponentBaseActivity activity = (ComponentBaseActivity) getActivity();

            ComponentsHolder componentsHolder = activity.getComponentsHolder();
            C fragmentComponent = (C) componentsHolder.getFragmentComponent(mFragmentId);
            if (fragmentComponent == null) {
                fragmentComponent = createComponent();
                componentsHolder.addFragmentComponent(mFragmentId, fragmentComponent);
            }
            fragmentComponent.holder().set(this);
            fragmentComponent.inject(this);

        } catch (ClassCastException e) {
            throw new RuntimeException("Fragment must be inside ComponentBaseActivity");
        }
    }
}
