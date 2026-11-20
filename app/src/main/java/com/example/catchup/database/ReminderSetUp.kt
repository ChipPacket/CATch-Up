package com.example.catchup.database

import androidx.room3.ColumnInfo
import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Entity
import androidx.room3.Insert
import androidx.room3.PrimaryKey
import androidx.room3.Query
import kotlinx.coroutines.flow.Flow

// https://medium.com/@caayush/room-tutorial-part-2-insert-and-access-data-61d0efb5a864

// ROOM ENTITY
@Entity(tableName = "reminders")
data class Reminder(
    /// display values
    @ColumnInfo(name = "reminder_desc") val name: String,
    @ColumnInfo(name = "time_hour") val alarmTimeHour: Int,
    @ColumnInfo(name = "time_minutes") val alarmTimeMinutes: Int,
    @ColumnInfo(name = "date") val reminderDateString: String,

    // actual date time info
    @ColumnInfo(name = "date_millis") val dateMillis: Long, // timestamp at start of the day (local midnight)
    @ColumnInfo (name="time_millis") val alarmTimeMillis: Long = 0L,

    // booleans
    @ColumnInfo(name = "completed") val completed: Boolean = false, // has it been completed today
    @ColumnInfo(name = "enabled") val enabled: Boolean = true, // is the alarm currently enabled

    @PrimaryKey(autoGenerate = true) val id: Int = 0,
) {
    val formattedTime: String
        get() {
            val h = if (alarmTimeHour == 0 || alarmTimeHour == 12) 12 else alarmTimeHour % 12
            val amPm = if (alarmTimeHour < 12) "AM" else "PM"
            return String.format("%02d:%02d %s", h, alarmTimeMinutes, amPm)
        }

    fun formattedTime24h(): String {
        return String.format("%02d:%02d", alarmTimeHour, alarmTimeMinutes)
    }
}
// ROOM ENTITY END

// ROOM DAO
@Dao
interface ReminderDao {
    @Insert
    suspend fun insert(reminder: Reminder)

    @Delete
    suspend fun delete(reminder: Reminder)

    @Query("SELECT * FROM reminders")
    fun getAllReminders(): Flow<List<Reminder>>

    @Query("SELECT * FROM reminders WHERE id = :id")
    suspend fun getReminderById(id: Int): Reminder?

    @Query("UPDATE reminders SET completed = :completed WHERE id = :id")
    suspend fun updateCompletionStatus(id: Int, completed: Boolean)

    //@Query("UPDATE reminders SET completed = :completed WHERE id = :id")
    //suspend fun updateCompletionStatus(id: Int, completed: Boolean)

    //@Update
    //suspend fun update(reminder: Reminder)
}
// ROOM DAO END


