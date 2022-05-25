package com.imdevil.filemanager.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.imdevil.filemanager.bean.FileInfo
import com.imdevil.filemanager.bean.MediaCategory
import com.imdevil.filemanager.repository.MediaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MediaFragmentViewModel @Inject constructor(
    application: Application,
    private val mediaRepository: MediaRepository,
) : AndroidViewModel(application) {

    private val _lists = MutableStateFlow<List<FileInfo>>(emptyList())
    val lists: StateFlow<List<FileInfo>> = _lists

    fun query(category: MediaCategory) {
        viewModelScope.launch {
            _lists.value = mediaRepository.mediaQuery(category)
        }
    }
}