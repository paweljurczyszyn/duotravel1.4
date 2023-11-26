package pl.duotravel.duotravel_v14.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pl.duotravel.duotravel_v14.model.Note
import pl.duotravel.duotravel_v14.repository.NoteRepository

// ViewModel dla dziennika podróży
class NoteViewModel(private val noteRepository: NoteRepository) : ViewModel() {
    //     val allNotes: LiveData<List<Note>> = noteRepository.getAllNotes() //LiveData przechowujące listę notatek

    // metody dostępowe do operacji związanych z ViewModel

    fun addNote(note: Note) =
        viewModelScope.launch {
            noteRepository.insertNote(note)
        }

    fun deleteNote(note: Note) =
        viewModelScope.launch {
            noteRepository.deleteNote(note)
        }

    fun updateNote(note: Note) =
        viewModelScope.launch {
            noteRepository.updateNote(note)
        }

    fun getAllNote() = noteRepository.getAllNotes()

    fun searchNote(query: String?) =
        noteRepository.searchNote(query)

    companion object {
        // Klasa fabryki do dostarczania ViewModel z zależnościami
        fun create(noteRepository: NoteRepository): NoteViewModel {
            return NoteViewModel(noteRepository)
        }
    }
}