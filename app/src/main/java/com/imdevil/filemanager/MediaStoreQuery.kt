package com.imdevil.filemanager

import android.app.Application
import android.content.ContentUris
import android.provider.MediaStore
import com.imdevil.filemanager.bean.FileInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MediaStoreQuery(private val application: Application) {

    suspend fun queryImages(): List<FileInfo> = withContext(Dispatchers.IO) {
        val files = mutableListOf<FileInfo>()
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.TITLE,
            MediaStore.Images.Media.DATE_ADDED,
            MediaStore.Images.Media.BUCKET_ID,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Images.Media.SIZE,
        )

        val selection = null

        val selectionArgs = null

        val sortOrder = "${MediaStore.Images.Media.DATE_ADDED} DESC"

        application.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            selectionArgs,
            sortOrder
        )?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val dataColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            val displayNameColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
            val titleColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.TITLE)
            val dateModifiedColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_ADDED)
            val bucketIdColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_ID)
            val bucketDisplayNameColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
            val sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)


            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val path = cursor.getString(dataColumn)
                val displayName = cursor.getString(displayNameColumn)
                val title = cursor.getString(titleColumn)
                val dateModified = cursor.getLong(dateModifiedColumn)
                val bucketId = cursor.getLong(bucketIdColumn)
                val bucketDisplayName = cursor.getString(bucketDisplayNameColumn)
                val size = cursor.getLong(sizeColumn)

                val contentUri = ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    id
                )

                val file = FileInfo(
                    id, contentUri, path, displayName, title, dateModified,
                    bucketId, bucketDisplayName, size,
                )

                files += file
            }
        }
        files
    }
}