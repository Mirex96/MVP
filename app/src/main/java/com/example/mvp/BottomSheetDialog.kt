package com.example.mvp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResult
import com.example.mvp.BottomSheetDialogAction.*
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


const val REQUEST_KEY_BS = "REQUEST_KEQ_BS"
const val BUNDLE_KEY_TYPE = "BottomSheetDialogAction"

enum class BottomSheetDialogAction {
    DELETE,
    EDIT,
    CLONE,
    SELECT,
    EXIT
}

class BottomSheetDialog : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_dialog_layout, container, false)
    }

    private fun onClickResult(action: BottomSheetDialogAction) {
        setFragmentResult(REQUEST_KEY_BS, bundleOf(BUNDLE_KEY_TYPE to action))
        dismiss()
    }


    override fun getTheme() = R.style.AppBottomSheetDialogTheme


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.deleteBs).setOnClickListener {
            onClickResult(DELETE)
            dismiss()
        }
        view.findViewById<Button>(R.id.editBs).setOnClickListener {
            onClickResult(EDIT)
            dismiss()
        }
        view.findViewById<Button>(R.id.cloneBs).setOnClickListener {
            onClickResult(CLONE)
            dismiss()
        }
        view.findViewById<Button>(R.id.selectBs).setOnClickListener {
            onClickResult(SELECT)
            dismiss()
        }
        view.findViewById<ImageView>(R.id.exitBS).setOnClickListener {
            onClickResult(EXIT)
            dismiss()
        }
    }

    companion object {
        private const val TAG = "DIALOG"
        fun show(fragmentManager: FragmentManager) {
            if (fragmentManager.findFragmentByTag(TAG) == null)
                BottomSheetDialog()
                    .show(fragmentManager, TAG)
        }
    }
}