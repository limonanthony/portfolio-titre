package api.app.abstract

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

abstract class CoroutineContext(
    protected val dispatcher: CoroutineDispatcher = Dispatchers.IO
)