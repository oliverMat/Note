package br.oliver.mark4.view.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import br.oliver.mark4.R
import br.oliver.mark4.data.model.Categoria
import br.oliver.mark4.databinding.BottomSheetDialogOpcoesBinding
import br.oliver.mark4.viewModel.CategoriaApplication
import br.oliver.mark4.viewModel.CategoriaViewModel
import br.oliver.mark4.viewModel.CategoriaViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BDFragmentOpcoes : BottomSheetDialogFragment() {

    private val categoriaViewModel: CategoriaViewModel by viewModels {
        CategoriaViewModelFactory((requireActivity().application as CategoriaApplication).repository)
    }

    private lateinit var binding: BottomSheetDialogOpcoesBinding

    private lateinit var nomeTable: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            nomeTable = requireArguments().getString(TAG).toString()
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = BottomSheetDialogOpcoesBinding.inflate(inflater, container, false)

        binding.llDeletarTab.setOnClickListener {
            deleteAlerts()
        }

        binding.llRenomearTab.setOnClickListener { //caso queira renomear a tabela, é passado o nome para o proximo fragment

            val modalBottomSheet : BDFragmentInserir =
                BDFragmentInserir.newInstance(nomeTable)
            modalBottomSheet.show(requireActivity().supportFragmentManager, BDFragmentInserir.TAG)

            dismiss()
        }

        return binding.root
    }

    private fun deleteAlerts() {

        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(R.string.desejaDeletar)
        builder.setMessage(nomeTable)
        builder.setCancelable(false)
        builder.setPositiveButton(R.string.sim) { _: DialogInterface?, _: Int ->
            categoriaViewModel.deletar(
            Categoria(nomeTable))
            dismiss() }

        builder.setNegativeButton(R.string.nao) { _, _ -> }

        val dialog = builder.create()
        dialog.show()
    }

    companion object {
        const val TAG = "BottomSheet"

        fun newInstance(nomeTabela: String?): BDFragmentOpcoes {
            val fragment = BDFragmentOpcoes()
            val args = Bundle()
            args.putString(TAG, nomeTabela)
            fragment.arguments = args
            return fragment
        }
    }
}