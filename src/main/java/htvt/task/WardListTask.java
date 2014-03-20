package htvt.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;
import htvt.InternalIntents;
import htvt.api.MemberManager;
import htvt.api.ServiceException;
import htvt.domain.Family;
import htvt.domain.HTVTDB;
import htvt.domain.Member;
import main.java.htvt.R;
import roboguice.util.RoboAsyncTask;

import javax.inject.Inject;
import java.util.List;

public class WardListTask  extends RoboAsyncTask<Void> {
    @Inject
    private SharedPreferences preferences;

    @Inject
    private MemberManager memberManager;

    @Inject
    private HTVTDB db;
    protected ProgressDialog progressDialog;

    boolean forceUpdate;

    private ServiceException.ServiceError serviceError = ServiceException.ServiceError.NONE;
    private Handler handler;

    public  WardListTask(Context context, boolean forceUpdate, ProgressDialog progressDialog) {
        super(context);
        this.forceUpdate = forceUpdate;
        this.progressDialog = progressDialog;
    }

    @Override
    protected void onSuccess(Void unused) {
        // do this in the UI thread if call() succeeds
        if( progressDialog != null ) {
            progressDialog.setMessage( getContext().getString( R.string.pref_sync_all_progress_success ) );
        }
    }

    @Override
    protected void onException(Exception e) {
        String errorMsg = getContext().getString( R.string.pref_sync_all_progress_failure );
        // do this in the UI thread if call() threw an exception
        if (progressDialog != null && progressDialog.isShowing()){
            progressDialog.setMessage( errorMsg );
        } else {
            Toast.makeText(getContext(), errorMsg, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onFinally() {
        // always do this in the UI thread after calling call()
        if (progressDialog != null && progressDialog.isShowing()){
            // delay dismissal 2 seconds to allow user time to read error or success
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    progressDialog.dismiss();
                }}, 2000);
        }
    }
    @Override
    public Void call() throws Exception {
        boolean dataUpdated = false;
        if(forceUpdate || !db.hasData(Member.TABLE_NAME)) {
            List<Family> families = memberManager.getWardList();
            if( !families.isEmpty() ) {
                db.updateWardList(families);
                dataUpdated = true;
            }
        }

        if (dataUpdated) {
            Intent intent = new Intent();
            intent.setAction(InternalIntents.SYNC_COMPLETE);
            LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance( context );
            broadcastManager.sendBroadcast( intent );
        }
        return null;
    }
}