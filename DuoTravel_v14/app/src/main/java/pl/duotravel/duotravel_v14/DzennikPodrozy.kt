package pl.duotravel.duotravel_v14

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import pl.duotravel.duotravel_v14.databinding.ActivityDzennikPodrozyBinding
import pl.duotravel.duotravel_v14.db.NoteDatabase
import pl.duotravel.duotravel_v14.repository.NoteRepository
import pl.duotravel.duotravel_v14.viewmodel.NoteViewModel
import pl.duotravel.duotravel_v14.viewmodel.NoteViewModelFactory

class DzennikPodrozy : AppCompatActivity() {

    private lateinit var binding: ActivityDzennikPodrozyBinding
    lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDzennikPodrozyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        setUpViewModel()
    }

    private fun setUpViewModel() {
        val noteRepository = NoteRepository(
            NoteDatabase(this)
        )

        val viewModelFactory = NoteViewModelFactory(application, noteRepository)

        noteViewModel = ViewModelProvider(this, viewModelFactory)[NoteViewModel::class.java]
    }
}