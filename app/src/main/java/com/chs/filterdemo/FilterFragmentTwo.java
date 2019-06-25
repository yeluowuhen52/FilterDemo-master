package com.chs.filterdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.chs.filterdemo.adapter.FirstPersonAdapter;
import com.chs.filterdemo.bean.Contact;
import com.chs.filterdemo.util.HanziToPinyinUtil;
import com.chs.filterdemo.widget.SideBar;

import java.util.ArrayList;

/**
 * 作者：chs on 2016/10/22 16:01
 * 邮箱：657083984@qq.com
 */

public class FilterFragmentTwo extends Fragment implements SideBar.OnTouchingLetterChangedListener, TextWatcher {
    //    private ListView lv_department;
    private ImageView iv_back;
//    private String departmentName = "";
//    String[] list;

    private ListView mListView;
    private TextView mFooterView;
    private ArrayList<Contact> datas = new ArrayList<>();
    private FirstPersonAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_department_select, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        iv_back = (ImageView) view.findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showNext();
                getActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        });

        SideBar mSideBar = (SideBar) view.findViewById(R.id.school_friend_sidrbar);
        TextView mDialog = (TextView) view.findViewById(R.id.school_friend_dialog);
        mListView = (ListView) view.findViewById(R.id.school_friend_member);
        mSideBar.setTextView(mDialog);
        mSideBar.setOnTouchingLetterChangedListener(this);

        // 给listView设置adapter
        mFooterView = (TextView) View.inflate(getActivity(), R.layout.item_list_contact_count, null);
        mListView.addFooterView(mFooterView);

        parser();
    }

    private void parser() {
        for (int i = 0; i < 10; i++) {
            Contact data = new Contact();
            data.setName("张三");
            data.setUrl("aa");
            data.setId(i);
            data.setPinyin(HanziToPinyinUtil.getPinYin(data.getName()));
            datas.add(data);
        }
        for (int i = 0; i < 10; i++) {
            Contact data = new Contact();
            data.setName("李三");
            data.setUrl("aa");
            data.setId(i);
            data.setPinyin(HanziToPinyinUtil.getPinYin(data.getName()));
            datas.add(data);
        }

        for (int i = 0; i < 10; i++) {
            Contact data = new Contact();
            data.setName("王三");
            data.setUrl("aa");
            data.setId(i);
            data.setPinyin(HanziToPinyinUtil.getPinYin(data.getName()));
            datas.add(data);
        }
        mFooterView.setText(datas.size() + "位联系人");
        mAdapter = new FirstPersonAdapter(getActivity(), datas);
        mListView.setAdapter(mAdapter);

      mAdapter.setOnItemSelectedListener(new FirstPersonAdapter.OnItemSelectedListener() {
          @Override
          public void OnItemSelected(int position) {

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
