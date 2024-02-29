package pl.duotravel.duotravel_v14


import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import pl.duotravel.duotravel_v14.databinding.FragmentUpdateNoteBinding
import pl.duotravel.duotravel_v14.model.Note
import pl.duotravel.duotravel_v14.viewmodel.NoteViewModel


class UpdateNoteFragment : Fragment(R.layout.fragment_update_note) {

    private var _binding: FragmentUpdateNoteBinding? = null
    private val binding get() = _binding!!

    private val args: UpdateNoteFragmentArgs by navArgs()
    private lateinit var currentNote: Note
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentUpdateNoteBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteViewModel = (activity as DzennikPodrozy).noteViewModel
        currentNote = args.note!!

        binding.etNoteBodyUpdate.setText(currentNote.noteBody)
        binding.etNoteTitleUpdate.setText(currentNote.noteTitle)

        binding.fabDone.setOnClickListener {
            val title = binding.etNoteTitleUpdate.text.toString().trim()
            val body = binding.etNoteBodyUpdate.text.toString().trim()

            if (title.isNotEmpty()) {
                val note = Note(currentNote.id, title, body)
                noteViewModel.updateNote(note)

                view.findNavController().navigate(R.id.action_updateNoteFragment_to_homeFragment)

            } else {
                activity?.toast("Wprowadź nazwę lokalizacji")
            }
        }
        }


        private fun deleteNote() {
            AlertDialog.Builder(activity).apply {
                setTitle("Usuń dzennik")
                setMessage("Czy na pewno chcesz całkowicie usunąć dziennik?")
                setPositiveButton("USUŃ") { _, _ ->
                    noteViewModel.deleteNote(currentNote)
                    view?.findNavController()?.navigate(
                        R.id.action_updateNoteFragment_to_homeFragment
                    )
                }
                setNegativeButton("ANULUJ", null)
            }.create().show()

        }

        override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
            menu.clear()
            inflater.inflate(R.menu.update_menu, menu)
            super.onCreateOptionsMenu(menu, inflater)
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.menu_delete -> {
                    deleteNote()
                }
            }

            return super.onOptionsItemSelected(item)
        }

        override fun onDestroy() {
            super.onDestroy()
            _binding = null

    }
}

