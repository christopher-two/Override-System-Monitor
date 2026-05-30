package org.override.system.monitor.di

import org.koin.dsl.module

val appModule = module {
    includes(
        dataModule,
        domainModule,
        viewModelModule,
        navigationModule
    )
}