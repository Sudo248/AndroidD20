package com.sudo.androidd20

import android.os.Bundle
import androidx.fragment.app.*
import androidx.lifecycle.LifecycleOwner

class Navigator private constructor(
    private val fragmentManager: FragmentManager
) {

    companion object {
        fun of(fragmentManager: FragmentManager): Navigator {
            return Navigator(fragmentManager)
        }
    }

    private var idHolder: Int? = null

    fun withPlaceHolder(idHolder: Int): Navigator {
        this.idHolder = idHolder
        return this
    }

    fun addBundle(key: String, bundle: Bundle): Navigator {
        fragmentManager.setFragmentResult(key, bundle)
        return this
    }

    fun getBundle(
        key: String,
        lifecycleOwner: LifecycleOwner,
        listener: FragmentResultListener
    ): Navigator {
        fragmentManager.setFragmentResultListener(key, lifecycleOwner, listener)
        return this
    }

    fun navigate(fClass: Class<out Fragment>, block: ((FragmentTransaction) -> Unit)? = null): Int {
        return idHolder?.let { holder ->
            fragmentManager
                .beginTransaction()
                .replace(holder, fClass.newInstance(), fClass.simpleName)
                .apply { block?.invoke(this) }
                .addToBackStack(null)
                .commit()
        } ?: -1
    }

    fun navigateOff(fClass: Class<out Fragment>, block: ((FragmentTransaction) -> Unit)? = null): Int {
        return idHolder?.let { holder ->
            fragmentManager
                .beginTransaction()
                .replace(holder, fClass.newInstance(), fClass.simpleName)
                .apply { block?.invoke(this) }
                .commit()
        } ?: -1
    }

    fun navigateOffAll(fClass: Class<out Fragment>, block: ((FragmentTransaction) -> Unit)? = null): Int {
        return idHolder?.let { holder ->
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            fragmentManager
                .beginTransaction()
                .replace(holder, fClass.newInstance(), fClass.simpleName)
                .apply { block?.invoke(this) }
                .addToBackStack(null)
                .commit()
        } ?: -1
    }

    fun add(fClass: Class<out Fragment>, block: ((FragmentTransaction) -> Unit)? = null): Int {
        return idHolder?.let { holder ->
            fragmentManager
                .beginTransaction()
                .add(holder, fClass.newInstance(), fClass.simpleName)
                .apply { block?.invoke(this) }
                .commit()
        } ?: -1
    }

    fun back() {
        fragmentManager.popBackStack()
    }

    fun commit(block: FragmentTransaction.() -> Unit): Int {
        return fragmentManager
            .beginTransaction()
            .apply { block() }
            .commit()
    }

}