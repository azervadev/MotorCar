package com.azerva.motorcar.ui.launcher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.azerva.motorcar.R
import com.azerva.motorcar.databinding.FragmentLauncherBinding
import com.azerva.motorcar.ui.home.HomeTransaction

class LauncherFragment : Fragment() {

    companion object {
        fun newInstance() = LauncherFragment()
    }

    private var _binding : FragmentLauncherBinding? = null
    private val binding get() = _binding!!

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLauncherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setup()


    }

    private fun setup() {
        binding.launcherBtnAccess.setOnClickListener {
            HomeTransaction().replace(requireActivity().supportFragmentManager, R.id.main_container)
        }
    }
}