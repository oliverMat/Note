package br.oliver.mark4.data.dao

import androidx.room.*
import br.oliver.mark4.data.model.Categoria
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoriaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserir(categoria: Categoria)

    @Query("SELECT * FROM Categoria ORDER BY nome ASC")
    fun getCategorias(): Flow<List<Categoria>>

    @Delete
    suspend fun deletar(categoria: Categoria)
}