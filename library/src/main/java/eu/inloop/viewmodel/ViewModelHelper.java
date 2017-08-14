package eu.inloop.viewmodel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

public class ViewModelHelper<T extends IView, R extends AbstractViewModel<T>> {

    private R mViewModel;

    private boolean mModelRemoved;
    private boolean mOnSaveInstanceCalled;

    public ViewModelHelper(R viewModel) {
        mViewModel = viewModel;
    }

    /**
     * Call from {@link android.app.Activity#onCreate(android.os.Bundle)} or
     * {@link android.support.v4.app.Fragment#onCreate(android.os.Bundle)}
     *
     * @param savedInstanceState savedInstance state from {@link Activity#onCreate(Bundle)} or
     *                           {@link Fragment#onCreate(Bundle)}
     * @param arguments          pass {@link Fragment#getArguments()}  or
     *                           {@link Activity#getIntent()}.{@link Intent#getExtras() getExtras()}
     */
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable Bundle arguments) {
        // no viewmodel for this fragment
        if (mViewModel == null) {
            return;
        }

        // screen (activity/fragment) not created for first time
        if (savedInstanceState != null) {
            mOnSaveInstanceCalled = false;
        }

        // detect that the system has killed the app - saved instance is not null, but the model was recreated
        if (BuildConfig.DEBUG && savedInstanceState != null) {
            Log.d("model", "Fragment recreated by system or ViewModelStatePagerAdapter - restoring viewmodel"); //NON-NLS
        }
        mViewModel.onCreate(arguments, savedInstanceState);
    }

    /**
     * Call from {@link android.support.v4.app.Fragment#onViewCreated(android.view.View, android.os.Bundle)}
     * or {@link android.app.Activity#onCreate(android.os.Bundle)}
     *
     * @param view view
     */
    public void setView(@NonNull final T view) {
        if (mViewModel == null) {
            //no viewmodel for this fragment
            return;
        }
        mViewModel.onBindView(view);
    }

    /**
     * Use in case this model is associated with an {@link android.support.v4.app.Fragment}
     * Call from {@link android.support.v4.app.Fragment#onDestroyView()}. Use in case model is associated
     * with Fragment
     *
     * @param fragment fragment
     */
    public void onDestroyView(@NonNull Fragment fragment) {
        if (mViewModel == null) {
            //no viewmodel for this fragment
            return;
        }
        mViewModel.clearView();
        if (fragment.getActivity() != null && fragment.getActivity().isFinishing()) {
            removeViewModel();
        }
    }

    /**
     * Use in case this model is associated with an {@link android.support.v4.app.Fragment}
     * Call from {@link android.support.v4.app.Fragment#onDestroy()}
     *
     * @param fragment fragment
     */
    public void onDestroy(@NonNull final Fragment fragment) {
        if (mViewModel == null) {
            //no viewmodel for this fragment
            return;
        }
        if (fragment.getActivity().isFinishing()) {
            removeViewModel();
        } else if (fragment.isRemoving() && !mOnSaveInstanceCalled) {
            // The fragment can be still in backstack even if isRemoving() is true.
            // We check mOnSaveInstanceCalled - if this was not called then the fragment is totally removed.
            if (BuildConfig.DEBUG) {
                Log.d("mode", "Removing viewmodel - fragment replaced"); //NON-NLS
            }
            removeViewModel();
        }
    }

    /**
     * Use in case this model is associated with an {@link android.app.Activity}
     * Call from {@link android.app.Activity#onDestroy()}
     *
     * @param activity activity
     */
    public void onDestroy(@NonNull final Activity activity) {
        if (mViewModel == null) {
            //no viewmodel for this fragment
            return;
        }
        mViewModel.clearView();
        if (activity.isFinishing()) {
            removeViewModel();
        }
    }

    /**
     * Call from {@link android.app.Activity#onStop()} or {@link android.support.v4.app.Fragment#onStop()}
     */
    public void onStop() {
        if (mViewModel == null) {
            //no viewmodel for this fragment
            return;
        }
        mViewModel.onStop();
    }

    /**
     * Call from {@link android.app.Activity#onStart()} ()} or {@link android.support.v4.app.Fragment#onStart()} ()}
     */
    public void onStart() {
        if (mViewModel == null) {
            //no viewmodel for this fragment
            return;
        }
        mViewModel.onStart();
    }

    /**
     * Returns the current ViewModel instance associated with the Fragment or Activity.
     * Throws an {@link IllegalStateException} in case the ViewModel is null. This can happen
     * if you call this method too soon - before {@link Activity#onCreate(Bundle)} or {@link Fragment#onCreate(Bundle)}
     * or this {@link ViewModelHelper} is not properly setup.
     *
     * @return {@link R}
     */
    @NonNull
    public R getViewModel() {
        if (null == mViewModel) {
            throw new IllegalStateException("ViewModel is not ready. Are you calling this method before Activity/Fragment onCreate?"); //NON-NLS
        }
        return mViewModel;
    }

    /**
     * Call from {@link android.app.Activity#onSaveInstanceState(android.os.Bundle)}
     * or {@link android.support.v4.app.Fragment#onSaveInstanceState(android.os.Bundle)}.
     * This allows the model to save its state.
     *
     * @param bundle bundle
     */
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        if (mViewModel != null) {
            mViewModel.onSaveInstanceState(bundle);
            mOnSaveInstanceCalled = true;
        }
    }

    public void removeViewModel() {
        if (mViewModel != null && !mModelRemoved) {
            mViewModel.onDestroy();
            mModelRemoved = true;
        }
    }
}
