package com.example.piash.primarytutorials.Adapter;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.piash.primarytutorials.R;
import com.example.piash.primarytutorials.Tutorial;

import java.util.List;

public class ListAdapterClass extends BaseAdapter {

    Context context;
    List<Tutorial> valueList;


    public ListAdapterClass(List<Tutorial> listValue, Context context) {
        this.context = context;
        this.valueList = listValue;
    }

    @Override
    public int getCount() {
        return this.valueList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.valueList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewItem viewItem = null;

        if (convertView == null) {
            viewItem = new ViewItem();

            LayoutInflater layoutInfiater = (LayoutInflater) this.context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInfiater.inflate(R.layout.listview_activity, null);

            viewItem.TextViewTutorialTopic = convertView.findViewById(R.id.tv1);

            viewItem.TextViewTutorialClass = convertView.findViewById(R.id.tv2);

            convertView.setTag(viewItem);
        } else {
            viewItem = (ViewItem) convertView.getTag();
        }

        viewItem.TextViewTutorialTopic.setText(valueList.get(position).TutorialTopic);
        viewItem.TextViewTutorialClass.setText("Class " + valueList.get(position).TutorialClasses);

        return convertView;
    }
}

class ViewItem {
    TextView TextViewTutorialTopic;
    TextView TextViewTutorialClass;

}