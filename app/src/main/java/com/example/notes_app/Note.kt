package com.example.notes_app

import java.util.Date


class Note {
    var id: Int
    var title: String
    var description: String
    var deleted: Date?

    constructor(id: Int, title: String, description: String, deleted: Date?) {
        this.id = id
        this.title = title
        this.description = description
        this.deleted = deleted
    }

    constructor(id: Int, title: String, description: String) {
        this.id = id
        this.title = title
        this.description = description
        deleted = null
    }

    companion object {
        var noteArrayList: ArrayList<Note> = ArrayList()
        var NOTE_EDIT_EXTRA: String = "noteEdit"

        fun getNoteForID(passedNoteID: Int): Note? {
            for (note in noteArrayList) {
                if (note.id == passedNoteID) return note
            }

            return null
        }

        fun nonDeletedNotes(): ArrayList<Note> {
            val nonDeleted = ArrayList<Note>()
            for (note in noteArrayList) {
                if (note.deleted == null) nonDeleted.add(note)
            }

            return nonDeleted
        }
    }
}