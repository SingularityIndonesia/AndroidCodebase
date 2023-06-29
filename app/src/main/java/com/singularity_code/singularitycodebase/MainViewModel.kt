package com.singularity_code.singularitycodebase

import androidx.lifecycle.ViewModel
import com.singularity_code.codebase.util.collect
import com.singularity_code.codebase.util.lazyFunction
import com.singularity_code.codebase.util.provider
import com.singularity_code.singularitycodebase.data.SampleFactory
import com.singularity_code.singularitycodebase.domain.repo.SampleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first

class MainViewModel(
    private val repository: SampleRepository = SampleFactory()
) : ViewModel() {

    /** Creating a Provider **/
    val sampleProvider by provider(repository::getSample)

    /**
     * this is the pattern that I recommended,
     * I called it "State Automation".
     * This pattern successfully reduce Code Diversity and remove Side Effects completely.
     **/
    val sampleText: Flow<String> by lazy {

        // private internal flow
        val flow = MutableStateFlow("Idle")

        // updater function
        val updater = lazyFunction {
            val isLoading = sampleProvider.loading.first()
            if (isLoading) {
                flow.emit("Loading..")
                return@lazyFunction
            }

            val success = sampleProvider.success.first()
            if (success.first) {
                flow.emit("Success : ${success.second}")
                return@lazyFunction
            }

            val failed = sampleProvider.failed.first()
            if (failed.first) {
                flow.emit("Failed : ${failed.second}")
                return@lazyFunction
            }
        }

        // state relations
        run {
            collect(
                sampleProvider.state
            ) {
                updater.tryInvoke( signature = "sampleProvider.state ${System.currentTimeMillis()}")
            }
        }

        flow
    }
}
