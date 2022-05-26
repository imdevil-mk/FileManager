package com.imdevil.filemanager.bean

import com.imdevil.filemanager.R

enum class MediaCategory(val title: Int, val icon: Int) {
    NULL(-1, -1),
    IMAGE(R.string.media_image, R.drawable.ic_image),
    VIDEO(R.string.media_video, R.drawable.ic_video),
    AUDIO(R.string.media_audio, R.drawable.ic_audio)
}