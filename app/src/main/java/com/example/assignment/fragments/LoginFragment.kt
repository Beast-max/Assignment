package com.example.assignment.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.assignment.R
import com.example.assignment.api.RetrofitInstance
import com.example.assignment.databinding.FragmentLoginBinding
import com.example.assignment.models.LoginRequest
import com.example.assignment.models.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginFragment : Fragment() {
    private lateinit var binding:FragmentLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginButton.setOnClickListener {
            if(!isValidEmail(binding.emailTxt.text.toString())){
                binding.email.error = "Enter valid email"

            }
            else if(binding.passwordTxt.text?.isEmpty()==true){
                binding.password.error = "Password Can not be null"
            }
            else{
                loginApiCall(binding.emailTxt.text.toString(),binding.passwordTxt.text.toString())
            }
        }
    }

    fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$".toRegex()
        return emailRegex.matches(email)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }
    private fun loginApiCall(email:String,password:String){
        val call: Call<LoginResponse> = RetrofitInstance.apiService.loginUser(LoginRequest(email,password))
        call.enqueue(object :Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if(response.code()==200){
                    val responseBody:LoginResponse? = response.body()
                    responseBody?.let { saveToken(it.token) }
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    Log.d("response",responseBody.toString())
                }
                else{
                    Toast.makeText(requireContext(),"User not found",Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("error_login",t.toString())
                Toast.makeText(requireContext(),"User not found",Toast.LENGTH_LONG).show()

            }

        })
    }
    private fun saveToken(token:String){
        val sharedPref = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        Toast.makeText(requireContext(),"Login Successful",Toast.LENGTH_LONG).show()
        val editor = sharedPref?.edit()
        editor?.putString("TOKEN",token)
        editor?.apply()
    }

}