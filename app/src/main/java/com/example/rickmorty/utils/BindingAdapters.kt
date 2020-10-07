package com.example.rickmorty.utils

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
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

//@BindingAdapter("app:setRecyclerViewList")
//fun bindRecyclerViewWithList(recyclerView: RecyclerView, characters: List<Result>?) {
//    characters?.let {
//        val adapter = (recyclerView.adapter as CharactersAdapter)
//        adapter.submitList(characters)
//    }
//}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("app:setRecyclerViewList")
fun <T> bindRecyclerViewWithList(recyclerView: RecyclerView, data: T?) {
    data?.let {
        if (recyclerView.adapter is GenericAdapter<*>) {
            (recyclerView.adapter as GenericAdapter<T>).setData(data)
        }
    }
}

@BindingAdapter("app:errorMessageStatus", "app:errorMessageText")
fun bindErrorMessageWithTextView(textView: TextView, status: LoadingStatus?, message: String?) {
    status?.let {
        message?.let {
            if (status == LoadingStatus.ERROR) {
                textView.visibility = View.VISIBLE
                textView.text = message
            } else {
                textView.visibility = View.GONE
            }
        }
    }
}

