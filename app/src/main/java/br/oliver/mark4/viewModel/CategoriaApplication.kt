package br.oliver.mark4.viewModel

import android.app.Application
import br.oliver.mark4.data.NoteRoomDb
import br.oliver.mark4.repo.CategoriaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class CategoriaApplication : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    private val database by lazy { NoteRoomDb.getDatabase(this, applicationScope) }
    val repository by lazy { CategoriaRepository(database.CategoriaDao()) }
}