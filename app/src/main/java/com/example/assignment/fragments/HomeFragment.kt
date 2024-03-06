package com.example.assignment.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.example.assignment.MyApp
import com.example.assignment.R
import com.example.assignment.databinding.FragmentHomeBinding
import com.example.assignment.db.DataBase
import com.example.assignment.fragments.adapter.MyAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    private var doubleBackPressedOnce = false
    private lateinit var adapter: MyAdapter
    private lateinit var binding:FragmentHomeBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.add.setOnClickListener {
            findNavController().navigate(R.id.addEmployeeFragment)
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            if (doubleBackPressedOnce) {
                requireActivity().finish()
            } else {
                Toast.makeText(requireContext(),"Backpress again to exit", Toast.LENGTH_LONG).show()

                doubleBackPressedOnce = true
                view.postDelayed({ doubleBackPressedOnce = false }, 2000)
            }
        }

        adapter = MyAdapter()
        binding.rv.adapter = adapter
        fetchData()
    }


    private fun fetchData(){
        CoroutineScope(Dispatchers.IO).launch {
            MyApp.database.Dao().getAll().let {
                adapter.submitList(it)
            }
            Log.d("user_list", MyApp.database.Dao().getAll().toString())
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }


}