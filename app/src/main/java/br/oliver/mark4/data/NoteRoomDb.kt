package br.oliver.mark4.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import br.oliver.mark4.data.dao.CategoriaDao
import br.oliver.mark4.data.model.Categoria
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Categoria::class], version = 1)
abstract class NoteRoomDb : RoomDatabase() {

    abstract fun CategoriaDao(): CategoriaDao

    companion object {
        @Volatile
        private var INSTANCE: NoteRoomDb? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): NoteRoomDb {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                NoteRoomDb::class.java,"Note")
                    .fallbackToDestructiveMigration()
                    .addCallback(NoteRoomDbCallback(scope))
                    .build()

                INSTANCE = instance

                // return instance
                instance
            }
        }

        private class NoteRoomDbCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.CategoriaDao())
                    }
                }
            }
        }

        suspend fun populateDatabase(categoriaDao: CategoriaDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.

            val categorias = Categoria("Note")
            categoriaDao.inserir(categorias)

        }
    }
}