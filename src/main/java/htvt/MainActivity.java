package htvt;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import htvt.activity.AssignFragment;
import htvt.activity.IndividualListFragment;
import htvt.activity.RecordFragment;
import htvt.activity.ReportFragment;
import htvt.api.MemberManager;
import main.java.htvt.R;
import roboguice.activity.RoboActivity;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends RoboActivity {
    private static String[] NAV_PAGES = {"INDIVIDUALLIST","ASSIGN","RECORD","REPORT"};

    private HashMap<Long, String> members = new HashMap<Long, String>();
    private String[] navOptions;
    private DrawerLayout drawerNav;
    private ListView navList;
    private FrameLayout pageFrame;
    private ActionBarDrawerToggle drawerToggle;

    @Inject
    MemberManager memberManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        navOptions = getResources().getStringArray(R.array.nav_options);
        drawerNav = (DrawerLayout) findViewById(R.id.nav_drawer);
        navList = (ListView) findViewById(R.id.nav_drawer_list);
        pageFrame = (FrameLayout) findViewById(R.id.page_frame);

        drawerToggle = new ActionBarDrawerToggle(this, drawerNav, R.drawable.ic_launcher, R.string.openDrawer, R.string.closeDrawer);
        drawerNav.setDrawerListener(drawerToggle);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        navList.setAdapter(new ArrayAdapter<String>(this, R.layout.nav_option, navOptions));
        navList.setOnItemClickListener(new ListView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                setPageActivity(position);
            }
        });

        BuildMemberListWithOptions();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setPageActivity(int position){
        pageFrame.removeAllViews();

        Fragment fragment;
        switch (position){
            case 0:
            default:
                fragment = new IndividualListFragment();
                break;
            case 1:
                fragment = new AssignFragment();
                break;
            case 2:
                fragment = new RecordFragment();
                break;
            case 3:
                fragment = new ReportFragment();
                break;
        }
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.page_frame, fragment).commit();

        navList.setItemChecked(position, true);
        setTitle(navOptions[position]);
        drawerNav.closeDrawer(navList);
    }

    public HashMap<Long, String> HydrateMemberList() {
        members.put(100L,"Brian Blanchard");
        members.put(101L, "Alex Carrasco");
        members.put(102L, "Adam Shephard");
        members.put(103L, "Matthew Stauffer");
        members.put(104L, "William Wilcox");
        members.put(105L, "Vard Lott");
        members.put(106L, "Drew Terry");
        members.put(107L, "Jeff Nelson");
        return members;
    }

    public void BuildMemberListWithOptions() {
        //List<Member> memberList = memberManager.getWardList();
        HydrateMemberList();
        TableLayout l_layout = (TableLayout) findViewById(R.id.mainTableLayout);
        String[] spinnerOption = {"Not Recorded", "Yes", "No"};
        for(Map.Entry<Long, String> val : members.entrySet()) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLeft(5);
            Spinner yesNo = new Spinner(this);
            yesNo.setId(Integer.valueOf(val.getKey().toString()));
            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerOption);
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
            yesNo.setAdapter(spinnerArrayAdapter);
            TextView label = new TextView(this);
            label.setText(val.getValue());
            tableRow.addView(label);
            tableRow.addView(yesNo);
            l_layout.addView(tableRow);
        }
        //yesNo.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
    }

    public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            // An item was selected. You can retrieve the selected item using
            // parent.getItemAtPosition(pos)
        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Another interface callback
        }
    }
}
