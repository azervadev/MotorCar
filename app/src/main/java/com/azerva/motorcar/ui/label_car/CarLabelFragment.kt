package com.azerva.motorcar.ui.label_car

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.azerva.brainpassword.core.ext.makeGone
import com.azerva.brainpassword.core.ext.makeVisible
import com.azerva.brainpassword.core.ext.toast
import com.azerva.motorcar.R
import com.azerva.motorcar.data.DatabaseRoom
import com.azerva.motorcar.databinding.FragmentCarLabelBinding
import com.azerva.motorcar.model.CarVo
import com.azerva.motorcar.ui.home.HomeTransaction


class CarLabelFragment : Fragment() {


    companion object{
        fun newInstance() = CarLabelFragment()
    }

    private var _binding : FragmentCarLabelBinding? = null
    private val binding get() = _binding!!

    private lateinit var db: DatabaseRoom
    private var carObject : CarVo? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCarLabelBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = DatabaseRoom.getInstance(this@CarLabelFragment.requireActivity().applicationContext)

        carObject = arguments?.getSerializable("car", CarVo::class.java)

        if (carObject != null){
                binding.mainBtnSaveCar.makeGone()
                binding.mainBtnUpdateCar.makeVisible()
                binding.mainBtnDeleteCar.makeVisible()
                getItemsTextObject()

        }else {
            binding.mainBtnUpdateCar.makeGone()
            binding.mainBtnDeleteCar.makeGone()
        }
        setup()
    }

    private fun getItemsTextObject() {
        binding.apply {
            etBrand.setText( carObject?.mark)
            etModel.setText( carObject?.model )
            etKilometers.setText( carObject?.kilometers )
            etDate.setText( carObject?.date )
            etCc.setText( carObject?.cc )
            etCv.setText( carObject?.cv )
            etPrice.setText( carObject?.price )
            if (carObject?.isSold == 1){
                labelIsSold.isChecked = true
            }
        }
    }


    private fun setup() {
        listeners()
    }

    private fun listeners() {
        binding.apply {
            mainBtnBack.setOnClickListener{ HomeTransaction().replace(requireActivity().supportFragmentManager, R.id.main_container) }
            mainBtnSaveCar.setOnClickListener {
                onSaveCar(
                    CarVo(
                        mark = etBrand.text.toString(),
                        model = etModel.text.toString(),
                        kilometers = etKilometers.text.toString(),
                        date = etDate.text.toString(),
                        cv = etCv.text.toString(),
                        cc = etCc.text.toString(),
                        price = etPrice.text.toString(),
                        isSold = if (labelIsSold.isChecked) 1 else 0,
                    )
                )
            }
            mainBtnUpdateCar.setOnClickListener { onUpdateCar(CarVo(
                id = carObject!!.id,
                mark = etBrand.text.toString(),
                model = etModel.text.toString(),
                kilometers = etKilometers.text.toString(),
                date = etDate.text.toString(),
                cv = etCv.text.toString(),
                cc = etCc.text.toString(),
                price = etPrice.text.toString(),
                isSold = if (labelIsSold.isChecked) 1 else 0,
            )) }
            mainBtnDeleteCar.setOnClickListener { onDeleteCar() }

        }
    }

    private fun onDeleteCar() {
        Thread{
            carObject?.let { db.carDao().deleteCarById(it.id) }
            requireActivity().runOnUiThread {
                HomeTransaction().replace(requireActivity().supportFragmentManager, R.id.main_container)
                toast("Car delete")
            }
        }.start()

    }

    private fun onUpdateCar(carVo: CarVo) {
        if (carVo.isCarValid()) {
            // Utilizar un hilo secundario para realizar la operación de inserción
            Thread {
                db.carDao().updateCar(carVo)
                // Después de la inserción, puedes realizar cualquier operación en el hilo principal
                requireActivity().runOnUiThread {
                    HomeTransaction().replace(requireActivity().supportFragmentManager, R.id.main_container)
                    toast("Car update")
                }
            }.start()
        } else {
            toast("Error: Check fields.")
        }
    }

    private fun onSaveCar(carVo: CarVo) {
        if (carVo.isCarValid()) {
            // Utilizar un hilo secundario para realizar la operación de inserción
            Thread {
                db.carDao().insertCar(carVo)
                // Después de la inserción, puedes realizar cualquier operación en el hilo principal
                requireActivity().runOnUiThread {
                    HomeTransaction().replace(requireActivity().supportFragmentManager, R.id.main_container)
                    toast("Save data car")
                }
            }.start()
        } else {
            toast("Error: Check fields.")
        }
    }



}