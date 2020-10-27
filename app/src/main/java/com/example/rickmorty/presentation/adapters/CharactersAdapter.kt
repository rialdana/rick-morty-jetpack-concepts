package com.example.rickmorty.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmorty.databinding.ItemCharacterBinding
import com.example.rickmorty.framework.utils.GenericAdapter

/**
 * Adapter created to display characters
 */
class CharactersAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<com.example.rickmorty.domain.Character, CharactersAdapter.CharactersViewHolder>(DiffCallback),
    GenericAdapter<List<com.example.rickmorty.domain.Character>> {

    /**
     * DiffCallback algorithm to check when the item of the view changed
     */
    companion object DiffCallback : DiffUtil.ItemCallback<com.example.rickmorty.domain.Character>() {
        override fun areItemsTheSame(
            oldItem: com.example.rickmorty.domain.Character,
            newItem: com.example.rickmorty.domain.Character
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: com.example.rickmorty.domain.Character,
            newItem: com.example.rickmorty.domain.Character
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }

    /**
     * ViewHolder to bind each character to its respective data binding class
     */
    class CharactersViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: com.example.rickmorty.domain.Character) {
            binding.character = character
            binding.executePendingBindings()
        }
    }

    /**
     * ClickListener that returns a character every time the function is called
     */
    class OnClickListener(private val clickListener: (character: com.example.rickmorty.domain.Character) -> Unit) {
        fun onCharacterClicked(character: com.example.rickmorty.domain.Character) = clickListener(character)
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

    override fun setData(data: List<com.example.rickmorty.domain.Character>) {
        submitList(data)
    }
}