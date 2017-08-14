package eu.inloop.viewmodel;

import eu.inloop.viewmodel.base.ViewModelBaseActivity;
import eu.inloop.viewmodel.base.ViewModelBaseFragment;

/**
 * Any Activity or Fragment that needs a ViewModel needs to implement this interface.
 * You don't need to implement it yourself - use {@link ViewModelBaseActivity} and
 * {@link ViewModelBaseFragment} instead.
 */
public interface IView {
    /**
     * Implement this method to remove the ViewModel associated with the Fragment or Activity.
     * This is usually implemented by calling {@link ViewModelHelper#removeViewModel()},
     * see {@link ViewModelBaseActivity#removeViewModel()} and {@link ViewModelBaseFragment#removeViewModel()}.
     */
    void removeViewModel();
}
