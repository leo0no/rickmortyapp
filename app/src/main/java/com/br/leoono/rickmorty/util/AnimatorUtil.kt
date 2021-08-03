package com.br.leoono.rickmorty.util

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Dialog
import android.view.View
import android.view.ViewAnimationUtils

object AnimatorUtil {
    const val DEFAULT_ANIMATION_DURATION = 350

    fun revealShow(dialogView: View?, show: Boolean, dialog: Dialog) {
        revealShow(dialogView, show, dialog, null)
    }

    fun revealShow(
        dialogView: View?,
        show: Boolean,
        dialog: Dialog,
        animationListener: Animator.AnimatorListener?
    ) {
        val w = dialogView?.width
        val h = dialogView?.height
        val endRadius =
            Math.hypot(dialogView?.width?.toDouble()!!, dialogView?.height?.toDouble()!!).toInt()
        val y = 0
        if (show) {
            dialogView.post {
                showRevealAnimation(
                    dialogView,
                    w!!,
                    y,
                    endRadius,
                    animationListener
                )
            }
        } else {
            dialogView?.post {
                closeRevealAnimation(
                    dialog,
                    dialogView,
                    endRadius,
                    w!!,
                    y,
                    animationListener
                )
            }
        }
    }

    fun closeRevealAnimation(
        dialog: Dialog,
        view: View?,
        endRadius: Int,
        x: Int,
        y: Int,
        animationListener: Animator.AnimatorListener?
    ) {
        if (view?.isAttachedToWindow!!) {
            val anim = ViewAnimationUtils.createCircularReveal(view, x, y, endRadius.toFloat(), 0f)
            if (animationListener != null) anim.addListener(animationListener)
            anim.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    dialog.dismiss()
                    view.visibility = View.INVISIBLE
                }
            })
            anim.duration = DEFAULT_ANIMATION_DURATION.toLong()
            anim.start()
        }
    }

    fun showRevealAnimation(
        view: View?,
        x: Int,
        y: Int,
        endRadius: Int,
        animationListener: Animator.AnimatorListener?
    ) {
        if (view?.isAttachedToWindow!!) {
            view?.visibility = View.VISIBLE
            val revealAnimator =
                ViewAnimationUtils.createCircularReveal(view, x, y, 0f, endRadius.toFloat())
            revealAnimator.duration = DEFAULT_ANIMATION_DURATION.toLong()
            if (animationListener != null) revealAnimator.addListener(animationListener)
            revealAnimator.start()
        }
    }
}