package com.imdevil.filemanager.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.imdevil.filemanager.adapters.ImagesByDirAdapter
import com.imdevil.filemanager.databinding.FragmentMediaImagesByDirBinding
import com.imdevil.filemanager.viewmodels.MediaImagesByDirViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MediaImagesByDirFragment : BaseFragment() {

    private lateinit var viewBinding: FragmentMediaImagesByDirBinding
    private val viewModel: MediaImagesByDirViewModel by viewModels()
    private lateinit var adapter: ImagesByDirAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentMediaImagesByDirBinding.inflate(inflater, container, false)

        viewBinding.imagesByDirList.layoutManager = LinearLayoutManager(requireContext())
        adapter = ImagesByDirAdapter()
        viewBinding.imagesByDirList.adapter = adapter

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.sortedList.collect {
                //Log.d(TAG, "onViewCreated: $it")
                adapter.submitList(it)
            }
        }
    }
}