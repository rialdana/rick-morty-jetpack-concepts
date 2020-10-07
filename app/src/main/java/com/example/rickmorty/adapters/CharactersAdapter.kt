package com.example.rickmorty.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmorty.data.models.characters.Character
import com.example.rickmorty.databinding.ItemCharacterBinding
import com.example.rickmorty.utils.GenericAdapter

class CharactersAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Character, CharactersAdapter.CharactersViewHolder>(DiffCallback),
    GenericAdapter<List<Character>> {

    companion object DiffCallback : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(
            oldItem: Character,
            newItem: Character
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: Character,
            newItem: Character
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class CharactersViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character) {
            binding.character = character
            binding.executePendingBindings()
        }
    }

    class OnClickListener(private val clickListener: (character: Character) -> Unit) {
        fun onCharacterClicked(character: Character) = clickListener(character)
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

    override fun setData(data: List<Character>) {
        submitList(data)
    }
}