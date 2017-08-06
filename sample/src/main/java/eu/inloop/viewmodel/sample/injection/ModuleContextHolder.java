package eu.inloop.viewmodel.sample.injection;

import java.lang.ref.WeakReference;

public class ModuleContextHolder<T> {

    private WeakReference<T> mRef;

    public ModuleContextHolder(T context) {
        mRef = new WeakReference<>(context);
    }

    public T get() {
        return mRef.get();
    }

    public void set(T context) {
        mRef = new WeakReference<>(context);
    }
}
