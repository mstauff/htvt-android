package htvt.activity;

import android.content.Intent;
import android.os.Bundle;
import htvt.task.WardListTask;
import roboguice.activity.RoboActivity;

public class StartupActivity extends RoboActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new WardListTask(getApplicationContext(), true, null).execute();
	    /* We do not have a startup/splash screen yet */
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}