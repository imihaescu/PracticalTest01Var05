package ro.pub.cs.systems.eim.practicaltest01var5;

import android.content.Context;
import android.content.Intent;
import android.service.autofill.Dataset;
import android.util.Log;

import java.util.Date;

public class ProcessingThread extends Thread {
    private Context context = null;
    private boolean isRunning = true;
    private int scor;

    public ProcessingThread (Context context, int s) {
        this.context = context;
        scor = s;
    }

    @Override
    public void run() {
        while (isRunning) {
            Log.d("[ProcessingThread]", "Thread has started!");
            while (isRunning) {
                sendMessage();
                sleep();
            }
            Log.d("[ProcessingThread]", "Thread has stopped!");
        }

    }
    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction("Victory");
        intent.putExtra("data", new Date(System.currentTimeMillis()) + " " + scor);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }
    public void stopThread() {
        isRunning = false;
    }
}
