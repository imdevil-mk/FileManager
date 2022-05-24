package com.imdevil.filemanager

import android.os.Bundle

class MainActivity : PermissionsActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStoragePermissionGranted() {
        super.onStoragePermissionGranted()

    }
}