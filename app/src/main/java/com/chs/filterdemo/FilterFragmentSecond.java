package com.chs.filterdemo;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.chs.filterdemo.adapter.FirstPersonAdapter;
import com.chs.filterdemo.bean.Contact;
import com.chs.filterdemo.bean.SliderMsg;
import com.chs.filterdemo.widget.SideBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 二级Fragment
 *
 * @author Jiang
 * @date 2019-06-26
 */

public class FilterFragmentSecond extends BaseSliderFragmentPage implements SideBar.OnTouchingLetterChangedListener, TextWatcher {
    //    private ListView lv_department;
    private ImageView iv_back;
//    private String departmentName = "";
//    String[] list;

    private ListView mListView;
    //被选中的
    private List<String> seletcedList;
    //    private TextView mFooterView;
    private ArrayList<Contact> datas = new ArrayList<>();
    private FirstPersonAdapter mAdapter;

    private Button btn_confirm;

    @Override
    public View onMyCreateView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_department_select, null);
        initView(view);
        return view;
    }


    private void initView(View view) {
        seletcedList = ((MainActivity) getMyActivity()).getSlectedList();
        btn_confirm = (Button) view.findViewById(R.id.btn_confirm);
        iv_back = (ImageView) view.findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showNext();
                getFragmentManager().popBackStackImmediate();
            }
        });


        SideBar mSideBar = (SideBar) view.findViewById(R.id.school_friend_sidrbar);
        TextView mDialog = (TextView) view.findViewById(R.id.school_friend_dialog);
        mListView = (ListView) view.findViewById(R.id.school_friend_member);
        mSideBar.setTextView(mDialog);
        mSideBar.setOnTouchingLetterChangedListener(this);

        // 给listView设置adapter
//        mFooterView = (TextView) View.inflate(getActivity(), R.layout.item_list_contact_count, null);
//        mListView.addFooterView(mFooterView);

        parser();
    }

    private void parser() {
        SliderMsg sliderMsg = ((MainActivity) getMyActivity()).getFragmentContact();

//        mFooterView.setText(datas.size() + "位联系人");
        mAdapter = new FirstPersonAdapter(getMyActivity(), sliderMsg.getContacts());
        mListView.setAdapter(mAdapter);

        mAdapter.setOnItemSelectedListener(new FirstPersonAdapter.OnItemSelectedListener() {
            @Override
            public void OnItemSelected(int position) {
                seletcedList.add(String.valueOf(position));
                Collections.sort(seletcedList);
                btn_confirm.setText("确定(" + seletcedList.size() + "条)");
            }
        });

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
            mListView.setSelection(position);
        } else if (s.contains("#")) {
            mListView.setSelection(0);
        }
    }

}
