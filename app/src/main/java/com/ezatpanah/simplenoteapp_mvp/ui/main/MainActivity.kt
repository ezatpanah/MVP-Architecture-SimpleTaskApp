package com.ezatpanah.simplenoteapp_mvp.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.ezatpanah.simplenoteapp_mvp.R
import com.ezatpanah.simplenoteapp_mvp.adapter.NoteAdapter
import com.ezatpanah.simplenoteapp_mvp.databinding.ActivityMainBinding
import com.ezatpanah.simplenoteapp_mvp.db.NoteEntity
import com.ezatpanah.simplenoteapp_mvp.repository.DbRepository
import com.ezatpanah.simplenoteapp_mvp.ui.add.NoteFragment
import com.ezatpanah.simplenoteapp_mvp.utils.*
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainContracts.View {

    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var repository: DbRepository

    @Inject
    lateinit var noteAdapter: NoteAdapter

    @Inject
    lateinit var presenter: MainPresenter

    private var selectedItem = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {

            setSupportActionBar(notesToolbar)

            btnAddNote.setOnClickListener {
                NoteFragment().show(supportFragmentManager, NoteFragment().tag)
            }

            presenter.loadAllNotes()

            noteAdapter.setOnItemClickListener { noteEntity, state ->
                when (state) {
                    EDIT -> {
                        val noteFragment = NoteFragment()
                        val bundle = Bundle()
                        bundle.putInt(BUNDLE_ID, noteEntity.id)
                        noteFragment.arguments = bundle
                        noteFragment.show(supportFragmentManager, NoteFragment().tag)
                    }
                    DELETE -> {
                        val entity = NoteEntity(noteEntity.id, noteEntity.title, noteEntity.desc, noteEntity.cat, noteEntity.pr)
                        presenter.deleteNote(entity)
                    }
                }
            }

            notesToolbar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.actionFilter -> {
                        filterByPriority()
                        return@setOnMenuItemClickListener true
                    }
                    R.id.actionSearch -> {
                        return@setOnMenuItemClickListener true
                    }
                    else -> {
                        return@setOnMenuItemClickListener false
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        val search = menu.findItem(R.id.actionSearch)
        val searchView = search.actionView as SearchView
        searchView.queryHint = "Search..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                presenter.searchNote(newText!!)
                return true
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun showAllNotes(notes: List<NoteEntity>) {
        binding.apply {
            emptyLay.visibility = View.GONE
            noteList.visibility = View.VISIBLE
            noteAdapter.setData(notes)
            noteList.apply {
                layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                adapter = noteAdapter
            }
        }
    }

    override fun showEmpty() {
        binding.apply {
            emptyLay.visibility = View.VISIBLE
            noteList.visibility = View.GONE
        }
    }

    override fun deleteMessage() {
        Snackbar.make(binding.root, "Note deleted!", Snackbar.LENGTH_SHORT).show()
    }

    private fun filterByPriority() {
        val builder = AlertDialog.Builder(this)
        val priories = arrayOf(ALL, HIGH, NORMAL, LOW)
        builder.setSingleChoiceItems(priories, selectedItem) { dialog, item ->
            when (item) {
                0 -> {
                    presenter.loadAllNotes()
                }
                in 1..3 -> {
                    presenter.filterNote(priories[item])
                }
            }
            selectedItem = item
            dialog.dismiss()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }
}
