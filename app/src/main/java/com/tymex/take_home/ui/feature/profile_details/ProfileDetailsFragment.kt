package com.tymex.take_home.ui.feature.profile_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.base.fragment.BaseFragment
import com.tymex.takehome.databinding.FragmentDashboardBinding
import com.tymex.takehome.databinding.FragmentProfileDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileDetailsFragment : BaseFragment() {

    private var _binding: FragmentProfileDetailsBinding? = null
    val binding get() = _binding!!
    val viewModel: ProfileDetailsViewModel by viewModel()
    val argument: ProfileDetailsFragmentArgs by navArgs()

    override val lifecycleObserver = ProfileDetailsLifecycleObserver(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun back() {
        findNavController().popBackStack()
    }

}