package tr.ornek.workoutin7minutes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import tr.ornek.workoutin7minutes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private var isPaused = false
    private var resumeFromMillis: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        binding?.flStart?.setOnClickListener{
         val intent = Intent (this,EgzersizActivity::class.java)
            startActivity(intent)
        }

        binding?.flVKI?.setOnClickListener{
         val intent = Intent (this,VKIActivity::class.java)
            startActivity(intent)
        }

        binding?.flGecmis?.setOnClickListener{
            val intent = Intent (this,HistoryActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onDestroy() {
        super.onDestroy()

        binding = null
    }

}