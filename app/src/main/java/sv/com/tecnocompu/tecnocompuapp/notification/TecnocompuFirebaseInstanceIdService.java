package sv.com.tecnocompu.tecnocompuapp.notification;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by cesar on 11-03-17.
 */

public class TecnocompuFirebaseInstanceIdService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        Log.d("token", "onTokenRefresh: "+ FirebaseInstanceId.getInstance().getToken());
    }
}
