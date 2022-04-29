package br.oliver.mark4.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import br.oliver.mark4.R
import br.oliver.mark4.data.model.Categoria
import br.oliver.mark4.databinding.BottomSheetAddTabBinding
import br.oliver.mark4.viewModel.CategoriaApplication
import br.oliver.mark4.viewModel.CategoriaViewModel
import br.oliver.mark4.viewModel.CategoriaViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BDFragmentInserir : BottomSheetDialogFragment() {

    private val categoriaViewModel: CategoriaViewModel by viewModels {
        CategoriaViewModelFactory((requireActivity().application as CategoriaApplication).repository)
    }

    private lateinit var binding: BottomSheetAddTabBinding

    private lateinit var nomeTable: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            nomeTable = requireArguments().getString(BDFragmentOpcoes.TAG).toString()
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = BottomSheetAddTabBinding.inflate(inflater, container, false)

        binding.buttonCloseTab.setOnClickListener {
            dismiss()
        }

        binding.buttonAddTab.setOnClickListener {
            addTabBottomSheet()
        }

        if (nomeTable.isNotEmpty()){
            binding.editTextAddTab.hint = nomeTable
        }


        return binding.root
    }

    private fun addTabBottomSheet() {

        if (binding.editTextAddTab.text.isNotEmpty()) {

            if (nomeTable.isNotEmpty()){
                categoriaViewModel.rename(nomeTable, binding.editTextAddTab.text.toString())
            }else {
                categoriaViewModel.inserir(Categoria(binding.editTextAddTab.text.toString()))
            }

            dismiss()

        }else {
            Toast.makeText(requireContext().applicationContext, R.string.DigiteUmNome, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val TAG = "BottomSheet"

        fun newInstance(nomeTabela: String?): BDFragmentInserir {
            val fragment = BDFragmentInserir()
            val args = Bundle()
            args.putString(TAG, nomeTabela)
            fragment.arguments = args
            return fragment
        }
    }
}