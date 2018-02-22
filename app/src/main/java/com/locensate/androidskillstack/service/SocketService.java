package com.locensate.androidskillstack.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.concurrent.Executors;

/**
 *  socket
 * @author xiaobinghe
 */

public class SocketService extends Service {
    public SocketService() {
    }


    @Override
    public void onCreate() {
        super.onCreate();

      Executors.newCachedThreadPool().execute(new Runnable() {
          @Override
          public void run() {

          }
      });
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
