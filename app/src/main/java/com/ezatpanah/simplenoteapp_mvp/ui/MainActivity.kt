package com.ezatpanah.simplenoteapp_mvp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ezatpanah.simplenoteapp_mvp.R
import com.ezatpanah.simplenoteapp_mvp.databinding.ActivityMainBinding
import com.ezatpanah.simplenoteapp_mvp.ui.add.NoteFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            btnAddNote.setOnClickListener {
                NoteFragment().show(supportFragmentManager,NoteFragment().tag)
            }
        }
    }
}