package com.azerva.motorcar.ui.label_car

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.azerva.motorcar.core.ext.TransactionFragment

class CarLabelTransaction : TransactionFragment {

    private var instance : CarLabelFragment? = null
    override fun fragment(): Fragment {
        if (instance == null){
            instance = CarLabelFragment.newInstance()
        }

        return instance!!
    }

    override fun add(manager: FragmentManager, containerId: Int, tag: String): Int {
        return super.replace(manager, containerId)
    }

    override fun replace(manager: FragmentManager, containerId: Int): Int = manager.beginTransaction().replace(containerId, fragment()).commit()

    override fun show(manager: FragmentManager): Int {
        return super.show(manager)
    }

    override fun hide(manager: FragmentManager): Int {
        return super.hide(manager)
    }

    override fun remove(manager: FragmentManager): Int {
        return super.remove(manager)
    }
}