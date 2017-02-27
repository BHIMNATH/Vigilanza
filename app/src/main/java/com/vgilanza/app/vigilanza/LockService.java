package com.vgilanza.app.vigilanza;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;

public class LockService extends Service {
    public LockService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final IntentFilter filter=new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_USER_PRESENT);
        final BroadcastReceiver mReciever=new TapReceiver();
        registerReceiver(mReciever,filter);
        return super.onStartCommand(intent, flags, startId);
    }
    public class LocalBinder extends Binder{
        LockService getService(){
            return LockService.this;
        }
    }
}
