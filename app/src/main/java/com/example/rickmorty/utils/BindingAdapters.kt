package com.example.rickmorty.utils

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("app:loadImage")
fun bindImageWithGlide(imageView: ImageView, url: String?) {
    url?.let {
        Glide.with(imageView.context).load(url).into(imageView)
    }
}

@BindingAdapter("app:progressBarVisibility")
fun bindProgressBarWithStatus(progressBar: ProgressBar, loadingStatus: LoadingStatus?) {
    loadingStatus?.let {
        if (loadingStatus == LoadingStatus.LOADING) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }
}

@BindingAdapter("app:emptyStateLoadingStatus", "app:emptyStateListSize")
fun bindEmptyStateWithStatus(view: View, loadingStatus: LoadingStatus?, listSize: Int?) {
    loadingStatus?.let {
        listSize?.let {
            if (loadingStatus == LoadingStatus.SUCCESS) {
                view.visibility = if (listSize < 1) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
            } else {
                view.visibility = View.GONE
            }
        }
    }
}

