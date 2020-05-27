package ru.somarov.marathon.backend.main.plugin.runner_card.work_manager

import android.content.Context
import android.provider.SyncStateContract
import androidx.work.Worker
import androidx.work.WorkerParameters

class RunnerCardWorkManager(c: Context, params: WorkerParameters): Worker(c, params) {
    override fun doWork(): Result {
       /* uri = inputData.getString("PHOTO_URI")
        val success = upload(uri)*/
        return if(true) {
            Result.success()
        } else {
            Result.failure()
        }
    }

}