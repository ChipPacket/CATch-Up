package com.example.catchup.database

import androidx.room3.ColumnInfo
import androidx.room3.ColumnTypeConverter
import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Entity
import androidx.room3.Insert
import androidx.room3.PrimaryKey
import androidx.room3.Query
import androidx.room3.Update
import kotlinx.coroutines.flow.Flow
import java.util.Date

// TYPE CONVERTERS FOR DATE
object Converters {
    @ColumnTypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @ColumnTypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}
// TYPE CONVERTERS END

// ROOM ENTITY
@Entity(tableName = "reminders")
data class Reminder(
    /// display values
    @ColumnInfo(name = "reminder_desc") val name: String,
    @ColumnInfo(name = "time") val reminderTimeString: String,
    @ColumnInfo(name = "date") val reminderDateString: String,

    // reminder scheduling
    //val reminderTime: Long, // time - will need to make a converter
    //val reminderDate: Date?, // date - need a converter

    // https://medium.com/@caayush/room-tutorial-part-2-insert-and-access-data-61d0efb5a864

    // booleans
    @ColumnInfo(name = "completed") val completed: Boolean, // has it been completed today
    @ColumnInfo(name = "enabled") val enabled: Boolean,

    @PrimaryKey(autoGenerate = true) val id: Int = 0,
)
// ROOM ENTITY END

// ROOM DAO
@Dao
interface ReminderDao {
    @Insert
    suspend fun insert(reminder: Reminder)

    @Query("SELECT * FROM reminders")
    fun getAllReminders(): Flow<List<Reminder>>

    @Delete
    suspend fun delete(reminder: Reminder)

    //@Update
    //suspend fun update(reminder: Reminder)
}
// ROOM DAO END


