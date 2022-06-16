package com.imdevil.filemanager.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.imdevil.filemanager.bean.FileInfo
import com.imdevil.filemanager.bean.ImagesByDir
import com.imdevil.filemanager.bean.MediaCategory
import com.imdevil.filemanager.bean.SortOrder
import com.imdevil.filemanager.repository.MediaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MediaImagesByDirViewModel @Inject constructor(
    application: Application,
    private val mediaRepository: MediaRepository,
) : AndroidViewModel(application) {

    private val queryCategory = MutableStateFlow(MediaCategory.IMAGE_BY_PATH)

    private val sort = MutableStateFlow(SortOrder.TIME)

    private val lists: Flow<List<FileInfo>> = queryCategory.map {
        if (it == MediaCategory.NULL) {
            emptyList()
        } else {
            mediaRepository.mediaQuery(it)
        }
    }

    val sortedList: Flow<List<ImagesByDir>> = lists.combine(sort) { originList, sort ->
        val ret = mutableListOf<ImagesByDir>()
        originList.sortedWith(sort.comparator).groupBy { it -> it.bucketDisplayName }.onEach {
            ret.add(ImagesByDir(it.key, it.value[0].bucketDisplayName, it.value))
        }
        ret
    }
}