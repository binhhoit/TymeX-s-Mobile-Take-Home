package com.tymex.take_home.ui.feature.profile_details

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.bumptech.glide.Glide
import com.data.model.DataState
import com.data.network.model.UserDTO
import com.tymex.takehome.R

class ProfileDetailsLifecycleObserver(private val fragment: ProfileDetailsFragment) : DefaultLifecycleObserver {

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        initView()
        setDataProfile(fragment.argument.userInfo)
        observeData()

    }

    private fun initView() {
        fragment.apply {
           binding.apply {
               ivBack.setOnClickListener{ back() }
           }
            viewModel.getInfoDetailsUser(argument.userInfo?.login ?: "")
        }
    }

    private fun setDataProfile(user: UserDTO?) {
        fragment.apply {
            binding.userInfo = user
            Glide
                .with(requireContext())
                .load(user?.avatarUrl)
                .placeholder(R.drawable.ic_logo)
                .centerCrop()
                .into(binding.rivAvatar)
        }
    }

    private fun observeData() {
        fragment.apply {
            viewModel.infoDetailLiveData.observe(viewLifecycleOwner) { state ->
                when (state) {
                    is DataState.Loading -> {
                        if (state.isLoading) showLoading() else hideLoading()
                    }

                    is DataState.Success -> {
                       setDataProfile(state.data)
                    }

                    is DataState.Failure -> {
                       showError(Throwable(getString(R.string.fragment_login_can_t_login)))
                    }
                }
            }
        }
    }


}
