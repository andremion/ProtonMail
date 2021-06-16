package ch.protonmail.android.protonmailtest.ui.util

import android.content.Context
import android.graphics.Bitmap
import java.io.File
import java.io.FileOutputStream

val Context.imagesPath: File get() = File(cacheDir, "images")

fun Context.imageFile(day: Int) = File(imagesPath, day.toString())

fun saveImage(image: Bitmap, path: File, fileName: String) {
    path.mkdirs()
    val imageFile = File(path, fileName)
    FileOutputStream(imageFile).use { outputStream ->
        image.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
    }
}
