package com.kharozim.day17crudsqlite.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.kharozim.day17crudsqlite.R
import com.kharozim.day17crudsqlite.databinding.FragmentDetailBinding
import com.kharozim.day17crudsqlite.databinding.ItemNoteBinding
import com.kharozim.day17crudsqlite.models.NoteModel
import com.kharozim.day17crudsqlite.presenter.NotePresenter
import com.kharozim.day17crudsqlite.repository.NoteRepsitory
import com.kharozim.day17crudsqlite.repository.local.NoteLocalRepo
import com.kharozim.day17crudsqlite.views.contract.Note

class DetailFragment : Fragment(), Note.View {

    private lateinit var binding: FragmentDetailBinding
    private val repo: NoteRepsitory by lazy { NoteLocalRepo(requireContext()) }
    private val presenter: Note.Presenter by lazy { NotePresenter(this, repo) }
    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false).apply {
            tieNote.setText(args.todo.catatan)

            btnUpdate.setOnClickListener {
                if (tieNote.text.toString().isNotEmpty()) {
                    presenter.updateNote(args.todo.copy(catatan = tieNote.text.toString()))
                } else {
                    Toast.makeText(
                        requireContext(),
                        "catatan tidak boleh kosong",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        return binding.root


    }

    override fun onSuccessGetAllNote(note: List<NoteModel>) {}

    override fun onSuccessUpdateNote(noteModel: NoteModel) {
        Toast.makeText(
            requireContext(),
            "note dengan ID = ${noteModel.id} berhasil di update",
            Toast.LENGTH_SHORT
        )
            .show()
        requireActivity().onBackPressed()
    }

    override fun onSuccessInsertNote(noteModel: NoteModel) {}

    override fun onSuccessDeleteNote(id: Long) {}


}