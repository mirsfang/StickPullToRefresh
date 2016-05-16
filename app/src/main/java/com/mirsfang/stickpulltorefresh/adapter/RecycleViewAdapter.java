package com.mirsfang.stickpulltorefresh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.mirsfang.stickpulltorefresh.R;


/**
 * @author 顾修忠-guxiuzhong@youku.com/gfj19900401@163.com
 * @Title: RecycleViewAdapter
 * @Package com.gxz.stickynavlayout.adapter
 * @Description:
 * @date 16/1/1
 * @time 下午2:51
 */
public class RecycleViewAdapter extends BaseExpandableListAdapter {
    private Context context;

    public  RecycleViewAdapter(Context context){
        this.context=context;
    }

    @Override
    public int getGroupCount() {
        return 10;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 10;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView=LayoutInflater.from(context).inflate(R.layout.rv_item,null);
        TextView textView= (TextView) convertView.findViewById(R.id.id_tv);
        textView.setText("这是父View"+groupPosition);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView=LayoutInflater.from(context).inflate(R.layout.rv_item,null);
        TextView textView= (TextView) convertView.findViewById(R.id.id_tv);
        textView.setText("     这是子View"+groupPosition);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
