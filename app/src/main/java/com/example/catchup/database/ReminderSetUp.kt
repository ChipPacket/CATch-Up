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
    @ColumnInfo(name = "time_display") val time: String,
    //@ColumnInfo(name = "date_display") val date: String,

    // time data
    @ColumnInfo(name = "time_millis") val timeMillis: Long,

    // booleans
    @ColumnInfo(name = "completed") val completed: Boolean = false, // has it been completed today
    @ColumnInfo(name = "enabled") val enabled: Boolean = true, // is the alarm currently enabled

    @PrimaryKey(autoGenerate = true) val id: Int = 0,
)
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
    suspend fun getReminderById(id: Int): Reminder

    @Query("UPDATE reminders SET completed = :completed WHERE id = :id")
    suspend fun updateCompletionStatus(id: Int, completed: Boolean)

    //@Query("UPDATE reminders SET completed = :completed WHERE id = :id")
    //suspend fun updateCompletionStatus(id: Int, completed: Boolean)

    //@Update
    //suspend fun update(reminder: Reminder)
}
// ROOM DAO END


