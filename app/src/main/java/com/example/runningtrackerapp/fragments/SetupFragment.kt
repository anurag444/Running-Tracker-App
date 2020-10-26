package com.example.runningtrackerapp.fragments

import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.runningtrackerapp.R
import com.example.runningtrackerapp.other.CONSTANTS.KEY_FIRST_TIME_TOGGLE
import com.example.runningtrackerapp.other.CONSTANTS.KEY_NAME
import com.example.runningtrackerapp.other.CONSTANTS.KEY_WEIGHT
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_setup.*
import javax.inject.Inject


@AndroidEntryPoint
class SetupFragment: Fragment(R.layout.fragment_setup) {

    @Inject
    lateinit var sharedPref : SharedPreferences

    @set:Inject
    var isFirstAppOpen = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.getColor(R.color.colorAccent)?.let {
            activity?.window?.setStatusBarColor(
                it
            )
        }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!isFirstAppOpen){
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.setupFragment, true)
                .build()
            findNavController().navigate(
                R.id.action_setupFragment_to_runFragment,
                savedInstanceState, navOptions
            )
        }

        tvContinue.setOnClickListener {
            val success = writePersonalDataToSharedPref()
            if (success) {
                findNavController().navigate(R.id.action_setupFragment_to_runFragment)
            }else{
                Snackbar.make(
                    requireView(),
                    "Please enter all fields",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }
    
    private fun writePersonalDataToSharedPref(): Boolean{
        val name = etName.text.toString()
        val weight = etWeight.text.toString()

        if (name.isEmpty() || weight.isEmpty()){
            return false
        }
        sharedPref.edit()
            .putString(KEY_NAME, name)
            .putFloat(KEY_WEIGHT, weight.toFloat())
            .putBoolean(KEY_FIRST_TIME_TOGGLE, false)
            .apply()
        val toolbarText = "Let's go, $name!"
        requireActivity().tvToolbarTitle.text = toolbarText
        return true
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }
}