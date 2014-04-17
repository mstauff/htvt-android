package htvt.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;
import htvt.domain.*;
import main.java.htvt.R;
import roboguice.fragment.RoboFragment;

import java.util.List;

public class CompanionshipListFragment extends RoboFragment {
    private District district;
    private View companionshipView;
    private ListView companionshipListView;

    public CompanionshipListFragment(District district) {
        super();
        this.district = district;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        companionshipView = inflater.inflate(R.layout.companionship_list, container, false);
        ((TextView)companionshipView.findViewById(R.id.companionship_list_title)).setText(district.getName());
        companionshipListView = (ListView) companionshipView.findViewById(R.id.companionship_list);
        CompanionshipListAdapter adapter = new CompanionshipListAdapter(getActivity(), R.layout.companionship_list_item, district.getCompanionships());
        companionshipListView.setAdapter(adapter);

        return companionshipView;
    }

    private class CompanionshipListAdapter extends ObjectListAdapter {
        Context context;
        int viewId;
        LayoutInflater inflater;

        public CompanionshipListAdapter(Context context, int viewResourceId,List<Companionship> list) {
            super(context, R.layout.list_item, list);
            this.context = context;
            viewId = viewResourceId;
            inflater = ((Activity)context).getLayoutInflater();
        }
        public CompanionshipListAdapter(Context context,int viewResourceId,Companionship[] list) {
            super(context, R.layout.list_item, list);
            this.context = context;
            viewId = viewResourceId;
            inflater = ((Activity)context).getLayoutInflater();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                convertView = inflater.inflate(viewId, parent, false);
            }
            TextView textView = (TextView) convertView.findViewById(R.id.companionship_text);
            textView.setText(((TextView) super.getView(position, textView, (ViewGroup)convertView)).getText());

            Companionship companionship = (Companionship) super.getItem(position);
            ListView listView = (ListView) convertView.findViewById(R.id.assignment_list);
            AssignmentListAdapter adapter = new AssignmentListAdapter(context, R.layout.companionship_family_item, companionship.getAssignments());
            listView.setAdapter(adapter);

            return convertView;
        }
    }

    private class AssignmentListAdapter extends ObjectListAdapter {
        int viewId;
        LayoutInflater inflater;
        public AssignmentListAdapter(Context context, int viewResourceId,List<? extends Listable> list) {
            super(context, R.layout.list_item, list);
            viewId = viewResourceId;
            inflater = ((Activity)context).getLayoutInflater();
        }
        public AssignmentListAdapter(Context context,int viewResourceId,Listable[] list) {
            super(context, R.layout.list_item, list);
            viewId = viewResourceId;
            inflater = ((Activity)context).getLayoutInflater();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                convertView = inflater.inflate(viewId, parent, false);
            }
            TextView textView = (TextView) convertView.findViewById(R.id.assignment_text);
            textView.setText(((TextView)super.getView(position, textView, (ViewGroup)convertView)).getText());

            Assignment assignment = (Assignment) super.getItem(position);
            Visit visit = getCurrentVisit(assignment);
            final ToggleButton yesButton = (ToggleButton) convertView.findViewById(R.id.yesButton);
            final ToggleButton noButton = (ToggleButton) convertView.findViewById(R.id.noButton);
            yesButton.setChecked(visit.getVisited() == 1);
            noButton.setChecked(visit.getVisited() == 0);
            yesButton.setOnCheckedChangeListener(new ToggleButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b) {
                        noButton.setChecked(false);
                    }
                }
            });
            noButton.setOnCheckedChangeListener(new ToggleButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b) {
                        yesButton.setChecked(false);
                    }
                }
            });


            return convertView;
        }

        private Visit getCurrentVisit(Assignment assignment) {
            Visit visit = assignment.getVisits().get(0);
            return visit;
        }
    }
}
