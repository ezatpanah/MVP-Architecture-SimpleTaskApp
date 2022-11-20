package com.ezatpanah.simplenoteapp_mvp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ezatpanah.simplenoteapp_mvp.adapter.NoteAdapter
import com.ezatpanah.simplenoteapp_mvp.databinding.ActivityMainBinding
import com.ezatpanah.simplenoteapp_mvp.db.NoteEntity
import com.ezatpanah.simplenoteapp_mvp.repository.DbRepository
import com.ezatpanah.simplenoteapp_mvp.ui.add.NoteFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() ,MainContracts.View{

    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var repository: DbRepository

    @Inject
    lateinit var noteAdapter: NoteAdapter

    private val presenter by lazy { MainPresenter(repository,this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            btnAddNote.setOnClickListener {
                NoteFragment().show(supportFragmentManager,NoteFragment().tag)
            }
            presenter.loadAllNotes()
        }
    }

    override fun showAllNotes(notes: List<NoteEntity>) {
        binding.apply {
            emptyLay.visibility=View.GONE
            noteList.visibility=View.VISIBLE
            noteAdapter.setData(notes)
            noteList.apply {
                layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
                adapter=noteAdapter
            }
        }
    }

    override fun showEmpty() {
        binding.apply {
            emptyLay.visibility=View.VISIBLE
            noteList.visibility=View.GONE
        }
    }

    override fun deleteMessage() {
        TODO("Not yet implemented")
    }
}