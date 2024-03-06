package com.example.assignment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.assignment.db.DataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        isLogin()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


    private fun isLogin(){
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_nav_host) as NavHostFragment
        val navController: NavController = navHostFragment.navController
        val navGraph = navController.navInflater.inflate(R.navigation.main_nav)
        val sharedPref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val token:String? = sharedPref.getString("TOKEN",null)
        Log.d("token",token.toString())
        if(token!=null){
            navGraph.setStartDestination(R.id.homeFragment)
            navController.graph = navGraph
        }
        else{
            navGraph.setStartDestination(R.id.loginFragment)
            navController.graph = navGraph
        }
    }
}