package htvt.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import htvt.domain.District;
import htvt.domain.HTVTDB;
import htvt.domain.ObjectListAdapter;
import main.java.htvt.R;
import roboguice.fragment.RoboFragment;

import javax.inject.Inject;
import java.util.List;

public class DistrictListFragment extends RoboFragment {
    @Inject
    private HTVTDB db;

    private List<District> districts;
    private ListView districtListView;
    private View districtListLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        districtListLayout = inflater.inflate(R.layout.district_list, container, false);
        districtListView = (ListView) districtListLayout.findViewById(R.id.district_list);
        districts = db.getDistricts();
        ObjectListAdapter adapter = new ObjectListAdapter(this.getActivity(), R.layout.list_item, districts);
        districtListView.setAdapter(adapter);
        districtListView.setOnItemClickListener(new ListView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                FrameLayout frame = (FrameLayout) getActivity().findViewById(R.id.page_frame);
                frame.removeAllViews();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                District selectedDistrict = districts.get(position);
                Fragment fragment = new CompanionshipListFragment(selectedDistrict);
                fragmentManager.beginTransaction().replace(R.id.page_frame, fragment).commit();
            }
        });
        return districtListLayout;
    }
}