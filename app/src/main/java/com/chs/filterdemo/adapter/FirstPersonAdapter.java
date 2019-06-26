package com.chs.filterdemo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chs.filterdemo.R;
import com.chs.filterdemo.bean.Contact;

import java.util.List;


/**
 * 选择机器号Adapter
 *
 * @author Jiang
 */

public class FirstPersonAdapter extends BaseAdapter {
    private List<Contact> mList;
    private Context mContext;
    private OnItemSelectedListener onItemSelectedListener;

    /**
     * 展示条目被点击时候的回调接口
     */
    public interface OnItemSelectedListener {
        /**
         * 展示条目被点击时候的回调接口
         *
         * @param position
         */
        void OnItemSelected(int position);
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }

    public FirstPersonAdapter(Context mContext, List<Contact> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList != null ? mList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        final Contact userInfo = mList.get(position);
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_list_opeartor_person, null);
            viewHolder = new ViewHolder();
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.contact_title);
            viewHolder.tvLetter = (TextView) convertView.findViewById(R.id.contact_catalog);
            viewHolder.tvLine = (TextView) convertView.findViewById(R.id.contact_line);
            viewHolder.llItem = (LinearLayout) convertView.findViewById(R.id.llItem);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        //如果是第0个那么一定显示#号
        if (position == 0) {
            Contact prevData = mList.get(0);
            viewHolder.tvLetter.setVisibility(View.VISIBLE);
            viewHolder.tvLetter.setText(prevData.getFirstChar() + "");
            viewHolder.tvLine.setVisibility(View.VISIBLE);
        } else {
            //如果和上一个item的首字母不同，则认为是新分类的开始
            Contact prevData = mList.get(position - 1);
            if (userInfo.getFirstChar() != prevData.getFirstChar()) {
                viewHolder.tvLetter.setVisibility(View.VISIBLE);
                viewHolder.tvLetter.setText("" + userInfo.getFirstChar());
                viewHolder.tvLine.setVisibility(View.VISIBLE);
            } else {
                viewHolder.tvLetter.setVisibility(View.GONE);
                viewHolder.tvLine.setVisibility(View.GONE);
            }
        }

        if (userInfo.isSelected()) {
            viewHolder.llItem.setBackgroundColor(Color.BLUE);
        } else {
            viewHolder.llItem.setBackgroundColor(Color.WHITE);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemSelectedListener != null) {
                    onItemSelectedListener.OnItemSelected(position);
                    userInfo.setSelected(!userInfo.isSelected());
                    notifyList();
                }
            }
        });
        viewHolder.tvName.setText(userInfo.getName());

        return convertView;
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            char firstChar = mList.get(i).getFirstChar();
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }

    public void notifyList() {
        notifyDataSetChanged();
    }

    public static class ViewHolder {
        TextView tvName;
        TextView tvLetter;
        TextView tvLine;
        LinearLayout llItem;
    }
}
