package com.azerva.brainpassword.core.ext

import android.app.Activity
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

/**
 * Extension function to display a Toast message in an Activity.
 *
 * @param text The text message to be displayed in the Toast.
 * @param length The duration for which the Toast should be shown, default is Toast.LENGTH_LONG.
 */
fun Activity.toast(text: String, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, text, length).show()
}

/**
 * Extension function to display a Toast message in a Fragment.
 *
 * @param text The text message to be displayed in the Toast.
 * @param length The duration for which the Toast should be shown, default is Toast.LENGTH_LONG.
 */
fun Fragment.toast(text: String, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(requireActivity(), text, length).show()
}






