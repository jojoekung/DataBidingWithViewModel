package my.learing.com.recyclerviewbinding.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class NoteItem(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name = "note")
    var note: String,

    @ColumnInfo(name = "title")
    var title: String
)