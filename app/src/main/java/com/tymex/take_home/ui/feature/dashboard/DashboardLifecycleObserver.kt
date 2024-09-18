package com.tymex.take_home.ui.feature.dashboard

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.data.model.DataState
import com.tymex.takehome.R
import okhttp3.internal.toImmutableList


class DashboardLifecycleObserver(private val fragment: DashboardFragment) :
    DefaultLifecycleObserver {

    private val adapter by lazy { ProfileUsersAdapter() }
    private var perPage = 20
    private var since = 1
    private var isLoading = false

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        fragment.viewModel.getProfileUsers(perPage.toString(), since.toString())
        initAdapter()
        initScrollListener()
        observeData()
    }

    private fun initAdapter() {
        fragment.binding.apply {
            rcvUserProfile.adapter = adapter
            adapter.callback = { user -> fragment.navigateToProfileDetail(user) }
        }
    }

    private fun initScrollListener() {
        fragment.apply {
            binding.apply {
                rcvUserProfile.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)

                        val layoutManager = recyclerView.layoutManager as LinearLayoutManager?
                        if (!isLoading) {
                            if (layoutManager != null &&
                                layoutManager.findLastCompletelyVisibleItemPosition() == adapter.itemCount - 1
                            ) {
                                loadMore()
                            }
                        }
                    }
                })
            }
        }

    }

    private fun loadMore() {
        isLoading = true
        fragment.apply {
            binding.apply {
                adapter.submitList(adapter.currentList + null)

                Handler(Looper.getMainLooper()).postDelayed({
                    fragment.viewModel.getProfileUsers(perPage.toString(), since.toString())
                }, 2000)

            }
        }
    }


    private fun observeData() {
        fragment.apply {
            viewModel.usersLiveData.observe(viewLifecycleOwner) { state ->
                when (state) {
                    is DataState.Loading -> {
                        if (!isLoading)
                            if (state.isLoading) showLoading() else hideLoading()
                    }

                    is DataState.Success -> {
                        adapter.submitList(state.data.toImmutableList())
                        isLoading = false
                        since = since.plus(perPage)
                    }

                    is DataState.Failure -> {
                        showError(Throwable(getString(R.string.error_something_wrong)))
                        isLoading = false
                        adapter.notifyItemRemoved(adapter.itemCount)
                    }
                }
            }
        }
    }


}
