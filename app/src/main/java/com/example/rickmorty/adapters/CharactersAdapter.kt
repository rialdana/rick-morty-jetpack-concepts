package com.example.rickmorty.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmorty.data.models.characters.Result
import com.example.rickmorty.databinding.ItemCharacterBinding
import com.example.rickmorty.utils.GenericAdapter

class CharactersAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Result, CharactersAdapter.CharactersViewHolder>(DiffCallback),
    GenericAdapter<List<Result>> {

    companion object DiffCallback : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(
            oldItem: Result,
            newItem: Result
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: Result,
            newItem: Result
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class CharactersViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Result) {
            binding.character = character
            binding.executePendingBindings()
        }
    }

    class OnClickListener(private val clickListener: (character: Result) -> Unit) {
        fun onCharacterClicked(character: Result) = clickListener(character)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        return CharactersViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        val character = getItem(position)

        holder.itemView.setOnClickListener {
            onClickListener.onCharacterClicked(character)
        }

        holder.bind(character)
    }

    override fun setData(data: List<Result>) {
        submitList(data)
    }
}