package com.azerva.brainpassword.core.ext

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity



/**
 * Oculta el teclado virtual asociado con esta vista.
 *
 * @param completed Lambda opcional que se ejecutará después de intentar ocultar el teclado.
 */
fun View.dismissKeyboard(completed: () -> Unit = {}) {
    // Obtener el servicio del administrador de entrada
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    // Intentar ocultar el teclado virtual
    val wasOpened = inputMethodManager.hideSoftInputFromWindow(windowToken, 0)

    // Ejecutar la lambda completada si el teclado no estaba abierto
    if (!wasOpened) completed()
}

/**
 * Hace que la vista sea visible al establecer su visibilidad en View.VISIBLE.
 */
fun View.makeVisible() {
    visibility = View.VISIBLE
}

/**
 * Hace que la vista sea invisible al establecer su visibilidad en View.INVISIBLE.
 */
fun View.makeInvisible() {
    visibility = View.INVISIBLE
}

/**
 * Hace que la vista sea invisible y ocupa ningún espacio en el diseño al establecer su visibilidad en View.GONE.
 */
fun View.makeGone() {
    visibility = View.GONE
}
