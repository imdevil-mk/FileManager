package com.imdevil.filemanager.bean

data class ImagesByDir(
    val dirName: String,
    val dirPath: String,
    val images: List<FileInfo>,
)