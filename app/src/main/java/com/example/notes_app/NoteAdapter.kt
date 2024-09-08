package com.example.notes_app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class NoteAdapter(context: Context?, notes: List<Note?>?) :
    ArrayAdapter<Note?>(context!!, 0, notes!!) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val note = getItem(position)
        if (convertView == null) convertView =
            LayoutInflater.from(context).inflate(R.layout.note_cell, parent, false)

        val title = convertView!!.findViewById<TextView>(R.id.cellTitle)
        val desc = convertView.findViewById<TextView>(R.id.cellDesc)

        title.text = note!!.title
        desc.text = note.description

        return convertView
    }
}