package com.imadassamani.to_dolist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class CheckBoxListView {
    String listItem = null;
    boolean check = false;

    public CheckBoxListView(String listItem, boolean check) {
        super();
        this.listItem = listItem;
        this.check = check;
    }

    public String getItem() {
        return this.listItem;
    }

    public void setItem(String listItem) {
        this.listItem = listItem;
    }

    public boolean isChecked() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}

class CheckBoxListViewAdapter extends ArrayAdapter<CheckBoxListView> {
    private ArrayList<CheckBoxListView> list;
    Context ctx;

    CheckBoxListViewAdapter(Context ctx, int textViewResourceId,
                            ArrayList<CheckBoxListView> list) {
        super(ctx, textViewResourceId, list);
        this.ctx = ctx;
        this.list = new ArrayList<>();
        this.list= list;
    }

    private class ViewHolder {
        TextView item;
        CheckBox cb;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.v("Logg", "Null view");
        ViewHolder holder = null;
        LayoutInflater li = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = li.inflate(R.layout.check_box_list_view, null);
        holder = new ViewHolder();
        holder.item = (TextView) convertView.findViewById(R.id.list_view_item);
        holder.cb = (CheckBox) convertView.findViewById(R.id.list_view_item_checkbox);
        convertView.setTag(holder);
        CheckBoxListView temp = list.get(position);
        holder.item.setText(temp.getItem());
        holder.cb.setChecked(temp.isChecked());
        holder.item.setTag(temp);
        return convertView;
    }
}