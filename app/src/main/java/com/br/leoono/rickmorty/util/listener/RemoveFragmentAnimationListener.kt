package com.br.leoono.rickmorty.util.listener

import android.animation.Animator
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class RemoveFragmentAnimationListener(
    private val manager: FragmentManager?, private val fragment: Fragment?
) : Animator.AnimatorListener {

    override fun onAnimationStart(animation: Animator?) {}

    override fun onAnimationEnd(animation: Animator?) {
        manager!!.beginTransaction()
            .remove(fragment!!)
            .commitNowAllowingStateLoss()
    }

    override fun onAnimationCancel(animation: Animator?) {}

    override fun onAnimationRepeat(animation: Animator?) {}
}