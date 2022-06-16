package com.imdevil.filemanager.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.FragmentActivity
import com.imdevil.filemanager.MainActivity

open class BaseFragment : LogFragment() {

    lateinit var mActivity: FragmentActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = requireActivity() as MainActivity
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                mActivity.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}