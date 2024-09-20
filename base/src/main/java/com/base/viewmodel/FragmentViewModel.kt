package com.base.viewmodel

import androidx.lifecycle.ViewModel

open class FragmentViewModel : ViewModel() {

    var initialized = false

    open fun onInitialized() {}

}
