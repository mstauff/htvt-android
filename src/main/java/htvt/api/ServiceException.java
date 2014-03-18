package htvt.api;

import android.app.AlertDialog;
import android.content.Context;

public class ServiceException extends RuntimeException {

    public enum ServiceError {
        NONE,
         INVALID_CREDENTIALS,
         SERVICE_UNAVAILABLE,
         LOST_CONNECTION,
         NOT_A_MEMBER
    }

    private ServiceError errorCode = ServiceError.NONE;

    public ServiceException(ServiceError errorCode) {
        this.errorCode = errorCode;
    }

    public ServiceException(ServiceError errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ServiceError getErrorCode() {
        return errorCode;
    }

    public static void showError(Context context, ServiceError serviceError) {
        AlertDialog.Builder d = new AlertDialog.Builder(context);
//        d.setTitle(R.string.error);
//
//        switch (serviceError) {
//            case SERVICE_UNAVAILABLE:
//                d.setMessage(R.string.error_service_unavailable);
//                break;
//            case LOST_CONNECTION:
//                d.setMessage(R.string.error_lost_connection);
//                break;
//            default:
//                d.setMessage("Unknown Error");
//                break;
//        }
//
//        d.setIcon(android.R.drawable.ic_dialog_alert);
//        d.setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int whichButton) {
//            }
//        });
//        d.show();
    }

    @Override
    public String getMessage() {
        return errorCode.toString();
    }
}
