package eu.inloop.viewmodel.base;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import eu.inloop.viewmodel.AbstractViewModel;
import eu.inloop.viewmodel.BR;
import eu.inloop.viewmodel.IView;
import eu.inloop.viewmodel.ViewModelHelper;
import eu.inloop.viewmodel.binding.ViewModelBindingConfig;

public abstract class ViewModelBaseActivity<T extends IView, R extends AbstractViewModel<T>, B extends ViewDataBinding> extends AppCompatActivity implements IView  {

    private ViewModelHelper<T, R> mViewModeHelper;
    private B mBinding;

    public abstract B inflateBindingLayout(LayoutInflater inflater);

    @CallSuper
    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = inflateBindingLayout(getLayoutInflater());
        mBinding.setVariable(BR.view, this);
        mBinding.setVariable(BR.viewModel, getViewModel());
        setContentView(mBinding.getRoot());

        mViewModeHelper = new ViewModelHelper<>(getViewModel());
        
        mViewModeHelper.onCreate(savedInstanceState, getIntent().getExtras());
    }

    /**
     * Call this after your view is ready - usually on the end of {@link android.app.Activity#onCreate(Bundle)}
     * @param view view
     */
    @SuppressWarnings("unused")
    public void setModelView(@NonNull final T view) {
        mViewModeHelper.setView(view);
    }

    @CallSuper
    @Override
    public void onSaveInstanceState(@NonNull final Bundle outState) {
        super.onSaveInstanceState(outState);
        mViewModeHelper.onSaveInstanceState(outState);
    }

    @CallSuper
    @Override
    public void onStart() {
        super.onStart();
        mViewModeHelper.onStart();
    }

    @CallSuper
    @Override
    public void onStop() {
        super.onStop();
        mViewModeHelper.onStop();
    }

    @CallSuper
    @Override
    public void onDestroy() {
        mViewModeHelper.onDestroy(this);
        super.onDestroy();
    }

    @NonNull
    public abstract R getViewModel();

    public B getBinding() {
        return mBinding;
    }

    @Override
    public void removeViewModel() {
        mViewModeHelper.removeViewModel();
    }

    @Nullable
    @Override
    public ViewModelBindingConfig getViewModelBindingConfig() {
        return null;
    }
}
