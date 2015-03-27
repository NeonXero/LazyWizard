package net.neonlotus.lazywizard.application;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class SaveService extends Service {

    /*@Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        MyApplication.getInstance().saveAll();
        Log.d("ryan", "save service happened");
        // I don't want this service to stay in memory, so I stop it
        // immediately after doing what I wanted it to do.
        stopSelf();

        return START_NOT_STICKY;
    }*/

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {

        AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarm.set(
                alarm.RTC_WAKEUP,
                System.currentTimeMillis() + (1000*60*30),
                PendingIntent.getService(this, 0, new Intent(this, MainService.class), 0)
        );

    }
}