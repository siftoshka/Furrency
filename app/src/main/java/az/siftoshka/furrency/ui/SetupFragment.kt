package az.siftoshka.furrency.ui

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import az.siftoshka.furrency.Constants.PREF_LAUNCH
import az.siftoshka.furrency.Constants.TURN_ON
import az.siftoshka.furrency.R
import kotlinx.android.synthetic.main.fragment_setup.*
import org.koin.android.ext.android.inject
import timber.log.Timber

class SetupFragment: Fragment(R.layout.fragment_setup) {

    private val sharedPreferences: SharedPreferences by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            requireActivity().window.navigationBarColor = resources.getColor(R.color.mainBackground)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firstLaunch()
    }

    private fun firstLaunch() {
        Timber.d("First launch")
        val launch = sharedPreferences.getInt(PREF_LAUNCH, 0)
        if (launch != TURN_ON) {
            button.setOnClickListener {
                findNavController().navigate(R.id.action_setupFragment_to_mainFragment)
                sharedPreferences.edit()
                    .putInt(PREF_LAUNCH, TURN_ON)
                    .apply()
            }
        } else
            findNavController().navigate(R.id.action_setupFragment_to_mainFragment)
    }
}