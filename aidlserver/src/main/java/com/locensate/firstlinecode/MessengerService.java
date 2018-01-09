package com.locensate.firstlinecode;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class MessengerService extends Service {
    private static class MessengerHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Log.e(TAG, "messenger ===" + msg.getData().getString("msg"));

                    Messenger messenger = msg.replyTo;
                    Message message = Message.obtain(null, 2);
                    Bundle bundle = new Bundle();
                    bundle.putString("reply", "Hello, this is reply client!");
                    message.setData(bundle);
                    try {
                        messenger.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    private final Messenger mMessenger = new Messenger(new MessengerHandler());

    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }


}
