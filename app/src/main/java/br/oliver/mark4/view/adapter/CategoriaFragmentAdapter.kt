package br.oliver.mark4.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import br.oliver.mark4.data.model.Categoria
import br.oliver.mark4.view.NotaFragment

class CategoriaFragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, private val table: List<Categoria>) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun createFragment(position: Int): Fragment {
        val listTable: Categoria = table[position]
        return NotaFragment.newInstance(listTable.nome)
    }

    override fun getItemCount(): Int {
        return table.size
    }

}