package pl.duotravel.duotravel_v14.repository

import android.app.Application
import pl.duotravel.duotravel_v14.DzennikPodrozy
import pl.duotravel.duotravel_v14.db.NoteDatabase
import pl.duotravel.duotravel_v14.model.Note
import kotlin.coroutines.coroutineContext

// repozytorium dla dziennika podrozy

class NoteRepository(private val db: NoteDatabase) {

    suspend fun insertNote(note: Note) = db.getNoteDao().insertNote(note)
    suspend fun updateNote(note: Note) = db.getNoteDao().updateNote(note)
    suspend fun deleteNote(note: Note) = db.getNoteDao().deleteNote(note)
    fun getAllNotes() = db.getNoteDao().getAllNotes()
    fun searchNote(query: String?) = db.getNoteDao().searchNote(query)

}