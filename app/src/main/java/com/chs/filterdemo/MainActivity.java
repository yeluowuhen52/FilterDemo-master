package com.chs.filterdemo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.chs.filterdemo.bean.Contact;
import com.chs.filterdemo.bean.SliderMsg;
import com.chs.filterdemo.util.HanziToPinyinUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView tv_filter;
    private DrawerLayout mDrawerLayout;
    //联系人信息
    private ArrayList<Contact> datas = new ArrayList<>();

    //被选中的
    private List<String> seletcedList;

    private FrameLayout mDrawerContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }

    private void initEvent() {
        tv_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(mDrawerContent);
            }
        });
    }

    private void initView() {
        tv_filter = (TextView) findViewById(R.id.tv_filter);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerContent = (FrameLayout) findViewById(R.id.drawer_content);

        Fragment fragment = new FilterFragment();
        FragmentManager fragmentManager = getFragmentManager();
        Bundle bundle = new Bundle();
        bundle.putString("departmentName", "");
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.drawer_content, fragment).commit();
    }


    public List<String> getSlectedList() {
        if (seletcedList == null) {
            seletcedList = new ArrayList<>();
        }
        return seletcedList;
    }

    public SliderMsg getFragmentContact() {
        return parser();
    }

    private SliderMsg parser() {
        if(datas.size() == 0){
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

            for (int i = 0; i < 10; i++) {
                Contact data = new Contact();
                data.setName("张三");
                data.setUrl("aa");
                data.setId(i);
                data.setPinyin(HanziToPinyinUtil.getPinYin(data.getName()));
                datas.add(data);
            }
        }

        return new SliderMsg(0, datas);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
