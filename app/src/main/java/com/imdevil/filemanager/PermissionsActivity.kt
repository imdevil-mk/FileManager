package com.imdevil.filemanager

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

private const val TAG = "PermissionsActivity"

open class PermissionsActivity : AppCompatActivity() {

    private var initialStart: Boolean = true
    private lateinit var storagePermissionDialog: AlertDialog
    private lateinit var storagePermissionLauncher: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        storagePermissionDialog = AlertDialog.Builder(this)
            .setTitle("Permissions")
            .setMessage("We need the Permissions!")
            .setPositiveButton(android.R.string.ok) { _, _ ->
                if (initialStart) {
                    storagePermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                } else {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        .setData(Uri.fromParts("package", packageName, null))
                    startActivity(intent)
                }
                storagePermissionDialog.dismiss()
            }
            .setNegativeButton(android.R.string.cancel) { _, _ ->
                finish()
            }
            .create()

        storagePermissionLauncher =
            registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (!isGranted) {
                    requestStoragePermission(false)
                } else {
                    onStoragePermissionGranted()
                }
            }

        checkForExternalPermission()
    }

    private fun checkForExternalPermission() {
        if (!checkStoragePermission()) {
            requestStoragePermission(true)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            //requestAllFilesAccess(this)
        }
    }

    private fun checkStoragePermission(): Boolean {
        // Verify that all required contact permissions have been granted.
        return (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED)
    }

    private fun requestStoragePermission(initial: Boolean) {
        initialStart = initial
        if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Log.d(TAG, "requestStoragePermission: rationale")
            initialStart = true
            storagePermissionDialog.show()
        } else if (initialStart) {
            Log.d(TAG, "requestStoragePermission: initialStart = false")
            storagePermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        } else {
            Log.d(TAG, "requestStoragePermission: else")
            storagePermissionDialog.show()
        }
    }

    open fun onStoragePermissionGranted() {
    }
}