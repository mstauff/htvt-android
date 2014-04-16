package htvt.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import htvt.domain.Family;
import htvt.domain.HTVTDB;
import htvt.domain.ObjectListAdapter;
import main.java.htvt.R;
import roboguice.RoboGuice;
import roboguice.fragment.RoboFragment;

import javax.inject.Inject;
import java.util.List;

public class IndividualListFragment extends RoboFragment {

    @Inject
    HTVTDB db;

   // ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ListView listView = (ListView)inflater.inflate(R.layout.individual_list, container, false);
        BuildMemberListWithOptions(listView);
        return listView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RoboGuice.getInjector(getActivity()).injectMembers(this);
    }

    public ListView BuildMemberListWithOptions(ListView listView) {
        List<Family> families = db.getWardList();
        ObjectListAdapter adapter = new ObjectListAdapter(getActivity(), android.R.layout.simple_list_item_1, families);

        listView.setAdapter(adapter);

        return listView;
    }
}