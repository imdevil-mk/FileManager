package com.imdevil.filemanager.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.imdevil.filemanager.R
import com.imdevil.filemanager.adapters.ImagesByDirAdapter
import com.imdevil.filemanager.bean.ImagesByDir
import com.imdevil.filemanager.bean.MediaCategory
import com.imdevil.filemanager.databinding.FragmentMediaBinding
import com.imdevil.filemanager.viewmodels.MediaFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

private const val TAG = "MediaFragment"

@AndroidEntryPoint
class MediaFragment : BaseFragment() {

    private lateinit var viewBinding: FragmentMediaBinding
    private val mediaFragmentArgs: MediaFragmentArgs by navArgs()
    private val viewModel: MediaFragmentViewModel by viewModels()
    private lateinit var imagesByDirAdapter: ImagesByDirAdapter

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


        viewBinding.mediaList.layoutManager = LinearLayoutManager(requireContext())
        when (mediaFragmentArgs.mediaType) {
            MediaCategory.IMAGE_BY_PATH -> {
                imagesByDirAdapter = ImagesByDirAdapter()
                viewBinding.mediaList.adapter = imagesByDirAdapter
            }
        }

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.sortedList.collect { lists ->
                when (mediaFragmentArgs.mediaType) {
                    MediaCategory.IMAGE_BY_PATH -> {
                        val ret = mutableListOf<ImagesByDir>()
                        lists.groupBy { it.bucketDisplayName }.onEach {
                            ret.add(ImagesByDir(it.key, it.value[0].bucketDisplayName, it.value))
                        }
                        imagesByDirAdapter.submitList(ret)
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.media_menu, menu)
    }
}