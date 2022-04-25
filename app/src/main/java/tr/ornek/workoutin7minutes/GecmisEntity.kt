package tr.ornek.workoutin7minutes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="gecmis-tablo")
data class GecmisEntity(
    @PrimaryKey
    val date:String
)

