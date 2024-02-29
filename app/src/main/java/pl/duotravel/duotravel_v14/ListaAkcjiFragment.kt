package pl.duotravel.duotravel_v14

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import pl.duotravel.duotravel_v14.databinding.FragmentListaAkcjiBinding

class ListaAkcjiFragment : Fragment() {

    private var _binding: FragmentListaAkcjiBinding? = null //info że _binding może być "null"
    private val binding get() = _binding!! //val który wskazuje "_" i rzutuje na nie "null"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListaAkcjiBinding.inflate(layoutInflater, container, false) //rozdmuchanie
        return binding.root //zwrócenie roota
    }

    // ustawienie funkcji przejścia do następnych aktywności dla button-ów
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    view.findViewById<Button>(R.id.mapaButton).setOnClickListener {
            val i = Intent(activity, MapyActivity::class.java)
            activity?.startActivity(i)
    }

    view.findViewById<Button>(R.id.zdjeciaButton).setOnClickListener {
            val i = Intent(activity, ZdjeciaActivity::class.java)
            activity?.startActivity(i)
        }

    view.findViewById<Button>(R.id.notatkisButton).setOnClickListener {
            val i = Intent(requireActivity(), DzennikPodrozy::class.java)
            activity?.startActivity(i)
    }
    }
}
