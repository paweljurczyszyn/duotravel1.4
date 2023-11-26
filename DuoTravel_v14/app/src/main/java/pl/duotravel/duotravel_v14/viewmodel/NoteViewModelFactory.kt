package pl.duotravel.duotravel_v14.viewmodel

import android.app.Activity
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pl.duotravel.duotravel_v14.DzennikPodrozy
import pl.duotravel.duotravel_v14.repository.NoteRepository

class NoteViewModelFactory(
    private val application: Application,
    private val noteRepository: NoteRepository
) : ViewModelProvider.AndroidViewModelFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NoteViewModel(noteRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}