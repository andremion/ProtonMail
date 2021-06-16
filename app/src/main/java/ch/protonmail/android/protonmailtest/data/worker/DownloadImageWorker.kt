package ch.protonmail.android.protonmailtest.data.worker

import android.content.Context
import android.net.Uri
import androidx.work.*
import ch.protonmail.android.protonmailtest.ui.util.imagesPath
import ch.protonmail.android.protonmailtest.ui.util.saveImage
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import java.io.File

class DownloadImageWorker(
    private val context: Context,
    params: WorkerParameters
) : Worker(context, params) {

    companion object {
        const val KEY_IMAGE_ID: String = "key_image_id"
        const val KEY_IMAGE_URI: String = "key_image_uri"

        fun createWorkRequest(id: Int, uri: String): WorkRequest =
            OneTimeWorkRequestBuilder<DownloadImageWorker>()
                .setInputData(createInputData(id, uri))
                .build()

        private fun createInputData(id: Int, uri: String): Data =
            Data.Builder()
                .putString(KEY_IMAGE_ID, id.toString())
                .putString(KEY_IMAGE_URI, uri)
                .build()
    }

    override fun doWork(): Result =
        try {
            val imageUri = Uri.parse(inputData.getString(KEY_IMAGE_URI))
            val bitmap = Glide.with(context)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .load(imageUri)
                .submit()
                .get()

            val path = context.imagesPath
            val fileName = requireNotNull(inputData.getString(KEY_IMAGE_ID))
            saveImage(bitmap, path, fileName)
            val imageFile = File(path, fileName)

            val output = workDataOf(KEY_IMAGE_URI to imageFile.absolutePath)
            Result.success(output)
        } catch (e: Exception) {
            Result.failure()
        }
}
