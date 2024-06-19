package com.example.myfirstonlinegame.base

import com.example.myfirstonlinegame.core.AppChannelImpl
import com.example.myfirstonlinegame.domain.communication.AppChannel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Almost all service functions throw exceptions.
 * Usage of this class forces you to catch exceptions, which would otherwise be propagated up
 * So, you can think of it as a safe access to methods
 * @param Takes - type of parameter
 * @param Returns - type of return
 * @param scope - scope of coroutine. Tasks do not have their own scope, since it might outlive the scope of the task
 * @author FaridG
 * */
abstract class Task <Takes, Returns> {

    abstract suspend fun run(param : Takes) : Returns

    operator fun invoke(
        param : Takes,
        scope : CoroutineScope,
        onFailure : (Exception) -> Unit,
        onSuccess : suspend (Returns) -> Unit
    ) {
        scope.launch {
            AppChannelImpl.setLoading(true)
            try {
                val result = run(param)
                onSuccess(result)
            } catch (e : CancellationException) {
                // cancellation exceptions are treated as a failure.
                // Can be extended if need be in the future
                throw e
            } catch (e:Exception) {
                onFailure(e)
            }
            AppChannelImpl.setLoading(false)
        }
    }
}
