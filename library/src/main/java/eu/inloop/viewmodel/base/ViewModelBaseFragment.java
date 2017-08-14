package eu.inloop.viewmodel.base;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import eu.inloop.viewmodel.AbstractViewModel;
import eu.inloop.viewmodel.BR;
import eu.inloop.viewmodel.IView;
import eu.inloop.viewmodel.ViewModelHelper;

public abstract class ViewModelBaseFragment<T extends IView, R extends AbstractViewModel<T>, B extends ViewDataBinding> extends Fragment implements IView {

    private ViewModelHelper<T, R> mViewModelHelper;
    private B mBinding;

    @CallSuper
    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModelHelper = new ViewModelHelper<>(getViewModel());

        getViewModelHelper().onCreate(savedInstanceState, getArguments());
    }

    public abstract B inflateBindingLayout(LayoutInflater layoutInflater, ViewGroup container);

    @CallSuper
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = inflateBindingLayout(inflater, container);
        mBinding.setVariable(BR.view, this);
        mBinding.setVariable(BR.viewModel, getViewModel());
        return mBinding.getRoot();
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

    public B getBinding() {
        return mBinding;
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
