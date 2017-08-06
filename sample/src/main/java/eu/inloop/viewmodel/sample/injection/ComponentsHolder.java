package eu.inloop.viewmodel.sample.injection;

import android.support.annotation.Nullable;

import java.util.HashMap;

import eu.inloop.viewmodel.sample.injection.component.BaseActivityComponent;
import eu.inloop.viewmodel.sample.injection.component.BaseFragmentComponent;

public class ComponentsHolder<A extends BaseActivityComponent, F extends BaseFragmentComponent> {

    private A mActivityComponent;

    private HashMap<String, F> mFragmentComponents = new HashMap<>();

    public ComponentsHolder(A activityComponent) {
        mActivityComponent = activityComponent;
    }

    public void addFragmentComponent(String id, F fragmentComponent) {
        mFragmentComponents.put(id, fragmentComponent);
    }

    public void removeFragmentComponent(String id) {
        mFragmentComponents.remove(id);
    }

    public A getActivityComponent() {
        return mActivityComponent;
    }

    @Nullable
    public F getFragmentComponent(String id) {
        return mFragmentComponents.get(id);
    }
}
