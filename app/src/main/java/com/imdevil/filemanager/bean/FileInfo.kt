package com.imdevil.filemanager.bean

import android.net.Uri

data class FileInfo(
    val id: Long,
    val contentUri: Uri,
    val path: String,
    val displayName: String,
    val title: String,
    val dateModified: Long,
    val bucketId: Long,
    val bucketDisplayName: String,
    val size: Long,
)