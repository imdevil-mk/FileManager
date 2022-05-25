package com.imdevil.filemanager.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.imdevil.filemanager.databinding.FragmentMediaBinding
import com.imdevil.filemanager.viewmodels.MediaFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MediaFragment : LogFragment() {

    private lateinit var viewBinding: FragmentMediaBinding
    private val mediaFragmentArgs: MediaFragmentArgs by navArgs()
    private val viewModel: MediaFragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.query(mediaFragmentArgs.mediaType)
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
        lifecycleScope.launch {
            viewModel.lists.collect {
                viewBinding.text.text = it.size.toString()
            }
        }
    }
}