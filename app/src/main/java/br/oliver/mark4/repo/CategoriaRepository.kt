package br.oliver.mark4.repo

import androidx.annotation.WorkerThread
import br.oliver.mark4.data.dao.CategoriaDao
import br.oliver.mark4.data.model.Categoria
import kotlinx.coroutines.flow.Flow

class CategoriaRepository(private val categoriaDao: CategoriaDao) {

    val allCategory: Flow<List<Categoria>> = categoriaDao.getCategorias()

    @WorkerThread
    suspend fun inserir(categoria: Categoria) {
        categoriaDao.inserir(categoria)
    }

    //@WorkerThread
    //suspend fun deletar(categoria: Categoria) {
    //    categoriaDao.deletarCategoria()
    //}
}