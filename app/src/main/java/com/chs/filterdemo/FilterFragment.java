package com.chs.filterdemo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chs.filterdemo.bean.Contact;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;

/**
 * 主Fragment
 *
 * @author Jiang
 * @date 2019-06-26
 */

public class FilterFragment extends BaseSliderFragmentPage {
    private DrawerLayout mDrawerLayout;
    private FrameLayout mDrawerContent;
    private RelativeLayout rl_department;
    private RelativeLayout rlSupply;
    private ImageView iv_back;
    private TextView department_selected;
    private Button btn_confirm;
    private ArrayList<Contact> datas = new ArrayList<>();

    private Fragment fragmentSecond;

//    private String[] mVals = new String[]{"通常入库", "直销入库"};

    private ArrayList<TestBean> testBeans;
    private ArrayList<TestBean> testBeansTest;

    private TagFlowLayout mFlowLayout;
    private TagFlowLayout next_flowlayout;

    @Override
    public View onMyCreateView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_patrol_filter, null);
        initView(view);
        initEvent();
        return view;
    }

    private void initEvent() {
        rlSupply.setOnClickListener(new View.OnClickListener() {
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

        testBeansTest = new ArrayList<>();
        testBeansTest.add(new TestBean("通常入库"));
        testBeansTest.add(new TestBean("直销入库sccevvrgq"));
        testBeansTest.add(new TestBean("直销入库"));
        testBeansTest.add(new TestBean("直销入库"));
        testBeansTest.add(new TestBean("直销入库"));
        testBeansTest.add(new TestBean("直销入库"));
        testBeansTest.add(new TestBean("直销入库"));
        testBeansTest.add(new TestBean("直销入库"));
        testBeansTest.add(new TestBean("直销入库"));
        testBeansTest.add(new TestBean("直销入库"));
        testBeansTest.add(new TestBean("直销入库"));
        testBeansTest.add(new TestBean("直销入库"));

        String departmentName = getArguments().getString("departmentName");
        mDrawerLayout = (DrawerLayout) getMyActivity().findViewById(R.id.drawer_layout);
        mDrawerContent = (FrameLayout) getMyActivity().findViewById(R.id.drawer_content);
        rl_department = (RelativeLayout) view.findViewById(R.id.rl_department);
        rlSupply = (RelativeLayout) view.findViewById(R.id.rlSupply);
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
                Toast.makeText(getMyActivity(), mFlowLayout.getSelectedList().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        final LayoutInflater mInflater = LayoutInflater.from(getActivity());
        mFlowLayout = (TagFlowLayout) view.findViewById(R.id.id_flowlayout);
        next_flowlayout = (TagFlowLayout) view.findViewById(R.id.next_flowlayout);

        mFlowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getMyActivity(), "FlowLayout Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        mFlowLayout.setAdapter(new TagAdapter<TestBean>(testBeans) {
            @Override
            public View getView(FlowLayout parent, int position, TestBean s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.tv,
                        mFlowLayout, false);
                tv.setText(s.getName());
                return tv;
            }
        });

        next_flowlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getMyActivity(), "FlowLayout Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        ArrayList<TestBean> testBeansShow = new ArrayList<>();
        if (testBeansTest.size() > 6) {
            for (int i = 0; i < 6; i++) {
                testBeansShow.add(testBeansTest.get(i));
            }
        } else {
            testBeansShow.addAll(testBeansTest);
        }

        next_flowlayout.setAdapter(new TagAdapter<TestBean>(testBeansShow) {
            @Override
            public View getView(FlowLayout parent, int position, TestBean s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.tv,
                        next_flowlayout, false);
                tv.setText(s.getName().length() < 5 ? "  " + s.getName() + "  " : s.getName().substring(0, 4) + "...");
                return tv;
            }
        });
    }


    private void showNext() {
        if (fragmentSecond == null) {
            fragmentSecond = new FilterFragmentSecond();
        }
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.animator.right_in, R.animator.left_out, R.animator.left_in, R.animator.right_out);
        fragmentTransaction.replace(R.id.drawer_content, fragmentSecond);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
    }




}

