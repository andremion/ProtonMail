package ch.protonmail.android.protonmailtest.data.worker

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.work.*
import ch.protonmail.android.protonmailtest.ui.util.imagesPath
import ch.protonmail.android.protonmailtest.ui.util.saveImage
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import java.io.File

private const val KEY_IMAGE_ID: String = "key_image_id"
private const val KEY_IMAGE_URI: String = "key_image_uri"
private const val KEY_IMAGE_PATH: String = "key_image_path"

class DownloadImageWorker(
    private val context: Context,
    params: WorkerParameters
) : Worker(context, params) {

    companion object {
        const val TAG: String = "DownloadImageWorker"

        fun createWorkRequest(id: Int, uri: String): WorkRequest =
            OneTimeWorkRequestBuilder<DownloadImageWorker>()
                .addTag(createTag(id))
                .setInputData(createInputData(id, uri))
                .build()
    }

    override fun doWork(): Result =
        try {
            val imageUri = Uri.parse(inputData.getString(KEY_IMAGE_URI))
            val bitmap = loadBitmap(imageUri)

            val path = context.imagesPath
            val fileName = requireNotNull(inputData.getString(KEY_IMAGE_ID))
            saveImage(bitmap, path, fileName)
            val imageFile = File(path, fileName)

            val output = workDataOf(KEY_IMAGE_PATH to imageFile.absolutePath)
            Result.success(output)
        } catch (e: Exception) {
            Result.failure()
        }

    private fun loadBitmap(imageUri: Uri): Bitmap =
        Glide.with(context)
            .asBitmap()
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .load(imageUri)
            .submit()
            .get()
}

val WorkInfo.imageFile: File get() = File(requireNotNull(outputData.getString(KEY_IMAGE_PATH)))

fun WorkManager.getWorkInfo(id: Int): LiveData<List<WorkInfo>> =
    getWorkInfosByTagLiveData(createTag(id))

private fun createTag(id: Int): String =
    "${DownloadImageWorker.TAG}[$id]"

private fun createInputData(id: Int, uri: String): Data =
    Data.Builder()
        .putString(KEY_IMAGE_ID, id.toString())
        .putString(KEY_IMAGE_URI, uri)
        .build()
