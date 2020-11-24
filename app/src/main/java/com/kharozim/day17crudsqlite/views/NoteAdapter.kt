package com.kharozim.day17crudsqlite.views

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kharozim.day17crudsqlite.databinding.ItemNoteBinding
import com.kharozim.day17crudsqlite.models.NoteModel

class NoteAdapter(private val context: Context, private val listener: NoteListener) :
    RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    var list = mutableListOf<NoteModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = list.size


    interface NoteListener {
        fun onClick(noteModel: NoteModel)
        fun onDelete(id: Long)
        fun onChange(noteModel: NoteModel)
    }

    inner class ViewHolder(
        private val binding: ItemNoteBinding,
        private val listener: NoteListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(noteModel : NoteModel) {
            binding.run {

            }
        }
    }
}