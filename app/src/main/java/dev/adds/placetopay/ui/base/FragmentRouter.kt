package dev.adds.placetopay.ui.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

interface FragmentRouter {

    fun fragment() : Fragment

    fun add(manager: FragmentManager, containerId: Int, tag : String) = manager.beginTransaction().add(containerId, fragment(), tag).commitAllowingStateLoss()

    fun replace(manager: FragmentManager, containerId: Int) = manager.beginTransaction().replace(containerId, fragment()).commit()

    fun show(manager: FragmentManager) = manager.beginTransaction().show(fragment()).commitAllowingStateLoss()

    fun hide(manager: FragmentManager) = manager.beginTransaction().hide(fragment()).commitAllowingStateLoss()

    fun remove(manager: FragmentManager) = manager.beginTransaction().remove(fragment()).commitAllowingStateLoss()
}
