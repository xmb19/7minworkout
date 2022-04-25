package tr.ornek.workoutin7minutes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import tr.ornek.workoutin7minutes.databinding.ActivityVkiBinding
import java.math.BigDecimal
import java.math.RoundingMode

class VKIActivity : AppCompatActivity() {
    private var binding: ActivityVkiBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityVkiBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbarVki)
        if(supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "VKİ Hesapla"
        }

        binding?.toolbarVki?.setNavigationOnClickListener {
            onBackPressed()
        }

        binding?.btnHesaplamaBirimi?.setOnClickListener {
            if(validateBirim()){
                val uzunlukDeger: Float = binding?.etUzunluk?.text.toString().toFloat() / 100 //cm yi m ye çevirmek için 100' le böl.
                val kgDeger: Float = binding?.etAgirlik?.text.toString().toFloat()
                val vki = kgDeger / (uzunlukDeger*uzunlukDeger)

                VKISonucGor(vki)

                //TODO VKİ Sonuçlarını Göster
            }else{
                Toast.makeText(this@VKIActivity,
                    "Lütfen Geçerli Değerler Girin",
                    Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }
    private fun VKISonucGor(vki: Float) {

        val vkiLabel : String
        val vkiYorum : String

        if(vki.compareTo(15f) <=0 ) {
            vkiLabel = "Çok Fazla Zayıf"
            vkiYorum = "İdeal Kilonuzun Çok Daha Altındasınız! Beslenmenize Dikkat Edin! Daha Fazla Yemek Yeyin!"
        }else if (vki.compareTo(15f) > 0 && vki.compareTo(16f) <=0)
        {
            vkiLabel = "Çok Zayıf"
            vkiYorum = "İdeal Kilonuzun Daha Altındasınız! Beslenmenize Dikkat Edin!"
        }else if (vki.compareTo(16f) > 0 && vki.compareTo(18.5f) <=0)
        {
            vkiLabel = "Zayıf"
            vkiYorum = "İdeal Kilonuzun Altındasınız! Beslenmenize Dikkat Edin!"
        }else if (vki.compareTo(18.5f) > 0 && vki.compareTo(25f) <=0)
        {
            vkiLabel = "Normal"
            vkiYorum = "İdeal Kilonuzdasınız! Böyle Devam Edin!"
        }else if (vki.compareTo(25f) > 0 && vki.compareTo(30f) <=0)
        {
            vkiLabel = "Kilolu"
            vkiYorum = "İdeal Kilonuzun Üzerindesiniz! Egzersiz Yapmaya ve Beslenmenize Dikkat Edin!"
        }else if (vki.compareTo(30f) > 0 && vki.compareTo(35f) <=0)
        {
            vkiLabel = "Obez I (Orta Derece Obez)"
            vkiYorum = "İdeal Kilonuzun Çok Üzerindesiniz! Egzersiz Yapmaya ve Beslenmenize Dikkat Edin!"
        }else if (vki.compareTo(35f) > 0 && vki.compareTo(40f) <=0)
        {
            vkiLabel = "Obez II (Ağır Obez)"
            vkiYorum = "İdeal Kilonuzun Çok Fazla Üzerindesiniz! Hemen Harekete Geçin!"
        }else{
            vkiLabel = "Obez III (Çok Ağır Obez)"
            vkiYorum = "İdeal Kilonuzun Çok Fazla Üzerindesiniz! Hemen Harekete Geçin!"
        }

        val vkiSonuc = BigDecimal(vki.toDouble())
            .setScale(2,RoundingMode.HALF_EVEN).toString()

        binding?.VKISonuc?.visibility = View.VISIBLE
        binding?.tvVkiNumara?.text = vkiSonuc
        binding?.tvVkiTip?.text = vkiLabel
        binding?.tvVkiTanim?.text=vkiYorum

    }

    private fun validateBirim(): Boolean {
        var isValid = true
        if (binding?.etAgirlik?.text.toString().isEmpty()){
            isValid=false
        } else if (binding?.etUzunluk?.text.toString().isEmpty()){
            isValid = false
        }
        return isValid
    }

}