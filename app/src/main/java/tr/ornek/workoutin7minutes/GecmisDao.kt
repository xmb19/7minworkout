package tr.ornek.workoutin7minutes

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GecmisDao {
    @Insert
    suspend fun insert (gecmisEntity: GecmisEntity)

    @Query("SELECT * FROM `gecmis-tablo`")
    fun fetchAllDates(): Flow<List<GecmisEntity>>

}