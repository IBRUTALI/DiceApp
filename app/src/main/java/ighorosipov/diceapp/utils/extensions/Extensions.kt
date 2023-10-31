package ighorosipov.diceapp.utils.extensions

import android.app.AlertDialog
import android.widget.Toast
import androidx.fragment.app.Fragment
import ighorosipov.diceapp.R

fun Fragment.showToast(
    message: String,
    longLength: Boolean = false
) {
    Toast.makeText(
        requireContext(),
        message,
        if (longLength) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
    ).show()
}

fun Fragment.showAlert(
    title: String,
    message: String,
    positiveButtonText: String,
    onPositiveButtonClick: () -> Unit = { },
    onNegativeButtonClick: () -> Unit = { }
) {
    AlertDialog.Builder(requireContext())
        .setTitle(title)
        .setMessage(message)
        .setCancelable(false)
        .setPositiveButton(positiveButtonText) { _, _ ->
            onPositiveButtonClick.invoke()
        }
        .setNegativeButton(getString(R.string.exit)) { _, _ ->
            onNegativeButtonClick.invoke()
        }
        .show()
}