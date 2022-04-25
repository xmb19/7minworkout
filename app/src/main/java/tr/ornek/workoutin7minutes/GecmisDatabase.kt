package tr.ornek.workoutin7minutes

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GecmisEntity::class],version = 1)
abstract class GecmisDatabase:RoomDatabase() {
    abstract fun gecmisDao():GecmisDao

    companion object{
        @Volatile
        private var INSTANCE: GecmisDatabase?= null
        fun getInstance(context: Context):GecmisDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        GecmisDatabase::class.java,
                        "gecmis_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}