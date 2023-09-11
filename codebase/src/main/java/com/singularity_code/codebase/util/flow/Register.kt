package com.singularity_code.codebase.util.flow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.singularity_code.codebase.pattern.Register
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

context (CoroutineScope)
fun <T> createRegister(default: T) = object : Register<T> {
    private val _data = MutableStateFlow<T>(default)
    override val data: Flow<T>
        get() = _data

    override fun set(
        data: T
    ) {
        launch {
            _data.emit(data)
        }
    }
}

fun <T> ViewModel.register(
    default: T
): Lazy<Register<T>> = lazy {
    object : Register<T> {

        private val _data = MutableSharedFlow<T>(1)

        override val data: Flow<T>
            get() = _data

        override fun set(data: T) {
            viewModelScope.launch {
                _data.emit(data)
            }
        }

        init {
            set(default)
        }
    }
}