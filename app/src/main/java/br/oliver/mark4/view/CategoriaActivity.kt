package br.oliver.mark4.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
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
import com.google.android.material.tabs.TabLayoutMediator

class CategoriaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoriaBinding

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

            addTabBottomSheet()

        }

    }

    private fun initViewPager(){

        val adapter = CategoriaFragmentAdapter(this)

        categoriaViewModel.allCategory.observe(this) {
            adapter.addFragment(it)
        }

        binding.viewPager2.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            categoriaViewModel.allCategory.observe(this) {
                tab.text = it[position].nome
            }
        }.attach()
    }

    private fun addTabBottomSheet() {

        val dialog = BottomSheetDialog(this, R.style.BaseBottomSheetDialog)

        val view = layoutInflater.inflate(R.layout.bottom_sheet_add_tab, null)

        val btnAdd = view.findViewById<TextView>(R.id.buttonAddTab)
        val btnClose = view.findViewById<ImageButton>(R.id.buttonCloseTab)
        val editTab = view.findViewById<EditText>(R.id.editTextAddTab)

        btnAdd.setOnClickListener {

            if (editTab.text.isNotEmpty()) {
                categoriaViewModel.inserir(Categoria(editTab.text.toString()))
                dialog.dismiss()
            }else {
                Toast.makeText(this,"Digite um nome",Toast.LENGTH_SHORT).show()
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

        if (id == R.id.add_categoria){

        }

        return super.onOptionsItemSelected(item)
    }
}