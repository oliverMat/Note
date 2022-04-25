package br.oliver.mark4.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.oliver.mark4.R
import br.oliver.mark4.data.model.Categoria
import br.oliver.mark4.databinding.ActivityCategoriaBinding
import br.oliver.mark4.view.adapter.CategoriaFragmentAdapter
import br.oliver.mark4.viewModel.CategoriaApplication
import br.oliver.mark4.viewModel.CategoriaViewModel
import br.oliver.mark4.viewModel.CategoriaViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator

class CategoriaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoriaBinding

    private lateinit var nomeTable : String

    private val categoriaViewModel: CategoriaViewModel by viewModels {
        CategoriaViewModelFactory((application as CategoriaApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoriaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        startComponent()
        initViewPager()

    }

    private fun startComponent() {
        val toolbar = binding.toolbarCategoria
        toolbar.title = ""
        setSupportActionBar(toolbar)

        binding.fabAddTab.setOnClickListener {

            addTabBottomSheet(false)

        }

    }

    private fun initViewPager(){

        val adapter = CategoriaFragmentAdapter(this)

        categoriaViewModel.allCategory.observe(this) {
            adapter.addFragment(it)

            TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
                tab.text = adapter.getPositionName(position)
            }.attach()

        }


        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                nomeTable = tab.text.toString()
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        binding.viewPager2.adapter = adapter

    }

    private fun addTabBottomSheet(edit: Boolean ) {

        val dialog = BottomSheetDialog(this, R.style.BaseBottomSheetDialog)

        val view = layoutInflater.inflate(R.layout.bottom_sheet_add_tab, null)

        val btnAdd = view.findViewById<TextView>(R.id.buttonAddTab)
        val btnClose = view.findViewById<ImageButton>(R.id.buttonCloseTab)
        val editTab = view.findViewById<EditText>(R.id.editTextAddTab)

        if (edit) {
            editTab.hint = nomeTable
        }

        btnAdd.setOnClickListener {

            if (editTab.text.isNotEmpty()) {

                if (edit) {
                    categoriaViewModel.rename(nomeTable, editTab.text.toString())
                }else {
                    categoriaViewModel.inserir(Categoria(editTab.text.toString()))
                }

                dialog.dismiss()
            }else {
                Toast.makeText(this,R.string.DigiteUmNome,Toast.LENGTH_SHORT).show()
            }
        }
        btnClose.setOnClickListener {
            dialog.dismiss()
        }
        dialog.setCancelable(false)
        dialog.setContentView(view)
        dialog.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.categoria_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.renomear_tab){

            addTabBottomSheet(true)

        } else if (id == R.id.deletar_tab) {

            categoriaViewModel.deletar(Categoria(nomeTable))
        }

        return super.onOptionsItemSelected(item)
    }
}