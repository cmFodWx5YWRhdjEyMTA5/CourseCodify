package com.srishti.srish.coursecodify_v1;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;


import java.util.ArrayList;

/**
 * Created by User on 26/11/2017.
 */

 class DirectoryExpandableListAdapter implements ExpandableListAdapter {

     private Context context;
     private ArrayList<String> groupName;
     private ArrayList<String> subdirectoryName;
     CreateDirectories createDirectories = new CreateDirectories();
     TextView eventName;

     public DirectoryExpandableListAdapter(Context context, ArrayList<String> groupName, ArrayList<String> subdirectoreyName){

         this.context = context;
         this.groupName = groupName;
         this.subdirectoryName = subdirectoreyName;
            Log.i("Constructor", "called");
     }
    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getGroupCount() {
        return groupName.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return subdirectoryName.size();
    }

    @Override
    public Object getGroup(int i) {
        return groupName.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return subdirectoryName.get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.eventname_layout_expandable, null);
        }

        eventName = (TextView) view.findViewById(R.id.eventName);
        Log.i("Here I am", "getGroupView");
        eventName.setText(groupName.get(i));
        return view;
    }

    @Override
    public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {
         if(view == null){

             LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             view = inflater.inflate(R.layout.subdirectory_events_layout_expandable, null);
         }

        final TextView childName = (TextView) view.findViewById(R.id.eventSubdirectory);
        childName.setText(subdirectoryName.get(i1));

        TextView childCount = (TextView) view.findViewById(R.id.countChildItem);
        Log.i( "Event Name --:"+ eventName.getText(), childName.getText()+"");
        int numberOfMaterial = createDirectories.readAllDirectoryName(eventName.getText().toString(),childName.getText().toString()).size();

        if(numberOfMaterial > 0)
        childCount.setText("   ["+numberOfMaterial+"]");

        childName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(childName.getText().toString().equals("Images")){
                    Intent intent = new Intent(view.getContext() , AllListActivity.class);
                    intent.putExtra("CalendarEvent",groupName.get(i) );
                    intent.putExtra("Material","Images");
                    context.startActivity(intent);
                }
                else if(childName.getText().toString().equals("Notes")){

                    Intent intent = new Intent(view.getContext() , AllListActivity.class);
                    intent.putExtra("CalendarEvent",groupName.get(i) );
                    intent.putExtra("Material","Notes");
                    context.startActivity(intent);

                }

                else if(childName.getText().toString().equals("Recordings")){

                    Intent intent = new Intent(view.getContext() , AllListActivity.class);
                    intent.putExtra("CalendarEvent",groupName.get(i) );
                    intent.putExtra("Material","Recordings");
                    context.startActivity(intent);

                }

                else if(childName.getText().toString().equals("All Materials")){

                    Intent intent = new Intent(view.getContext() , AllListActivity.class);
                    intent.putExtra("CalendarEvent",groupName.get(i) );
                    intent.putExtra("Material", "All Materials");

                    context.startActivity(intent);

                }

            }
        });

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int i) {

    }

    @Override
    public void onGroupCollapsed(int i) {

    }

    @Override
    public long getCombinedChildId(long l, long l1) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long l) {
        return 0;
    }
}
