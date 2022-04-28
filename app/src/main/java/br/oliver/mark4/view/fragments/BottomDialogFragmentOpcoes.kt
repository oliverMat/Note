package br.oliver.mark4.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.oliver.mark4.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomDialogFragmentOpcoes : BottomSheetDialogFragment() {

    private var nomeTable: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            nomeTable = requireArguments().getString(TAG)
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {



        return inflater.inflate(R.layout.bottom_sheet_dialog_opcoes, container, false)
    }

    companion object {
        const val TAG = "BottomSheet"

        fun newInstance(nomeTabela: String?): BottomDialogFragmentOpcoes {
            val fragment = BottomDialogFragmentOpcoes()
            val args = Bundle()
            args.putString(TAG, nomeTabela)
            fragment.arguments = args
            return fragment
        }
    }
}