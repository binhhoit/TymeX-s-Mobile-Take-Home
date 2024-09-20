
package com.tymex.take_home.ui.feature.ui.profile_details

import androidx.lifecycle.Observer
import com.data.domain.user.UsersUseCase
import com.data.model.DataState
import com.data.network.model.UserDTO
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.tymex.take_home.ui.feature.profile_details.ProfileDetailsViewModel
import com.tymex.take_home.ui.feature.profile_details.ProfileDetailsViewModelImpl
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

class ProfileDetailsViewModelTest : BaseViewModelTest<ProfileDetailsViewModel>() {

    @Mock
    private lateinit var usersUseCase: UsersUseCase
    private val mDataStateObserver: Observer<DataState<UserDTO>> = mock()

    override fun createViewModel(): ProfileDetailsViewModel {
        return ProfileDetailsViewModelImpl(usersUseCase)
    }

    @Before
    override fun setUp() {
        MockitoAnnotations.openMocks(this)
        super.setUp()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `get detail info user success, test DataState Success`() = testCoroutineRule.runTest {
        // arrange
        val username = ""
        whenever(usersUseCase.getProfileUsers(userName = username))
            .thenReturn(flowOf(UserDTO()))
        mViewModel.infoDetailLiveData.observeForever(mDataStateObserver)

        // action
        mViewModel.getInfoDetailsUser(userName = username)

        withContext(Dispatchers.IO) {
            Thread.sleep(2000)
        }

        // assert
        verify(mDataStateObserver, Times(1)).onChanged(DataState.Loading(true))
        verify(mDataStateObserver, Times(1)).onChanged(any<DataState.Success<UserDTO>>())
        verify(mDataStateObserver, Times(1)).onChanged(DataState.Loading(false))
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `get detail info user fail, test DataState Failure`() = testCoroutineRule.runTest{
        // arrange
        val username = ""
        whenever(usersUseCase.getProfileUsers(userName = username))
            .thenReturn(flow { throw Throwable("API error") })
        mViewModel.infoDetailLiveData.observeForever(mDataStateObserver)

        // action
        mViewModel.getInfoDetailsUser(userName = username)

        withContext(Dispatchers.IO) {
            Thread.sleep(2000)
        }

        // assert
        verify(mDataStateObserver, Times(1)).onChanged(DataState.Loading(true))
        verify(mDataStateObserver, Times(1)).onChanged(any<DataState.Failure<UserDTO>>())
        verify(mDataStateObserver, Times(1)).onChanged(DataState.Loading(false))
    }
}