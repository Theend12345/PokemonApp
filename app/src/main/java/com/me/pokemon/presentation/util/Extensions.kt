package com.me.pokemon.presentation.util

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast

fun ProgressBar.setVisible(visibility: Boolean) {
    if (visibility) this.visibility = View.VISIBLE else this.visibility = View.GONE
}

fun String.makeToast(context: Context){
    Toast.makeText(context, this, Toast.LENGTH_LONG).show()
}