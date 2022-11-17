package com.ezatpanah.simplenoteapp_mvp.ui.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ezatpanah.simplenoteapp_mvp.R
import com.ezatpanah.simplenoteapp_mvp.databinding.FragmentNoteBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class NoteFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentNoteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentNoteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            imgClose.setOnClickListener { this@NoteFragment.dismiss() }
        }
    }

}