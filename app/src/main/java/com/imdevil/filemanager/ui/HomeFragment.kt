package com.imdevil.filemanager.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.imdevil.filemanager.bean.MediaCategory
import com.imdevil.filemanager.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var viewBinding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        viewBinding.video.setOnClickListener {
            val action =
                HomeFragmentDirections.actionHomeFragmentToMediaFragment(MediaCategory.VIDEO)
            findNavController().navigate(action)
        }
        viewBinding.audio.setOnClickListener {
            val action =
                HomeFragmentDirections.actionHomeFragmentToMediaFragment(MediaCategory.AUDIO)
            findNavController().navigate(action)
        }
    }
}