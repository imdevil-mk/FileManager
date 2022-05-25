package com.imdevil.filemanager.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.imdevil.filemanager.R
import com.imdevil.filemanager.bean.MediaCategory
import com.imdevil.filemanager.databinding.FragmentMediaBinding

class MediaFragment : LogFragment() {

    private lateinit var viewBinding: FragmentMediaBinding
    private val mediaFragmentArgs: MediaFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        viewBinding = FragmentMediaBinding.inflate(layoutInflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        when (mediaFragmentArgs.mediaType) {
            MediaCategory.AUDIO -> {
                viewBinding.text.text = getString(R.string.media_audio)
            }
            MediaCategory.VIDEO -> {
                viewBinding.text.text = getString(R.string.media_video)
            }
            else -> {
                viewBinding.text.text = getString(R.string.app_name)
            }
        }
    }
}