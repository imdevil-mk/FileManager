package com.imdevil.filemanager

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.imdevil.filemanager.bean.MediaCategory
import com.imdevil.filemanager.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : PermissionsActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val host: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_nav_host) as NavHostFragment?
                ?: return
        val navController = host.navController

        setSupportActionBar(binding.toolbar)

        binding.bottomNav.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, arguments ->
            var title = R.string.app_name
            var showUp = false
            when (destination.id) {
                R.id.recentFragment -> {
                    title = R.string.recently
                }
                R.id.homeFragment -> {
                    title = R.string.category
                }
                R.id.mediaImagesByDirFragment -> {
                    showUp = true
                    title = R.string.media_image
                }
                R.id.mediaFragment -> {
                    showUp = true
                    when (arguments?.get("media_type")) {
                        MediaCategory.IMAGE_BY_PATH -> title = R.string.media_image
                        MediaCategory.VIDEO -> title = R.string.media_video
                        MediaCategory.AUDIO -> title = R.string.media_audio
                    }
                }
            }
            binding.toolbar.title = getString(title)
            supportActionBar?.setDisplayShowHomeEnabled(showUp)
            supportActionBar?.setDisplayHomeAsUpEnabled(showUp)
        }
    }

    override fun onStoragePermissionGranted() {
        super.onStoragePermissionGranted()
    }
}