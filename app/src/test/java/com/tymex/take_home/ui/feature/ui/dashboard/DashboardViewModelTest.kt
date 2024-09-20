
package com.tymex.take_home.ui.feature.ui.dashboard

import androidx.lifecycle.Observer
import com.data.domain.user.UsersUseCase
import com.data.model.DataState
import com.data.network.model.UserDTO
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.tymex.take_home.ui.feature.dashboard.DashboardViewModel
import com.tymex.take_home.ui.feature.dashboard.DashboardViewModelImpl
import com.tymex.take_home.viewmodel.BaseViewModelTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.internal.verification.Times

class DashboardViewModelTest : BaseViewModelTest<DashboardViewModel>() {

    @Mock
    private lateinit var usersUseCase: UsersUseCase
    private val mDataStateObserver: Observer<DataState<List<UserDTO>>> = mock()
    private val perPage = 20
    private val since = 1

    override fun createViewModel(): DashboardViewModel {
        return DashboardViewModelImpl(usersUseCase)
    }

    @Before
    override fun setUp() {
        MockitoAnnotations.openMocks(this)
        super.setUp()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `get list profile user success, test DataState Success`() = testCoroutineRule.runTest {
        // arrange
        val users = listOf(UserDTO())
        whenever(usersUseCase.getListProfileUser(perPage = perPage.toString(),since = since.toString()))
            .thenReturn(flowOf(users))
        mViewModel.usersLiveData.observeForever(mDataStateObserver)

        // action
        mViewModel.getProfileUsers(perPage = perPage.toString(),since = since.toString())

        withContext(Dispatchers.IO) {
            Thread.sleep(2000)
        }

        // assert
        verify(mDataStateObserver, Times(1)).onChanged(DataState.Loading(true))
        verify(mDataStateObserver, Times(1)).onChanged(any<DataState.Success<List<UserDTO>>>())
        verify(mDataStateObserver, Times(1)).onChanged(DataState.Loading(false))
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `get list profile user fail, test DataState Failure`() = testCoroutineRule.runTest{
        // arrange
        whenever(usersUseCase.getListProfileUser(perPage = perPage.toString(),since = since.toString()))
            .thenReturn(flow { throw Throwable("API error") })
        mViewModel.usersLiveData.observeForever(mDataStateObserver)

        // action
        mViewModel.getProfileUsers(perPage = perPage.toString(),since = since.toString())

        withContext(Dispatchers.IO) {
            Thread.sleep(2000)
        }

        // assert
        verify(mDataStateObserver, Times(1)).onChanged(DataState.Loading(true))
        verify(mDataStateObserver, Times(1)).onChanged(any<DataState.Failure<List<UserDTO>>>())
        verify(mDataStateObserver, Times(1)).onChanged(DataState.Loading(false))
    }
}