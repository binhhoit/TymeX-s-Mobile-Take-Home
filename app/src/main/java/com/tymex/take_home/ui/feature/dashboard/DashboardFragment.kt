package com.tymex.take_home.ui.feature.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.base.fragment.BaseFragment
import com.tymex.takehome.databinding.FragmentDashboardBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : BaseFragment() {

    private var _binding: FragmentDashboardBinding? = null
    val binding get() = _binding!!
    val viewModel: DashboardViewModel by viewModel()

    override val lifecycleObserver = DashboardLifecycleObserver(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun navigateToDashboard() {
        //findNavController().navigate(DashboardFragmentDirections.actionLoginFragmentToDashboardFragment())
    }

}