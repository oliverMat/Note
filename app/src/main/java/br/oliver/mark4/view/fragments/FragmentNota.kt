package br.oliver.mark4.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.oliver.mark4.databinding.FragmentNoteBinding

class FragmentNota : Fragment() {

    private lateinit var nomeTable: String

    private lateinit var binding: FragmentNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            nomeTable = requireArguments().getString(BDFragmentOpcoes.TAG).toString()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentNoteBinding.inflate(inflater, container, false)

        return binding.root
    }


    companion object {
        private const val ARG = "table"
        fun newInstance(nomeTabela: String?): FragmentNota {
            val fragment = FragmentNota()
            val args = Bundle()
            args.putString(ARG, nomeTabela)
            fragment.arguments = args
            return fragment
        }
    }
}