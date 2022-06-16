package com.imdevil.filemanager.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.imdevil.filemanager.bean.FileInfo
import com.imdevil.filemanager.bean.MediaCategory
import com.imdevil.filemanager.bean.SortOrder
import com.imdevil.filemanager.bean.ViewType
import com.imdevil.filemanager.repository.MediaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MediaFragmentViewModel @Inject constructor(
    application: Application,
    private val mediaRepository: MediaRepository,
) : AndroidViewModel(application) {

    private val _queryCategory = MutableSharedFlow<MediaCategory>()
    val queryCategory: SharedFlow<MediaCategory> = _queryCategory

    private val _listViewType = MutableStateFlow(ViewType.LIST)
    val listViewType: StateFlow<ViewType> = _listViewType

    private val _sort = MutableStateFlow(SortOrder.TIME)
    val sort: StateFlow<SortOrder> = _sort

    private val lists: Flow<List<FileInfo>> = _queryCategory.map {
        if (it == MediaCategory.NULL) {
            emptyList()
        } else {
            mediaRepository.mediaQuery(it)
        }
    }

    val sortedList: Flow<List<FileInfo>> = lists.combine(sort) { originList, sort ->
        originList.sortedWith(sort.comparator)
    }

    fun query(category: MediaCategory) {
        viewModelScope.launch {
            _queryCategory.emit(category)
        }
    }

    fun setListViewType(type: ViewType) {
        _listViewType.value = type
    }

    fun setSort(sortType: SortOrder) {
        _sort.value = sortType
    }
}