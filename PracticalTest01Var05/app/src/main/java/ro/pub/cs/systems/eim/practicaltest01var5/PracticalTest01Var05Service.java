package ro.pub.cs.systems.eim.practicaltest01var5;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class PracticalTest01Var05Service extends Service {
    private ProcessingThread processingThread = null;

    @Override
    public void onDestroy() {
        processingThread.stopThread();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int scor = intent.getIntExtra("data", -1);
        processingThread = new ProcessingThread(this, scor);
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
