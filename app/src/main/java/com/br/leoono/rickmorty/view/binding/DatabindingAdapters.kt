package com.br.leoono.rickmorty.view.binding

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object DatabindingAdapters {
    @JvmStatic
    @BindingAdapter("app:imageUri")
    fun loadImageWithUri(imageView: ImageView, imageUri: String?){
        if (imageUri != null) {
            Glide.with(imageView.context).load(Uri.parse(imageUri)).into(imageView)
        }
    }
}