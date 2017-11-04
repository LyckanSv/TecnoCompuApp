package sv.com.tecnocompu.tecnocompuapp.notification;


import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class TecnocompuFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

            Log.d("NOTIFICACION", "r: "+remoteMessage.getData().get("message"));

    }
}
