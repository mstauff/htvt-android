package org.ldscommunity.htvt;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity {
    private HashMap<Long, String> members = new HashMap<Long, String>();
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        BuildMemberListWithOptions();
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
