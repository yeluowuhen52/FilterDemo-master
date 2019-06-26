package com.chs.filterdemo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.databinding.DataBindingUtil;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chs.filterdemo.bean.Contact;
import com.chs.filterdemo.databinding.FragmentPatrolFilterBinding;
import com.chs.filterdemo.util.DensityUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * 主Fragment
 *
 * @author Jiang
 * @date 2019-06-26
 */

public class FilterFragment extends BaseSliderFragmentPage {
    private DrawerLayout mDrawerLayout;
    private FrameLayout mDrawerContent;
    private ArrayList<Contact> datas = new ArrayList<>();

    private TagAdapter<Contact> tagAdapter;
    private Set<Integer> set;
    private Fragment fragmentSecond;

    private ArrayList<Contact> contactBeans;
    private ArrayList<Contact> contactShow;

    private FragmentPatrolFilterBinding binding;

    @Override
    public View onMyCreateView(LayoutInflater inflater, ViewGroup container) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_patrol_filter, container, false);
        initEvent();
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        contactBeans = (ArrayList<Contact>) ((MainActivity) getMyActivity()).getFragmentContact().getContacts();
        initView();
    }

    private void initEvent() {
        binding.rlSupply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNext();
            }
        });
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.closeDrawer(mDrawerContent);
            }
        });
    }

    private void initView() {
        mDrawerContent = (FrameLayout) getMyActivity().findViewById(R.id.drawer_content);
//        rl_department = (RelativeLayout) view.findViewById(R.id.rl_department);
        binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getMyActivity(), binding.flowLayout.getSelectedList().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        final LayoutInflater mInflater = LayoutInflater.from(getActivity());
        binding.flowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getMyActivity(), "FlowLayout Clicked", Toast.LENGTH_SHORT).show();
            }
        });


        binding.nextFlowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getMyActivity(), "FlowLayout Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        if (contactShow == null) {
            contactShow = new ArrayList<>();
        }
        contactShow.clear();
        //不允许显示超过6个
        if (contactBeans.size() > 6) {
            for (int i = 0; i < 6; i++) {
                contactShow.add(contactBeans.get(i));
            }
        } else {
            contactShow.addAll(contactBeans);
        }
        if (tagAdapter == null) {
            tagAdapter = new TagAdapter<Contact>(contactShow) {
                @Override
                public View getView(FlowLayout parent, int position, Contact contact) {
                    TextView tvLayout = (TextView) mInflater.inflate(R.layout.item_slider_tv,
                            binding.nextFlowLayout, false);
                    float dimension = getResources().getDimension(R.dimen.slider_width);
                    tvLayout.setWidth((int) (dimension / 3 - DensityUtil.dp2px(15)));
                    tvLayout.setGravity(Gravity.CENTER);
                    tvLayout.setText(contact.getName().length() < 5 ? "  " + contact.getName() + "  " :
                            contact.getName().substring(0, 4) + "...");
                    return tvLayout;
                }
            };
        }
        if (set == null) {
            set = new HashSet();
        }

        for (Contact contact : contactBeans) {
            if (contact.isSelected()) {
                set.add(contactBeans.indexOf(contact));
            }
        }
        tagAdapter.setSelectedList(set);

        binding.nextFlowLayout.setAdapter(tagAdapter);
        //tagView监听
        binding.nextFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                contactBeans.get(position).setSelected(!contactBeans.get(position).isSelected());
                return false;
            }
        });
    }

    /**
     * 跳转Fragment
     */
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

