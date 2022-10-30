package com.mucheng.webops.plugin.data

import kotlinx.coroutines.flow.MutableStateFlow

class ObservableValue<T>(private val progressStateFlow: MutableStateFlow<T>) {

    fun setValue(value: T) {
        this.progressStateFlow.value = value
    }

    fun getValue(): T {
        return progressStateFlow.value
    }

}