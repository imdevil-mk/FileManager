package com.imdevil.filemanager.bean

import com.imdevil.filemanager.R

enum class MediaCategory(val title: Int, val icon: Int) {
    VIDEO(R.string.media_video, R.drawable.ic_launcher_foreground),
    AUDIO(R.string.media_audio, R.drawable.ic_launcher_foreground)
}