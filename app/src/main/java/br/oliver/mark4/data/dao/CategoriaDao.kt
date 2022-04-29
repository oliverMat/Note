package br.oliver.mark4.data.dao

import androidx.room.*
import br.oliver.mark4.data.model.Categoria
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoriaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserir(categoria: Categoria)

    @Query("UPDATE or IGNORE categoria SET nome=:novoNome WHERE nome=:nomeTable")
    suspend fun rename(nomeTable: String, novoNome: String)

    @Delete
    suspend fun deletar(categoria: Categoria)

    @Query("SELECT * FROM Categoria")
    fun getCategorias(): Flow<List<Categoria>>
}