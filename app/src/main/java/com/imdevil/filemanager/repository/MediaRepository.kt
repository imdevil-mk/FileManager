package com.imdevil.filemanager.repository

import android.app.Application
import com.imdevil.filemanager.MediaStoreQuery
import com.imdevil.filemanager.bean.FileInfo
import com.imdevil.filemanager.bean.MediaCategory
import javax.inject.Inject

class MediaRepository @Inject constructor(
    private val application: Application,
    private val mediaStoreQuery: MediaStoreQuery
) {

    suspend fun mediaQuery(category: MediaCategory): List<FileInfo> {
        return mediaStoreQuery.queryImages()
    }
}