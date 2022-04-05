package br.oliver.mark4.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import br.oliver.mark4.data.model.Categoria
import br.oliver.mark4.view.NotaFragment

class CategoriaFragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {

    private val table: MutableList<Categoria> = ArrayList()

    override fun createFragment(position: Int): Fragment {
        val listTable: Categoria = table[position]
        return NotaFragment.newInstance(listTable.nome)
    }

    override fun getItemCount(): Int = table.size

    fun addFragment(categoria: Categoria) {
        table.add(categoria)
    }

}