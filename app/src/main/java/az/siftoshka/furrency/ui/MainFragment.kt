package az.siftoshka.furrency.ui

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import az.siftoshka.furrency.R
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.android.ext.android.inject

class MainFragment : Fragment(R.layout.fragment_main) {

    private val sharedPreferences: SharedPreferences by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            requireActivity().window.navigationBarColor = resources.getColor(R.color.colorPrimary)
    }

    override fun onResume() {
        super.onResume()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            requireActivity().window.navigationBarColor = resources.getColor(R.color.colorPrimary)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        settingsIcon.setOnClickListener { findNavController().navigate(R.id.action_mainFragment_to_settingsFragment) }
    }
}