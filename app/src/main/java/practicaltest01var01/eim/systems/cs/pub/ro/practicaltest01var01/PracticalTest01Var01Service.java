package practicaltest01var01.eim.systems.cs.pub.ro.practicaltest01var01;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PracticalTest01Var01Service extends Service {
    public PracticalTest01Var01Service() {
    }

    ProcessingThread processingThread = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String text = intent.getStringExtra("text");

        processingThread = new ProcessingThread(this, text);
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        processingThread.stopThread();
    }

}
