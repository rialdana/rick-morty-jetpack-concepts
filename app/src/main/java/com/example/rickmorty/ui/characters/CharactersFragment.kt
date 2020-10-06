package com.example.rickmorty.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.rickmorty.adapters.CharactersAdapter
import com.example.rickmorty.databinding.FragmentCharactersBinding
import org.koin.android.ext.android.inject

class CharactersFragment : Fragment() {

    private val viewModel: CharactersViewModel by inject()
    private lateinit var binding: FragmentCharactersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharactersBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.recyclerViewCharacters.adapter =
            CharactersAdapter(CharactersAdapter.OnClickListener { character ->

            })

        viewModel.characters.observe(viewLifecycleOwner, Observer {
            it?.let {
                val adapter = (binding.recyclerViewCharacters.adapter as CharactersAdapter)
                adapter.submitList(it.results)
            }
        })

        return binding.root
    }
}