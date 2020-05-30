package ru.somarov.marathon.backend.main.plugin.runner_card

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class CardWorker(c: Context, params: WorkerParameters): Worker(c, params) {
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