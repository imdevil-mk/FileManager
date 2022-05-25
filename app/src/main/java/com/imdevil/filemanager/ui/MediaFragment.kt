package com.imdevil.filemanager.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.imdevil.filemanager.MainActivity
import com.imdevil.filemanager.R
import com.imdevil.filemanager.databinding.FragmentMediaBinding
import com.imdevil.filemanager.viewmodels.MediaFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MediaFragment : LogFragment() {

    private lateinit var viewBinding: FragmentMediaBinding
    private val mediaFragmentArgs: MediaFragmentArgs by navArgs()
    private val viewModel: MediaFragmentViewModel by viewModels()

    private lateinit var mainActivity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = requireActivity() as MainActivity
        setHasOptionsMenu(true)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.media_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                mainActivity.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}