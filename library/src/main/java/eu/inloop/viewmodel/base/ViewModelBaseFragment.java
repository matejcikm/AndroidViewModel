package eu.inloop.viewmodel.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import eu.inloop.viewmodel.AbstractViewModel;
import eu.inloop.viewmodel.IView;
import eu.inloop.viewmodel.ViewModelHelper;
import eu.inloop.viewmodel.binding.ViewModelBindingConfig;

public abstract class ViewModelBaseFragment<T extends IView, R extends AbstractViewModel<T>> extends Fragment implements IView {

    private ViewModelHelper<T, R> mViewModelHelper;

    @CallSuper
    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModelHelper = new ViewModelHelper<>(getViewModel());

        getViewModelHelper().onCreate(savedInstanceState, getArguments());
    }

    @CallSuper
    @Override
    public void onSaveInstanceState(@NonNull final Bundle outState) {
        super.onSaveInstanceState(outState);
        getViewModelHelper().onSaveInstanceState(outState);
    }

    @CallSuper
    @Override
    public void onStart() {
        super.onStart();
        getViewModelHelper().onStart();
    }

    @CallSuper
    @Override
    public void onStop() {
        super.onStop();
        getViewModelHelper().onStop();
    }

    @CallSuper
    @Override
    public void onDestroyView() {
        getViewModelHelper().onDestroyView(this);
        super.onDestroyView();
    }

    @CallSuper
    @Override
    public void onDestroy() {
        getViewModelHelper().onDestroy(this);
        super.onDestroy();
    }

    @NonNull
    public abstract R getViewModel();

    @Nullable
    @Override
    public ViewModelBindingConfig getViewModelBindingConfig() {
        return null;
    }

    @NonNull
    public ViewModelHelper<T, R> getViewModelHelper() {
        return mViewModelHelper;
    }

    @Override
    public void removeViewModel() {
        mViewModelHelper.removeViewModel();
    }

    /**
     * Call this after your view is ready - usually on the end of {@link
     * Fragment#onViewCreated(View, Bundle)}
     *
     * @param view view
     */
    protected void setModelView(@NonNull final T view) {
        getViewModelHelper().setView(view);
    }
}
