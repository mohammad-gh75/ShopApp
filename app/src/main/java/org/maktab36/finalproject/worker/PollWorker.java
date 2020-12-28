package org.maktab36.finalproject.worker;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import org.maktab36.finalproject.utils.WorkUtils;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class PollWorker extends Worker {
    public static final String TAG = "reza3";
    private static final String WORK_TAG_POLL = "PhotoPollWorker";

    public PollWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d(TAG, "doWork");
        WorkUtils.pollFromServerAndNotify(getApplicationContext(), TAG);
        return Result.success();
    }


    public static void scheduleWork(Context context, int time) {
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .build();

        PeriodicWorkRequest workRequest =
                new PeriodicWorkRequest.Builder(PollWorker.class, time, TimeUnit.HOURS)
                        .setConstraints(constraints)
                        .build();

        boolean isOn = PollWorker.isWorkScheduled(context);

        if (time != 0) {
            Log.d(TAG, "enqueue work");
            WorkManager
                    .getInstance(context)
                    .enqueueUniquePeriodicWork(
                            WORK_TAG_POLL,
                            ExistingPeriodicWorkPolicy.KEEP,
                            workRequest);
        } else if (isOn) {
            Log.d(TAG, "cancel work");
            WorkManager
                    .getInstance(context)
                    .cancelUniqueWork(WORK_TAG_POLL);
        }
    }

    private static boolean isWorkScheduled(Context context) {
        try {
            WorkManager workManager = WorkManager.getInstance(context);
            List<WorkInfo> workInfos = workManager.getWorkInfosForUniqueWork(WORK_TAG_POLL).get();

            for (WorkInfo workInfo : workInfos) {
                if (workInfo.getState() == WorkInfo.State.ENQUEUED ||
                        workInfo.getState() == WorkInfo.State.RUNNING)
                    return true;
            }

            return false;
        } catch (ExecutionException e) {
            Log.e(TAG, e.getMessage(), e);
            return false;
        } catch (InterruptedException e) {
            Log.e(TAG, e.getMessage(), e);
            return false;
        }
    }
}
