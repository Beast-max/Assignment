package com.example.assignment.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.assignment.MyApp
import com.example.assignment.databinding.FragmentAddEmployeeBinding
import com.example.assignment.db.EntityModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AddEmployeeFragment : Fragment() {
    private lateinit var binding:FragmentAddEmployeeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.add.setOnClickListener{
            if(binding.usernameTxt.text?.isEmpty() == true){
                binding.username.error = "Username cannot be empty"
            }
           else if(binding.ageTxt.text?.isEmpty() == true){
                binding.age.error = "age cannot be empty"
            }
           else if(binding.addressTxt.text?.isEmpty() == true){
                binding.address.error = "address cannot be empty"
            }
            else if(binding.cityTxt.text?.isEmpty() == true){
                binding.city.error = "city cannot be empty"
            }
            else{
                CoroutineScope(Dispatchers.IO).launch {
                    MyApp.database.Dao().insert(EntityModel(username = binding.usernameTxt.text.toString(), age = binding.ageTxt.text.toString().toInt(), address = binding.addressTxt.text.toString(), city = binding.cityTxt.text.toString()))

                }
                findNavController().popBackStack()

            }
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddEmployeeBinding.inflate(layoutInflater)
        return binding.root
    }
}