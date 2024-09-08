package com.example.notes_app

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.util.Date


class NoteDetailActivity : AppCompatActivity() {
    private var titleEditText: EditText? = null
    private var descEditText: EditText? = null
    private var deleteButton: Button? = null
    private var selectedNote: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detail)
        initWidgets()
        checkForEditNote()
    }

    private fun initWidgets() {
        titleEditText = findViewById(R.id.titleEditText)
        descEditText = findViewById(R.id.descriptionEditText)
        deleteButton = findViewById(R.id.deleteNoteButton)
    }

    private fun checkForEditNote() {
        val previousIntent = intent

        val passedNoteID = previousIntent.getIntExtra(Note.NOTE_EDIT_EXTRA, -1)
        selectedNote = Note.getNoteForID(passedNoteID)

        if (selectedNote != null) {
            titleEditText!!.setText(selectedNote!!.title)
            descEditText!!.setText(selectedNote!!.description)
        } else {
            deleteButton!!.visibility = View.INVISIBLE
        }
    }

    fun saveNote(view: View?) {
        val sqLiteManager: SQLiteManager? = SQLiteManager.instanceOfDatabase(this)
        val title = titleEditText!!.text.toString()
        val desc = descEditText!!.text.toString()

        if (selectedNote == null) {
            val id = Note.noteArrayList.size
            val newNote = Note(id, title, desc)
            Note.noteArrayList.add(newNote)
            sqLiteManager?.addNoteToDatabase(newNote)
        } else {
            selectedNote!!.title = title
            selectedNote!!.description = desc
            sqLiteManager?.updateNoteInDB(selectedNote!!)
        }

        finish()
    }

    fun deleteNote(view: View?) {
        selectedNote!!.deleted = Date()
        SQLiteManager.instanceOfDatabase(this)?.updateNoteInDB(selectedNote!!)
        finish()
    }
}