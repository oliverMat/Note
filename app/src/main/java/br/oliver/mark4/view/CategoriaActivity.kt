package br.oliver.mark4.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import br.oliver.mark4.R
import br.oliver.mark4.databinding.ActivityCategoriaBinding
import br.oliver.mark4.view.adapter.CategoriaFragmentAdapter
import br.oliver.mark4.viewModel.CategoriaApplication
import br.oliver.mark4.viewModel.CategoriaViewModel
import br.oliver.mark4.viewModel.CategoriaViewModelFactory
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