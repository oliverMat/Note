package br.oliver.mark4.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.oliver.mark4.R

class NotaFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            //nomeTable = arguments!!.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        //recyclerTarefas = view!!.findViewById(R.id.recyclerTarefas)
        //iv_iconVazio = view!!.findViewById(R.id.iv_iconVazio)
        //listar()
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    override fun onResume() {
        listar()
        super.onResume()
    }

    fun listar() {
        //notaDAO = NotaDAO(requireActivity())
        //listTasks = notaDAO.listar(nomeTable)
        //notaAdapter = NotaAdapter(listTasks)
        //val layoutManager = StaggeredGridLayoutManager(
        //    Grid_Largura.calculateNoOfColumns(requireActivity(), 180),
        //    StaggeredGridLayoutManager.VERTICAL
        //)
        //recyclerTarefas.setLayoutManager(layoutManager)
        //recyclerTarefas.setHasFixedSize(true)
        //notaAdapter.setHasStableIds(true)
        //Collections.reverse(listTasks)
        //recyclerTarefas.setAdapter(notaAdapter)
        //if (listTasks!!.isEmpty()) {
        //    iv_iconVazio!!.visibility = View.VISIBLE
        //    Glide.with(iv_iconVazio!!.context).load(R.drawable.ic_text_branco_gelo)
        //        .into(iv_iconVazio)
        //} else {
        //    iv_iconVazio!!.visibility = View.GONE
        //}
    }

    companion object {
        private const val ARG = "table"
        fun newInstance(nomeTabela: String?): NotaFragment {
            val fragment = NotaFragment()
            val args = Bundle()
            args.putString(ARG, nomeTabela)
            fragment.arguments = args
            return fragment
        }
    }
}