package com.kharozim.day17crudsqlite.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kharozim.day17crudsqlite.databinding.FragmentHomeBinding
import com.kharozim.day17crudsqlite.models.NoteModel
import com.kharozim.day17crudsqlite.presenter.NotePresenter
import com.kharozim.day17crudsqlite.repository.NoteRepsitory
import com.kharozim.day17crudsqlite.repository.local.NoteLocalRepo
import com.kharozim.day17crudsqlite.views.NoteAdapter
import com.kharozim.day17crudsqlite.views.contract.Note


class HomeFragment : Fragment(), Note.View, NoteAdapter.NoteListener {

    private lateinit var binding: FragmentHomeBinding
    private val adapter by lazy { NoteAdapter(requireContext(), this) }
    private val repository: NoteRepsitory by lazy { NoteLocalRepo(requireContext()) }
    private val presenter: Note.Presenter by lazy { NotePresenter(this, repository) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false).apply {
            rvHome.adapter = adapter
            btnAdd.setOnClickListener {
                if (tieNote.text.toString().isNotEmpty()) {
                    presenter.insertNote(tieNote.text.toString())
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Catatan tidak boleh kosong",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        presenter.getAllNote()
    }

    override fun onSuccessGetAllNote(note: List<NoteModel>) {
        adapter.list = note.toMutableList()
    }

    override fun onSuccessUpdateNote(noteModel: NoteModel) {
        adapter.updateNote(noteModel)
    }

    override fun onSuccessInsertNote(noteModel: NoteModel) {
        adapter.addNote(noteModel)
        binding.tieNote.setText("")
    }

    override fun onSuccessDeleteNote(id: Long) {
        adapter.deleteNote(id)
        Toast.makeText(requireContext(), "id =$id hasil dihapus", Toast.LENGTH_SHORT).show()
    }

    override fun onClick(noteModel: NoteModel) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(noteModel)
        findNavController().navigate(action)
    }

    override fun onDelete(id: Long) {
        presenter.deleteNote(id)
    }

    override fun onChange(noteModel: NoteModel) {
        noteModel.status = !noteModel.status
        presenter.updateNote(noteModel)
    }


}