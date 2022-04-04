package br.oliver.mark4.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import java.util.*

class NotaFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            //nomeTable = arguments!!.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        //view = inflater.inflate(R.layout.fragment_first, container, false)
        //recyclerTarefas = view!!.findViewById(R.id.recyclerTarefas)
        //iv_iconVazio = view!!.findViewById(R.id.iv_iconVazio)
        //listar()
        return view
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
        private const val ARG_PARAM1 = "param1"
        fun newInstance(nomeTabela: String?): NotaFragment {
            val fragment = NotaFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, nomeTabela)
            fragment.arguments = args
            return fragment
        }
    }
}