package com.example.rickmorty.presentation.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.rickmorty.presentation.adapters.CharactersAdapter
import com.example.rickmorty.databinding.FragmentCharactersBinding
import com.example.rickmorty.framework.utils.EventObserver
import org.koin.android.ext.android.inject

/**
 * CharactersFragment
 */
class CharactersFragment : Fragment() {

    private val viewModel: CharactersViewModel by inject()
    private lateinit var binding: FragmentCharactersBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharactersBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.recyclerViewCharacters.adapter =
            CharactersAdapter(CharactersAdapter.OnClickListener { character ->
                this.findNavController()
                    .navigate(
                        CharactersFragmentDirections.openCharacterDetailAction(
                            character.id ?: -1
                        )
                    )
            })

        viewModel.charactersResultMessage.observe(viewLifecycleOwner, EventObserver {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })

        return binding.root
    }
}