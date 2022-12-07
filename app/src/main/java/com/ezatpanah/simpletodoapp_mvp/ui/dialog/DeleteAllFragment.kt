package com.ezatpanah.simpletodoapp_mvp.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.ezatpanah.simpletodoapp_mvp.databinding.FragmentDeleteAllBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DeleteAllFragment : DialogFragment() , DeleteAllContracts.View {

    private lateinit var binding: FragmentDeleteAllBinding

    @Inject
    lateinit var presenter: DeleteAllPresenter

    companion object {
        const val TAG = "DeleteAllFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDeleteAllBinding.inflate(inflater, container, false)
        dialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnPositive.setOnClickListener {
                presenter.deleteAllTask()
                dismiss()
            }
            btnNegative.setOnClickListener {
                dismiss()
            }
        }

    }

    override fun deleteMessage() {
        Snackbar.make(binding.root, "All notes deleted!", Snackbar.LENGTH_SHORT).show()
    }
}