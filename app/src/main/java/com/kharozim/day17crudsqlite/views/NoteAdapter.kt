package com.kharozim.day17crudsqlite.views

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kharozim.day17crudsqlite.R
import com.kharozim.day17crudsqlite.databinding.ItemNoteBinding
import com.kharozim.day17crudsqlite.models.NoteModel

class NoteAdapter(private val context: Context, private val listener: NoteListener) :
    RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    var list = mutableListOf<NoteModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.ViewHolder {
        return ViewHolder(
            ItemNoteBinding.inflate(LayoutInflater.from(context), parent, false),
            listener
        )
    }

    override fun onBindViewHolder(holder: NoteAdapter.ViewHolder, position: Int) {
        holder.bindData(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun updateNote(noteModel: NoteModel) {
        val index = list.indexOfFirst { it.id == noteModel.id }
        if (index != -1) {
            list[index] = noteModel
            notifyItemChanged(index)
        }
    }

    fun addNote(noteModel: NoteModel) {
        list.add(0, noteModel)
        notifyItemInserted(0)
    }

    fun deleteNote(id: Long) {
        val index = list.indexOfFirst { it.id == id }
        if (index != -1){
            list.removeAt(index)
            notifyItemRemoved(index)
        }
    }


    interface NoteListener {
        fun onClick(noteModel: NoteModel)
        fun onDelete(id: Long)
        fun onChange(noteModel: NoteModel)
    }

    inner class ViewHolder(
        private val binding: ItemNoteBinding,
        private val listener: NoteListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(noteModel: NoteModel) {
            binding.run {
                tvNote.text = noteModel.catatan

                root.setOnClickListener { listener.onClick(noteModel) }
                ivDel.setOnClickListener { listener.onDelete(noteModel.id) }
                ivStatus.setImageResource(if (noteModel.status) R.drawable.ic_done else R.drawable.ic_note)
                ivStatus.setOnClickListener { listener.onChange(noteModel) }
            }
        }
    }
}