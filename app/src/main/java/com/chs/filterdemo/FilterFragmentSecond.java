package com.chs.filterdemo;

import android.databinding.DataBindingUtil;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chs.filterdemo.adapter.FirstPersonAdapter;
import com.chs.filterdemo.bean.Contact;
import com.chs.filterdemo.bean.SliderMsg;
import com.chs.filterdemo.databinding.FragmentDepartmentSelectBinding;
import com.chs.filterdemo.widget.SideBar;

/**
 * 二级Fragment
 *
 * @author Jiang
 * @date 2019-06-26
 */

public class FilterFragmentSecond extends BaseSliderFragmentPage implements SideBar.OnTouchingLetterChangedListener, TextWatcher {
    private FirstPersonAdapter mAdapter;
    private SliderMsg sliderMsg;
    private FragmentDepartmentSelectBinding binding;
    private View view;

    @Override
    public View onMyCreateView(LayoutInflater inflater, ViewGroup container) {
//        View view = inflater.inflate(R.layout.fragment_department_select, null);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_department_select, container, false);
        view = binding.getRoot();
        initView(view);
        return view;
    }


    private void initView(View view) {
        binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStackImmediate();
            }
        });
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showNext();
                getFragmentManager().popBackStackImmediate();
            }
        });


        binding.schoolFriendSidrbar.setTextView(binding.schoolFriendDialog);
        binding.schoolFriendSidrbar.setOnTouchingLetterChangedListener(this);

        // 给listView设置adapter
//        mFooterView = (TextView) View.inflate(getActivity(), R.layout.item_list_contact_count, null);
//        binding.lvShow.addFooterView(mFooterView);

        parser();
    }

    private void parser() {
        sliderMsg = ((MainActivity) getMyActivity()).getFragmentContact();
        binding.btnConfirm.setText("确定(" + getSelectedNum() + "条)");

//        mFooterView.setText(datas.size() + "位联系人");
        mAdapter = new FirstPersonAdapter(getMyActivity(), sliderMsg.getContacts());
        binding.lvShow.setAdapter(mAdapter);


        mAdapter.setOnItemSelectedListener(new FirstPersonAdapter.OnItemSelectedListener() {
            @Override
            public void OnItemSelected(int position) {
                binding.btnConfirm.setText("确定(" + getSelectedNum() + "条)");
            }
        });

    }

    public int getSelectedNum() {
        int selectNum = 0;
        //计算选中数量
        for (Contact contact : sliderMsg.getContacts()) {
            if (contact.isSelected()) {
                selectNum++;
            }
        }
        return selectNum;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onTouchingLetterChanged(String s) {
        int position = 0;
        // 该字母首次出现的位置
        if (mAdapter != null) {
            position = mAdapter.getPositionForSection(s.charAt(0));
        }
        if (position != -1) {
            binding.lvShow.setSelection(position);
        } else if (s.contains("#")) {
            binding.lvShow.setSelection(0);
        }
    }

}
