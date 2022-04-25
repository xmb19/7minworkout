package tr.ornek.workoutin7minutes

import android.app.Application

class EgzersizApp: Application() {
    val db by lazy {
        GecmisDatabase.getInstance(this)
    }
}