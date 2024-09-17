package com.data.di.module

import com.data.domain.user.UsersUseCase
import org.koin.dsl.module

val useCaseModule = module  {
    factory { UsersUseCase(get()) }
}