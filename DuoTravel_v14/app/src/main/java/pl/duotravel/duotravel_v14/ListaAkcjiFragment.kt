package pl.duotravel.duotravel_v14

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.dynamic.OnDelegateCreatedListener
import com.google.android.gms.maps.MapView
import pl.duotravel.duotravel_v14.databinding.ActivityMapyBinding
import pl.duotravel.duotravel_v14.databinding.FragmentListaAkcjiBinding
import pl.duotravel.duotravel_v14.databinding.FragmentMapyBinding

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

    view.findViewById<Button>(R.id.notatkisButton).setOnClickListener {
        val i = Intent(requireActivity(), DzennikPodrozy::class.java)
        activity?.startActivity(i)
    }
    }
}
