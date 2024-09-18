package com.tymex.take_home.di.module

import com.tymex.take_home.ui.feature.dashboard.DashboardViewModel
import com.tymex.take_home.ui.feature.dashboard.DashboardViewModelImpl
import com.tymex.take_home.ui.feature.main.MainViewModel
import com.tymex.take_home.ui.feature.main.MainViewModelImpl
import com.tymex.take_home.ui.feature.profile_details.ProfileDetailsViewModel
import com.tymex.take_home.ui.feature.profile_details.ProfileDetailsViewModelImpl
import com.tymex.take_home.ui.feature.welcome.WelcomeViewModel
import com.tymex.take_home.ui.feature.welcome.WelcomeViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<MainViewModel> { MainViewModelImpl() }
    viewModel<WelcomeViewModel> { WelcomeViewModelImpl(get()) }
    viewModel<DashboardViewModel> { DashboardViewModelImpl(get()) }
    viewModel<ProfileDetailsViewModel> { ProfileDetailsViewModelImpl(get()) }
}