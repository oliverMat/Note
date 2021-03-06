package br.oliver.mark4.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.viewpager2.adapter.FragmentStateAdapter
import br.oliver.mark4.data.model.Categoria
import br.oliver.mark4.util.PagerDiffUtil
import br.oliver.mark4.view.fragments.FragmentNota

class CategoriaFragmentAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    private val table: MutableList<Categoria> = ArrayList()

    override fun createFragment(position: Int): Fragment {
        val listTable: Categoria = table[position]
        return FragmentNota.newInstance(listTable.nome)
    }

    override fun getItemCount(): Int = table.size

    fun addFragment(categoria: List<Categoria>) {

        val callback = PagerDiffUtil(table, categoria)
        val diff = DiffUtil.calculateDiff(callback)
        table.clear()
        table.addAll(categoria)
        diff.dispatchUpdatesTo(this)

    }

    fun getPositionName(position: Int): String = table[position].nome

}