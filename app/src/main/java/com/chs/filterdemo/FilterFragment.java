package com.chs.filterdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;

/**
 * 作者：chs on 2016/10/10 10:07
 * 邮箱：657083984@qq.com
 */

public class FilterFragment extends Fragment {
    private DrawerLayout mDrawerLayout;
    private FrameLayout mDrawerContent;
    private RelativeLayout rl_department;
    private ImageView iv_back;
    private TextView department_selected;
    private Button btn_confirm;

//    private String[] mVals = new String[]{"通常入库", "直销入库"};

    private ArrayList<TestBean> testBeans;

    private TagFlowLayout mFlowLayout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_patrol_filter, null);
        initView(view);
        initEvent();
        return view;
    }

    private void initEvent() {
        rl_department.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNext();
            }
        });
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.closeDrawer(mDrawerContent);
            }
        });
    }

    private void initView(View view) {
        testBeans = new ArrayList<>();
        testBeans.add(new TestBean("通常入库"));
        testBeans.add(new TestBean("直销入库"));

        String departmentName = getArguments().getString("departmentName");
        mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        mDrawerContent = (FrameLayout) getActivity().findViewById(R.id.drawer_content);
        rl_department = (RelativeLayout) view.findViewById(R.id.rl_department);
        iv_back = (ImageView) view.findViewById(R.id.iv_back);
        department_selected = (TextView) view.findViewById(R.id.department_selected);
        btn_confirm = (Button) view.findViewById(R.id.btn_confirm);

        if (!TextUtils.isEmpty(departmentName)) {
            department_selected.setText(departmentName);
            department_selected.setTextColor(getResources().getColor(R.color.blue_text));
        }

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), mFlowLayout.getSelectedList().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        mFlowLayout = (TagFlowLayout) view.findViewById(R.id.id_flowlayout);
        mFlowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "FlowLayout Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        final LayoutInflater mInflater = LayoutInflater.from(getActivity());

        mFlowLayout.setAdapter(new TagAdapter<TestBean>(testBeans) {
            @Override
            public View getView(FlowLayout parent, int position, TestBean s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.tv,
                        mFlowLayout, false);
                tv.setText(s.getName());
                return tv;
            }

        });
    }


    private void showNext() {
        Fragment fragment = new FilterFragmentTwo();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.right_in, R.anim.left_out, R.anim.left_in, R.anim.right_out);
        fragmentTransaction.replace(R.id.drawer_content, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
    }

}
