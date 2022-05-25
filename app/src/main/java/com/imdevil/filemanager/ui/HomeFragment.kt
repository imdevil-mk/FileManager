package com.imdevil.filemanager.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.imdevil.filemanager.R
import com.imdevil.filemanager.bean.MediaCategory
import com.imdevil.filemanager.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var viewBinding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.image.setOnClickListener {
            navigate(MediaCategory.IMAGE)
        }
        viewBinding.video.setOnClickListener {
            navigate(MediaCategory.VIDEO)
        }
        viewBinding.audio.setOnClickListener {
            navigate(MediaCategory.AUDIO)
        }
    }

    private fun navigate(category: MediaCategory) {
        val action =
            HomeFragmentDirections.actionHomeFragmentToMediaFragment(category)
        findNavController().navigate(action)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.main_toolbar_menu, menu)
    }
}