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

import com.locensate.firstlinecode.golobal.Config;
import com.locensate.firstlinecode.service.MessengerService;
import com.locensate.firstlinecode.utils.ToastUtil;


/**
 * @author xiaobinghe
 */
public class MessengerTestActivity extends BaseActivity {


    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Messenger mMessenger = new Messenger(service);
            try {
                mObtain = Message.obtain(null, Config.CLIENT_MSG);
                Bundle bundle = new Bundle();
                bundle.putString("message", "send msg from client");
                mObtain.setData(bundle);
                ToastUtil.showToast("please input message");
                mMessenger.send(mObtain);

                mObtain.replyTo = getMessenger;

            } catch (RemoteException e) {
                e.printStackTrace();

            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    private Message mObtain;

    @Override
    protected void initData() {
    }


    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Config.SERVER_MSG:
                   ToastUtil.showToast("receive msg successful");
                    unbindService(mConnection);
                    break;
                default:
                    super.handleMessage(msg);
            }

        }
    }


    private Messenger getMessenger = new Messenger(new MyHandler());

    @Override
    protected void initView() {
        bindService(new Intent(MessengerTestActivity.this, MessengerService.class), mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_messenger_test_acitivity;
    }
}
