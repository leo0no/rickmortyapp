package com.br.leoono.rickmorty.util

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.br.leoono.rickmorty.util.listener.RemoveFragmentAnimationListener

object DialogUtil {
    fun displayFragmentModalDialog(
        context: Context?,
        manager: FragmentManager,
        fragment: Fragment,
        fillScreen: Boolean,
        removeFragmentOnDismiss: Boolean
    ): Dialog? {
        val ft = manager.beginTransaction()
        ft.add(fragment, "fragment")
        ft.commitNowAllowingStateLoss()
        val dialog: Dialog? = displayModalDialog(context, fragment.view, fillScreen)
        if (removeFragmentOnDismiss) {
            dialog?.setOnDismissListener { dialogInterface: DialogInterface? ->
                AnimatorUtil.revealShow(
                    fragment.view,
                    false,
                    dialog,
                    RemoveFragmentAnimationListener(manager, fragment)
                )
            }
        }
        return dialog
    }

    fun displayModalDialog(context: Context?, view: View?, fillScreen: Boolean): Dialog? {
        val dialog = Dialog(context!!)
        if (view != null) {
            DialogUtil.setCommonModalProperties(dialog, view, fillScreen)
            dialog.show()
        }
        return dialog
    }

    fun setCommonModalProperties(dialog: Dialog, dialogView: View, fillScreen: Boolean) {

        //dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Content view
        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        dialog.setContentView(dialogView, layoutParams)

        //Properties
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)

        //Listeners
        dialogView.visibility = View.INVISIBLE //Hide before showing to avoid animation bugs
        dialog.setOnShowListener { dialogInterface: DialogInterface? ->
            AnimatorUtil.revealShow(
                dialogView,
                true,
                dialog
            )
        }

        //Set layout to fill screen
        if (fillScreen) {
            dialog.window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        } else {
            dialog.window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }

        //Transparent background to simulate the modal style
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}