package com.base.viewmodel

import androidx.lifecycle.LifecycleOwner

interface CommonView {

    fun showLoading(whiteBackground: Boolean = false, onDismiss: (() -> Unit)? = null, cancelable: Boolean = false)

    fun showError(throwable: Throwable, tryAgainAction: (() -> Unit)?)

    fun showError(throwable: Throwable)

    fun hideLoading()

}