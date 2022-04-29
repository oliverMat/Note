package br.oliver.mark4.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.oliver.mark4.R
import br.oliver.mark4.databinding.ActivityCategoriaBinding
import br.oliver.mark4.view.adapter.CategoriaFragmentAdapter
import br.oliver.mark4.view.fragments.BDFragmentInserir
import br.oliver.mark4.view.fragments.BDFragmentOpcoes
import br.oliver.mark4.viewModel.CategoriaApplication
import br.oliver.mark4.viewModel.CategoriaViewModel
import br.oliver.mark4.viewModel.CategoriaViewModelFactory
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

            val modalBottomSheet : BDFragmentInserir =
                BDFragmentInserir.newInstance("")
            modalBottomSheet.show(supportFragmentManager, BDFragmentInserir.TAG)

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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.categoria_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.opcoes_tab){

            val modalBottomSheet : BDFragmentOpcoes =
            BDFragmentOpcoes.newInstance(nomeTable)
            modalBottomSheet.show(supportFragmentManager, BDFragmentOpcoes.TAG)

            return true
        }

        return super.onOptionsItemSelected(item)
    }
}