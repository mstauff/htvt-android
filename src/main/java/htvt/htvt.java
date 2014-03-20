package htvt;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import htvt.activity.StartupActivity;
import org.lds.mobile.util.TagUtil;

public class htvt extends Application {
    public static final String TAG = htvt.createTag(htvt.class);
    private static Class currentRootClass;
    public static final String DEFAULT_TAG_PREFIX = "htvt.";

    public static String createTag(Class clazz) {
        return TagUtil.createTag(DEFAULT_TAG_PREFIX, clazz);
    }

    public static void startRootActivity(Class clazz, Activity activity) {
        currentRootClass = clazz;

        Intent intent = new Intent(activity, StartupActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }
}