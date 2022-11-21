package com.ezatpanah.simplenoteapp_mvp.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.ezatpanah.simplenoteapp_mvp.databinding.FragmentNoteBinding
import com.ezatpanah.simplenoteapp_mvp.db.NoteEntity
import com.ezatpanah.simplenoteapp_mvp.repository.DbRepository
import com.ezatpanah.simplenoteapp_mvp.utils.BUNDLE_ID
import com.ezatpanah.simplenoteapp_mvp.utils.EDIT
import com.ezatpanah.simplenoteapp_mvp.utils.NEW
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NoteFragment : BottomSheetDialogFragment(), NoteContracts.View {

    private lateinit var binding: FragmentNoteBinding

    private lateinit var catList: Array<String>
    private var cat = ""
    private lateinit var priorityList: Array<String>
    private var priority = ""
    private var noteId = 0
    private var type = ""

    @Inject
    lateinit var entity: NoteEntity

    @Inject
    lateinit var repository: DbRepository

    @Inject
    lateinit var presenter: NotePresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentNoteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteId = arguments?.getInt(BUNDLE_ID) ?: 0

        type = if (noteId > 0) {
            EDIT
        } else {
            NEW
        }

        binding.apply {
            imgClose.setOnClickListener { this@NoteFragment.dismiss() }
            catSpinnerItem()
            prioritySpinnerItem()

            if (type == EDIT) {
                presenter.detailsNote(noteId)
            }

            saveNote.setOnClickListener {
                val title = titleEdt.text.toString()
                val desc = descEdt.text.toString()
                //entity
                entity.id = noteId
                entity.title = title
                entity.desc = desc
                entity.cat = cat
                entity.pr = priority
                //save
                when (type) {
                    EDIT -> presenter.updateNote(entity)
                    NEW -> presenter.saveNote(entity)
                }
                presenter.saveNote(entity)
            }


        }
    }

    private fun catSpinnerItem() {
        catList = arrayOf("Work", "Home", "Education", "Health")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, catList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.categoriesSpinner.adapter = adapter
        binding.categoriesSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                cat = catList[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }

    private fun prioritySpinnerItem() {
        priorityList = arrayOf("High", "Normal", "Low")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, priorityList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.prioritySpinner.adapter = adapter
        binding.prioritySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                priority = priorityList[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }

    override fun close() {
        this@NoteFragment.dismiss()
    }

    override fun loadNoteData(entity: NoteEntity) {
        if (this.isAdded) {
            requireActivity().runOnUiThread {
                binding.apply {
                    titleEdt.setText(entity.title)
                    descEdt.setText(entity.desc)
                    titleEdt.setText(entity.title)
                    categoriesSpinner.setSelection(getIndex(catList,entity.cat))
                    prioritySpinner.setSelection(getIndex(priorityList,entity.pr))
                }
            }
        }
    }

    private fun getIndex(list : Array<String>, item : String) : Int{
        var index=0
        for (i in list.indices){
            if(list[i] == item){
                index= i
                break
            }
        }
        return index
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }
}