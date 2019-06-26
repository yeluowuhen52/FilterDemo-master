package com.chs.filterdemo;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 侧栏Fragment基类
 *
 * @author Jiang
 * @date 2019-06-26
 */
abstract public class BaseSliderFragmentPage extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view  = onMyCreateView(inflater);
        return view;
    }

    abstract public View onMyCreateView(LayoutInflater inflater);
}
