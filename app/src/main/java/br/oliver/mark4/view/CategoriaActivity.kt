package br.oliver.mark4.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import br.oliver.mark4.data.model.Categoria
import br.oliver.mark4.databinding.ActivityCategoriaBinding
import br.oliver.mark4.view.adapter.CategoriaFragmentAdapter
import br.oliver.mark4.viewModel.CategoriaApplication
import br.oliver.mark4.viewModel.CategoriaViewModel
import br.oliver.mark4.viewModel.CategoriaViewModelFactory
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class CategoriaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoriaBinding
    private var listTasks: List<Categoria> = ArrayList()


    private val categoriaViewModel: CategoriaViewModel by viewModels {
        CategoriaViewModelFactory((application as CategoriaApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoriaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        categoriaViewModel.allCategory.observe(owner = this) { categorias ->
            categorias.forEach() {  binding.tabLayout.addTab(binding.tabLayout.newTab().setText(it.nome))
                listTasks = listOf(it)
            }
        }
        val adapter = CategoriaFragmentAdapter(supportFragmentManager, lifecycle, listTasks)
        binding.viewPager2.adapter = adapter

        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.viewPager2.currentItem = tab.position
                //nomeTable = tab.text.toString()
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        binding.viewPager2.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }
        })

    }
}