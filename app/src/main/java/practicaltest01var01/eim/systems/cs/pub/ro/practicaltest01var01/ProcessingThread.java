package practicaltest01var01.eim.systems.cs.pub.ro.practicaltest01var01;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;
import java.util.Random;

/**
 * Created by student on 03.04.2018.
 */

public class ProcessingThread extends Thread {

    final public static String[] actionTypes = {
            "ro.pub.cs.systems.eim.practicaltest01.arithmeticmean",
            "ro.pub.cs.systems.eim.practicaltest01.geometricmean"
    };

    private Context context = null;
    private boolean isRunning = true;

    private Random random = new Random();

    String text;

    public ProcessingThread(Context context, String text) {
        this.context = context;
        this.text = text;
    }

    @Override
    public void run() {
        Log.d("[ProcessingThread]", "Thread has started!");
        while (isRunning) {
            sendMessage();
            sleep();
        }
        Log.d("[ProcessingThread]", "Thread has stopped!");
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction(actionTypes[random.nextInt(actionTypes.length)]);
        intent.putExtra("message", this.text + " " + new Date(System.currentTimeMillis()));
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}
