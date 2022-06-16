package com.imdevil.filemanager.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.imdevil.filemanager.R
import com.imdevil.filemanager.bean.ImagesByDir
import com.imdevil.filemanager.databinding.ListImagesByDirItemBinding

class ImagesByDirAdapter :
    ListAdapter<ImagesByDir, ImagesByDirAdapter.ItemHolder>(ImagesByDirDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            ListImagesByDirItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ItemHolder(private val binding: ListImagesByDirItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val imageViews =
            listOf(R.id.images_dir_1, R.id.images_dir_2, R.id.images_dir_3, R.id.images_dir_4)

        fun bind(data: ImagesByDir) {
            binding.imagesDirTile.text = data.dirName
            data.images.forEachIndexed { index, fileInfo ->
                if (index < 4) {
                    val imageView = binding.root.findViewById<ImageView>(imageViews[index])
                    Glide.with(imageView).load(fileInfo.contentUri).into(imageView)
                }
            }
        }
    }

    class ImagesByDirDiffCallback : DiffUtil.ItemCallback<ImagesByDir>() {
        override fun areItemsTheSame(oldItem: ImagesByDir, newItem: ImagesByDir): Boolean {
            return oldItem.dirPath == newItem.dirPath
        }

        override fun areContentsTheSame(oldItem: ImagesByDir, newItem: ImagesByDir): Boolean {
            return oldItem == newItem
        }

    }
}