package tr.ornek.workoutin7minutes


import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import android.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import tr.ornek.workoutin7minutes.databinding.ActivityEgzersizBinding
import tr.ornek.workoutin7minutes.databinding.DialogGeriTusuBinding
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.min


class EgzersizActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private var binding : ActivityEgzersizBinding? = null

    private var restTimer: CountDownTimer? =null
    private var restProgress=  0
    private var restTimerDuration: Long = 1

    private var exerciseTimer: CountDownTimer? =null
    private var exerciseProgress =  0
    private var egzersizTimerDuration: Long = 1

    private var egzersizListesi : ArrayList<ExerciseModel>?= null
    private var currentExercisePosition = -1

    private var tts : TextToSpeech? = null
    private var player: MediaPlayer?= null


    private var egzersizAdapter: EgzersizDurumuAdapter? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEgzersizBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarEgzersiz)

        if(supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        binding?.toolbarEgzersiz?.setNavigationOnClickListener {
            customDialogForBackButton()
        }

        egzersizListesi = Constants.defaultEgzersizListesi()

        tts = TextToSpeech(this,this)


        //binding?.flProgressBar?.visibility = View.GONE
        setupRestView()
        setupEgzersizDurumuRecyclerView()
    }

    override fun onBackPressed() {
        customDialogForBackButton()
    }

    private fun customDialogForBackButton(){
        val customDialog = Dialog(this)
        val dialogBinding = DialogGeriTusuBinding.inflate(layoutInflater)
        customDialog.setContentView(dialogBinding.root)
        customDialog.setCanceledOnTouchOutside(false)
        //Evet butonuna basılırsa:
        dialogBinding.btnEvet.setOnClickListener {
            this@EgzersizActivity.finish()
            customDialog.dismiss()
        }
        //Hayır butonuna basılırsa:
        dialogBinding.btnHayir.setOnClickListener {

            customDialog.dismiss()
        }

        customDialog.show()

    }

    private fun setupEgzersizDurumuRecyclerView(){
        binding?.rvEgzersizDurumu?.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        egzersizAdapter = EgzersizDurumuAdapter(egzersizListesi!!)
        binding?.rvEgzersizDurumu?.adapter = egzersizAdapter

    }

    private fun setupRestView(){

        try {
            val soundURI = Uri.parse(
                "android.resource://tr.ornek.workoutin7minutes/"+ R.raw.bildirimsesi)
            player = MediaPlayer.create(applicationContext,soundURI)
            player?.isLooping = false
            player?.start()

        } catch (e : Exception){
            e.printStackTrace()
        }

        binding?.flRestView?.visibility = View.VISIBLE
        binding?.tvBaslik?.visibility = View.VISIBLE
        binding?.tvExerciseName?.visibility = View.INVISIBLE
        binding?.flExerciseView?.visibility = View.INVISIBLE
        binding?.ivImage?.visibility = View.INVISIBLE
        binding?.tvUpComingLabel?.visibility = View.VISIBLE
        binding?.tvUpComingExerciseName?.visibility = View.VISIBLE


        if(restTimer != null){
            restTimer?.cancel()
            restProgress = 0
        }

        binding?.tvUpComingExerciseName?.text =
            egzersizListesi!![currentExercisePosition +1].getName()

        setRestProgressBar()
    }

    private fun setupExerciseView() {
        binding?.flRestView?.visibility = View.INVISIBLE
        binding?.tvBaslik?.visibility = View.INVISIBLE
        binding?.tvExerciseName?.visibility = View.VISIBLE
        binding?.flExerciseView?.visibility = View.VISIBLE
        binding?.ivImage?.visibility = View.VISIBLE
        binding?.tvUpComingLabel?.visibility = View.INVISIBLE
        binding?.tvUpComingExerciseName?.visibility = View.INVISIBLE

        if(exerciseTimer != null){
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }

        speakOut(egzersizListesi!![currentExercisePosition].getName())


        binding?.ivImage?.setImageResource(egzersizListesi!![currentExercisePosition].getImage())
        binding?.tvExerciseName?.text = egzersizListesi!![currentExercisePosition].getName()

        setExerciseProgressBar()

    }
    private fun setRestProgressBar () {
        binding?.progressBar?.progress = restProgress
        restTimer = object: CountDownTimer (restTimerDuration*1000,1000) {
            override fun onTick(p0: Long) {
                restProgress++
                binding?.progressBar?.progress = 10 - restProgress
                binding?.tvTimer?.text = (10 - restProgress).toString()
            }
            override fun onFinish() {
                currentExercisePosition++

                egzersizListesi!![currentExercisePosition].setIsSelected(true)
                egzersizAdapter!!.notifyDataSetChanged()

                setupExerciseView()
            }
        }.start()

    }
    private fun setExerciseProgressBar () {
        binding?.progressBarExercise?.progress = exerciseProgress
        exerciseTimer = object: CountDownTimer (egzersizTimerDuration*1000,1000) {
            override fun onTick(p0: Long) {
                exerciseProgress++
                 binding?.progressBarExercise?.progress = 30 - exerciseProgress
                 binding?.tvTimerExercise?.text = (30 - exerciseProgress).toString()
            }
            override fun onFinish() {

                if (currentExercisePosition <egzersizListesi?.size!! -1) {
                    egzersizListesi!![currentExercisePosition].setIsSelected(false)
                    egzersizListesi!![currentExercisePosition].setIsCompleted(true)
                    egzersizAdapter!!.notifyDataSetChanged()
                    setupRestView()
                }else {
                    finish()
                    val intent = Intent(this@EgzersizActivity,BitisActivity::class.java)
                    startActivity(intent)
                }
            }
        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()

        if(restTimer != null) {
            restTimer?.cancel()
            restProgress = 0
        }
        if(exerciseTimer != null){
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }

        if(tts != null){
            tts!!.stop()
            tts!!.shutdown()
        }

        if(player != null) {
            player!!.stop()
        }

        binding = null
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS){
            val result = tts?.setLanguage(Locale.ROOT)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
                Log.e("TTS","Belirtilen Dil Desteklenmiyor!")
        }else {
            Log.e("TTS","Başlatma Başarısız!")
        }
    }

    private fun speakOut (text: String) {
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

}