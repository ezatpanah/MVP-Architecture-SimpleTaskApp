package com.ezatpanah.simpletodoapp_mvp.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.ezatpanah.simpletodoapp_mvp.R
import com.ezatpanah.simpletodoapp_mvp.adapter.TaskAdapter
import com.ezatpanah.simpletodoapp_mvp.databinding.ActivityMainBinding
import com.ezatpanah.simpletodoapp_mvp.db.TaskEntity
import com.ezatpanah.simpletodoapp_mvp.repository.DbRepository
import com.ezatpanah.simpletodoapp_mvp.ui.add.AddTaskFragment
import com.ezatpanah.simpletodoapp_mvp.ui.dialog.DeleteAllFragment
import com.ezatpanah.simpletodoapp_mvp.utils.Constants.ALL
import com.ezatpanah.simpletodoapp_mvp.utils.Constants.BUNDLE_ID
import com.ezatpanah.simpletodoapp_mvp.utils.Constants.DELETE
import com.ezatpanah.simpletodoapp_mvp.utils.Constants.EDIT
import com.ezatpanah.simpletodoapp_mvp.utils.Constants.HIGH
import com.ezatpanah.simpletodoapp_mvp.utils.Constants.LOW
import com.ezatpanah.simpletodoapp_mvp.utils.Constants.NORMAL
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainContracts.View {

    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var repository: DbRepository

    @Inject
    lateinit var taskAdapter: TaskAdapter

    @Inject
    lateinit var presenter: MainPresenter

    private var selectedItem = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {

            setSupportActionBar(tasksToolbar)

            btnAddTask.setOnClickListener {
                AddTaskFragment().show(supportFragmentManager, AddTaskFragment().tag)
            }

            presenter.loadAllTasks()

            taskAdapter.setOnItemClickListener { TaskEntity, state ->
                when (state) {
                    EDIT -> {
                        val addTaskFragment = AddTaskFragment()
                        val bundle = Bundle()
                        bundle.putInt(BUNDLE_ID, TaskEntity.id)
                        addTaskFragment.arguments = bundle
                        addTaskFragment.show(supportFragmentManager, AddTaskFragment().tag)
                    }
                    DELETE -> {
                        val entity = TaskEntity(TaskEntity.id, TaskEntity.title, TaskEntity.desc, TaskEntity.cat, TaskEntity.pr)
                        presenter.deleteTask(entity)
                    }
                }
            }

            tasksToolbar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.actionFilter -> {
                        filterByPriority()
                        return@setOnMenuItemClickListener true
                    }
                    R.id.actionSearch -> {
                        return@setOnMenuItemClickListener true
                    }
                    R.id.actionDeleteAll -> {
                        DeleteAllFragment().show(supportFragmentManager, DeleteAllFragment.TAG)
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
                presenter.searchTask(newText!!)
                return true
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun showAllTasks(Tasks: List<TaskEntity>) {
        binding.apply {
            emptyBody.visibility = View.GONE
            listBody.visibility = View.VISIBLE
            taskAdapter.setData(Tasks)
            taskList.apply {
                layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                adapter = taskAdapter
            }
        }
    }

    override fun showEmpty() {
        binding.apply {
            emptyBody.visibility = View.VISIBLE
            listBody.visibility = View.GONE
        }
    }

    override fun deleteMessage() {
        Snackbar.make(binding.root, "Task deleted!", Snackbar.LENGTH_SHORT).show()
    }

    private fun filterByPriority() {
        val builder = AlertDialog.Builder(this)
        val priories = arrayOf(ALL, HIGH, NORMAL, LOW)
        builder.setSingleChoiceItems(priories, selectedItem) { dialog, item ->
            when (item) {
                0 -> {
                    presenter.loadAllTasks()
                }
                in 1..3 -> {
                    presenter.filterTask(priories[item])
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
