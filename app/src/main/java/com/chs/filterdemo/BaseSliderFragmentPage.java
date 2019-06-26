package com.chs.filterdemo;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

/**
 * 侧栏Fragment基类
 *
 * @author Jiang
 * @date 2019-06-26
 */
abstract public class BaseSliderFragmentPage extends Fragment {
    //弱引用，防止内存泄漏
    private WeakReference<Activity> actReference;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actReference = new WeakReference<>(getActivity());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public Activity getMyActivity() {
        return actReference.get();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = onMyCreateView(inflater);
        return view;
    }

    abstract public View onMyCreateView(LayoutInflater inflater);
}
