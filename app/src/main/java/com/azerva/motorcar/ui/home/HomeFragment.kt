package com.azerva.motorcar.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.azerva.motorcar.R
import com.azerva.motorcar.core.adapters.MainAdapter
import com.azerva.motorcar.data.DatabaseRoom
import com.azerva.motorcar.databinding.FragmentMainBinding
import com.azerva.motorcar.model.CarVo
import com.azerva.motorcar.ui.label_car.CarLabelFragment
import com.azerva.motorcar.ui.label_car.CarLabelTransaction
import com.azerva.motorcar.ui.launcher.LauncherTransaction

class HomeFragment : Fragment(), MainAdapter.OnEventClickListener {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private var _binding : FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainAdapter : MainAdapter
    private lateinit var db : DatabaseRoom

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = DatabaseRoom.getInstance(this@HomeFragment.requireActivity().applicationContext)

        setup()

    }

    private fun setup() {
        initRecyclerView()
        listeners()
        getAllCars()
    }

    private fun listeners(){
        binding.apply {
            mainBtnExit.setOnClickListener { onDisconnect() }
            mainBtnAddCar.setOnClickListener { navigateToCarLabel() }
        }
    }

    private fun onDisconnect() {
        LauncherTransaction().replace(requireActivity().supportFragmentManager, R.id.main_container)
    }

    private fun navigateToCarLabel() {
        CarLabelTransaction().replace(requireActivity().supportFragmentManager, R.id.main_container)
    }

    private fun getAllCars() {
        Thread {
            val cars = db.carDao().getAllCars()
            activity?.runOnUiThread {
                mainAdapter.carsList = cars.toMutableList()
            }
        }.start()
    }



    private fun initRecyclerView() {
        mainAdapter = MainAdapter(this)
        binding.mainRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = mainAdapter
        }
    }

    override fun onCarClick(car: CarVo) {
        val fragment = CarLabelFragment()

        val bundle = Bundle()
        bundle.putSerializable("car", car)

        fragment.arguments = bundle

        requireActivity().supportFragmentManager.beginTransaction().replace(R.id.main_container, fragment).commit()
    }
}