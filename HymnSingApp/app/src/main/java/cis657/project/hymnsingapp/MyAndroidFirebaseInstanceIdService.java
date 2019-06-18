package cis657.project.hymnsingapp;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyAndroidFirebaseInstanceIdService extends FirebaseInstanceIdService {
    private static final String TAG = "DBG";

    @Override
    public void onTokenRefresh() {
        // get hold of the registration token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        // lg the token
        Log.d(TAG, "Refreshed token: " + refreshedToken);
    }
    private void sendRegistrationToServer(String token) {
        // implement this method if you want to store the token on your server
    }
}


//Not possible without God's help.