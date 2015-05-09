package net.neonlotus.lazywizard.application;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MainService extends Service {
    int total = 0;

    /*@Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Prefs_ p = MainActivity.getPrefs();

        Log.d("ryan", "main 3 seconds");

        // Query the database and show alarm if it applies
        if (p != null) {
            if (p.rateCalculation().exists()) {
                total = p.rateCalculation().get();
            } else {
                total = 0;
                p.rateCalculation().put(total);
            }


            if (MyApplication.getInstance().getUnitList() != null) {
                if (MyApplication.getInstance().getUnitList().size() > 0) {
                    for (int i = 0; i < MyApplication.getInstance().getUnitList().size(); i++) {
                        Unit un = MyApplication.getInstance().getUnitList().get(i);
                        long owned = un.owned;
                        long rate = un.rate;
                        long pay = owned * rate;
                        p.souls().put(p.souls().get() + pay);
                        MainActivity.updateSouls();
                    }
                }
            }

        }


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
        Log.d("ryan", "main 3 seconds");
        AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarm.set(
                alarm.RTC_WAKEUP,
                System.currentTimeMillis() + (3000),
                PendingIntent.getService(this, 0, new Intent(this, MainService.class), 0)
        );

    }
}