package pl.duotravel.duotravel_v14

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.snackbar.Snackbar
import pl.duotravel.duotravel_v14.databinding.ActivityMapyBinding
import pl.duotravel.duotravel_v14.databinding.FragmentListaAkcjiBinding
import pl.duotravel.duotravel_v14.databinding.FragmentMapyBinding

class MapyFragment : Fragment() {
    private var _binding: FragmentMapyBinding? = null //info że _binding może być "null"
    private val binding get() = _binding!! //val który wskazuje "_" i rzutuje na nie "null"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapyBinding.inflate(layoutInflater, container, false) //rozdmuchanie
        return binding.root //zwrócenie roota
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // ustawienia naszej nullowej referencji jako null
    }

}