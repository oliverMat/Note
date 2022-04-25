package br.oliver.mark4.viewModel

import androidx.lifecycle.*
import br.oliver.mark4.data.model.Categoria
import br.oliver.mark4.repo.CategoriaRepository
import kotlinx.coroutines.launch

class CategoriaViewModel (private val repository: CategoriaRepository) : ViewModel() {

    fun inserir(categoria: Categoria) = viewModelScope.launch {
        repository.inserir(categoria)
    }

    fun rename(nomeTable: String, novoNome: String) = viewModelScope.launch {
        repository.rename(nomeTable, novoNome)
    }

    fun deletar(categoria: Categoria) = viewModelScope.launch {
        repository.deletar(categoria)
    }

    val allCategory: LiveData<List<Categoria>> = repository.allCategory.asLiveData()
}

class CategoriaViewModelFactory(private val repository: CategoriaRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoriaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CategoriaViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
