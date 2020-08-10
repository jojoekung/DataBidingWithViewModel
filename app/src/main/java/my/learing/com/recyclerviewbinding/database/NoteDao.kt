package my.learing.com.recyclerviewbinding.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {

    @Insert
    fun insertNote(noteItem: NoteItem)

    @Update
    fun updateNote(noteItem: NoteItem?)

    @Query("SELECT * FROM note_table ORDER BY  id DESC")
    fun getAllNote(): LiveData<List<NoteItem>>

    @Query("SELECT * FROM note_table WHERE id = :id")
    fun getNoteById(id: Long?): LiveData<NoteItem>

    @Query("DELETE FROM note_table WHERE id = :id")
    fun deleteNoteById(id: Long)
}