package api.infrastructure.libraries.di.modules

import api.app.middlewares.TokenMiddleware
import org.koin.dsl.module

val middlewaresModule = module {
    factory { TokenMiddleware(get()) }
}