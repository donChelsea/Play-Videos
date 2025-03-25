package com.example.playvideos.domain

import android.app.Application
import android.net.Uri
import android.provider.MediaStore
import javax.inject.Inject
import com.example.playvideos.domain.model.Metadata
import javax.inject.Singleton

interface MetadataReader {
    fun getMetadataFromUri(contentUri: Uri): Metadata?
}

@Singleton
class MetadataReaderImpl @Inject constructor(
    private val app: Application
): MetadataReader {

    override fun getMetadataFromUri(contentUri: Uri): Metadata? {
        if (contentUri.scheme != "content") return null

        val fileName = app.contentResolver
            .query(
                contentUri,
                arrayOf(MediaStore.Video.VideoColumns.DISPLAY_NAME),
                null,
                null,
                null,
            )
            ?.use { cursor ->
                val index = cursor.getColumnIndex(MediaStore.Video.VideoColumns.DISPLAY_NAME)
                cursor.moveToFirst()
                cursor.getString(index)
            }
        return fileName?.let { fullFileName ->
            Metadata(
                fileName = Uri.parse(fullFileName).lastPathSegment ?: return null
            )
        }
    }

}