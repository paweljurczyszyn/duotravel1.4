package pl.duotravel.duotravel_v14

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import pl.duotravel.duotravel_v14.databinding.ActivityMainBinding
import pl.duotravel.duotravel_v14.databinding.ActivityStronaGlownaBinding

class StronaGlowna : AppCompatActivity() {
    private lateinit var binding: ActivityStronaGlownaBinding //uruchamia binding w celu edycji tekstu z xml tutaj w kotlinie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_strona_glowna)
        binding = ActivityStronaGlownaBinding.inflate(layoutInflater)
        setContentView(binding.root)  //root żeby pokazywało całość z kodu xml a nie konkretny tekst

        val firstFragment = MapyFragment()
        val secondFragment = ListaAkcjiFragment()

        supportFragmentManager.commit {//wstawianie fragmentu
            add(
                R.id.firstContainer,
                firstFragment,
                null
            )  //podajemy ID kontenera i fragemnt który chcemy w nim wstawić
            add(R.id.secondCantainer, secondFragment, null)
        }
    }
}