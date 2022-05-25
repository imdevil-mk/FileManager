package com.imdevil.filemanager.bean

import android.net.Uri
import java.util.*

data class FileInfo(
    val id: Long,
    val contentUri: Uri,
    val path: String,
    val displayName: String,
    val title: String,
    val dateAdded: Date,
    val bucketId: Long,
    val bucketDisplayName: String,
    val size: Long,
)