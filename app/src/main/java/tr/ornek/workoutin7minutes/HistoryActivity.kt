package tr.ornek.workoutin7minutes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import tr.ornek.workoutin7minutes.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {

    private var binding:ActivityHistoryBinding?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarGecmisActivity)
        if(supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "GEÇMİŞ"
        }

        binding?.toolbarGecmisActivity?.setNavigationOnClickListener {
            onBackPressed()
        }
        val gecmisDao = (application as EgzersizApp).db.gecmisDao()
        getAllCompletedDates(gecmisDao)

    }

    private fun getAllCompletedDates(gecmisDao: GecmisDao){
        lifecycleScope.launch {
            gecmisDao.fetchAllDates().collect { getAllCompletedDatesList ->
               if(getAllCompletedDatesList.isNotEmpty()){
                   binding?.tvGecmis?.visibility =View.VISIBLE
                   binding?.rvGecmis?.visibility=View.VISIBLE
                   binding?.tvDataYok?.visibility= View.INVISIBLE

                   binding?.rvGecmis?.layoutManager = LinearLayoutManager(this@HistoryActivity)
                   val dates = ArrayList<String>()
                   for(date in getAllCompletedDatesList){
                       dates.add(date.date)
                   }
                   val gecmisAdapter = GecmisAdapter (dates)
                   binding?.rvGecmis?.adapter = gecmisAdapter

               } else {
                   binding?.tvGecmis?.visibility =View.GONE
                   binding?.rvGecmis?.visibility=View.GONE
                   binding?.tvDataYok?.visibility= View.VISIBLE
               }

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}