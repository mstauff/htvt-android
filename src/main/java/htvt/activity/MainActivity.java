package htvt.activity;

import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import htvt.api.MemberManager;
import main.java.htvt.R;
import org.lds.mobile.util.TagUtil;
import roboguice.activity.RoboFragmentActivity;
import roboguice.fragment.RoboFragment;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

import javax.inject.Inject;
import java.util.HashMap;

@ContentView(R.layout.main)
public class MainActivity extends RoboFragmentActivity {
    public static final String DEFAULT_TAG_PREFIX = "workflow.";

    public static String createTag(Class clazz) {
        return TagUtil.createTag(DEFAULT_TAG_PREFIX, clazz);
    }
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
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @InjectView(R.id.page_frame)
        FrameLayout frameLayout;

    private void setPageActivity(int position) {
        pageFrame.removeAllViews();

        RoboFragment fragment;
        switch (position){
            case 0:
            default:
                fragment = new IndividualListFragment();
                break;
            case 1:
                fragment = new DistrictListFragment();
                break;
            case 2:
                fragment = new RecordFragment();
                break;
            case 3:
                fragment = new ReportFragment();
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(frameLayout.getId(), fragment).commit();

        navList.setItemChecked(position, true);
        setTitle(navOptions[position]);
        drawerNav.closeDrawer(navList);
    }
}