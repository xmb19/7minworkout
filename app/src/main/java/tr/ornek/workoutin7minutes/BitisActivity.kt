package tr.ornek.workoutin7minutes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import tr.ornek.workoutin7minutes.databinding.ActivityBitisBinding
import java.text.SimpleDateFormat
import java.util.*

class BitisActivity : AppCompatActivity() {

    private var binding: ActivityBitisBinding?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBitisBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        setSupportActionBar(binding?.toolbarBitisActivity)

        if(supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        binding?.toolbarBitisActivity?.setNavigationOnClickListener {
            onBackPressed()
        }

        binding?.btnFinish?.setOnClickListener {
            finish()
        }

        val gecmisDao = (application as EgzersizApp).db.gecmisDao()
        addDateToDatabase(gecmisDao)

    }

    private fun addDateToDatabase(gecmisDao: GecmisDao){
        val c= Calendar.getInstance()
        val dateTime=c.time
        Log.e("Tarih: ",""+dateTime)

        val sdf = SimpleDateFormat("dd MMM yyyy HH:mm:ss",Locale.getDefault())
        val date = sdf.format(dateTime)
        Log.e("Tarih Formatı: ",""+date)

        lifecycleScope.launch{
            gecmisDao.insert(GecmisEntity(date))
            Log.e(
                "Tarih: ",
                "Eklendi..."
            )
        }

    }

}