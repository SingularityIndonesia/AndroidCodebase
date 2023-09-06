package com.singularity_code.singularitycodebase.ui.activity.providerdemo

import androidx.lifecycle.ViewModel
import com.singularity_code.codebase.util.flow.automate
import com.singularity_code.codebase.util.flow.collect
import com.singularity_code.codebase.util.flow.lazyFunction
import com.singularity_code.codebase.util.flow.provider
import com.singularity_code.singularitycodebase.data.SampleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn

/**
 * Note that im giving you example as simple as possible, so it is less too you to get through the mechanism;
 * Therefore the architecture might not be satisfying.
 * But of course for the real world execution, I recommend you to use prefer architecture such Clean Architecture or such.
 */
class ProviderDemoViewModel(
    private val repository: SampleRepository = SampleRepository()
) : ViewModel() {

    /** Creating a Provider **/
    val sampleProvider by provider(operator = repository::getSample)

    /**
     * this is the pattern that I recommended,
     * I called it "State Automation".
     * This pattern successfully reduce Code Diversity and remove Side Effects completely.
     **/
    val sampleText: Flow<String> by lazy {

        MutableStateFlow("")
            .automate { flow ->
                // updater function
                combine(
                    sampleProvider.loading,
                    sampleProvider.success,
                    sampleProvider.error
                ) { loading, success, error ->
                    when {
                        loading -> "Loading.."
                        success.isSome() -> "Success : ${success.getOrNull()}"
                        error.isSome() -> "Failed : ${error.getOrNull()}"
                        else -> "Idle"
                    }
                }.flowOn(Dispatchers.IO)
                    .collect { text ->
                        flow.emit(text)
                    }

            }

    }
}
