package com.locensate.firstlinecode.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import com.locensate.firstlinecode.golobal.Config;

/**
 * MessengerService
 *
 * @author xiaobinghe
 */

public class MessengerService extends Service {


    private final Messenger mMessenger = new Messenger(new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == Config.CLIENT_MSG) {
                Log.e("msg", "------------" + msg.getData().getString("message"));
                Messenger messenger = msg.replyTo;
                try {
                    messenger.send(Message.obtain(null,Config.SERVER_MSG));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            return false;
        }
    }));

    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}
