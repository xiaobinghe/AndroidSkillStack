package com.locensate.firstlinecode;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
/**
 *-------------------------------------
 * <p>
 * 项目名称： ${PROJECT_NAME}
 * <p>
 * 版权：locensate.com 版权所有 2017
 * <p>
 * 公司主页：http://www.locensate.com/
 * <p>
 * 描述：
 * 时间： ${DATE} ${TIME}
 * <p>
 * 修改历史：
 * <p>
 * 修改时间：
 * <p>
 * 修改描述：
 * <p>
 * @author：xiaobinghe
 *-------------------------------------
 */

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Messenger mMessenger;
    private Messenger mGetReplyMessenger = new Messenger(new MessengerHandler());
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(TAG, "componentName====" + name.toString() + "===Binder===" + service.toString());
            mMessenger = new Messenger(service);
            Message message = Message.obtain(null, 1);
            Bundle bundle = new Bundle();
            bundle.putString("msg", "Hello ,this is client!");
            message.setData(bundle);
            message.replyTo = mGetReplyMessenger;
            try {
                mMessenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindService(new Intent(this, MessengerService.class), mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        unbindService(mConnection);
        super.onDestroy();
    }

    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 2:
                    Log.e(TAG, "Message=====" + msg.getData().getString("reply"));
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }
}
