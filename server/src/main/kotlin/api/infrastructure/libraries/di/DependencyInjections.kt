package api.infrastructure.libraries.di

import api.infrastructure.libraries.di.modules.*
import org.koin.core.Koin
import org.koin.core.context.startKoin

class DependencyInjections {
    val instance: Koin = startKoin {
        modules(
            servicesModule,
            middlewaresModule,
            controllerModule,
            repositoriesModules,
            envModule
        )
    }.koin
}