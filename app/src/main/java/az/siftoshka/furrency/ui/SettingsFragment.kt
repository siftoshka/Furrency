package az.siftoshka.furrency.ui

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import az.siftoshka.furrency.Constants
import az.siftoshka.furrency.Constants.PREF_MODE
import az.siftoshka.furrency.Constants.TURN_OFF
import az.siftoshka.furrency.Constants.TURN_ON
import az.siftoshka.furrency.R
import az.siftoshka.furrency.UtilsListener
import kotlinx.android.synthetic.main.fragment_settings.*
import org.koin.android.ext.android.inject

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private var utilsListener: UtilsListener? = null
    private val sharedPreferences: SharedPreferences by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            requireActivity().window.navigationBarColor = resources.getColor(R.color.mainBackground)
    }

    override fun onResume() {
        super.onResume()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            requireActivity().window.navigationBarColor = resources.getColor(R.color.mainBackground)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is UtilsListener) utilsListener = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        cToolbar.setExpandedTitleTextAppearance(R.style.CollapsingExpanded)
        cToolbar.setCollapsedTitleTextAppearance(R.style.CollapsingCollapsed)
        telegramContact.setOnClickListener { showTelegramPage() }
        githubContact.setOnClickListener { showGithubPage() }
        instagramContact.setOnClickListener { showInstagramPage() }
        rateButton.setOnClickListener { showGooglePlay() }
        spannableCreditFreepik()
        modeSwitcher()
        toolbar.setNavigationOnClickListener {
            utilsListener?.back()
        }
    }

    private fun modeSwitcher() {
        val tapMode = sharedPreferences.getInt(PREF_MODE, 0)

        modeSwitcher.isChecked = tapMode == TURN_ON
        modeSwitcher.setOnCheckedChangeListener { _, b ->
            if (b) {
                sharedPreferences.edit()
                    .putInt(PREF_MODE, TURN_ON)
                    .apply()
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                sharedPreferences.edit()
                    .putInt(PREF_MODE, TURN_OFF)
                    .apply()
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    private fun showTelegramPage() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(Constants.DEV_TELEGRAM)
        startActivity(intent)
    }

    private fun showGithubPage() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(Constants.DEV_GITHUB)
        startActivity(intent)
    }

    private fun showInstagramPage() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(Constants.DEV_INSTAGRAM)
        startActivity(intent)
    }

    private fun showGooglePlay() {
        val appPackageName = requireContext().packageName
        try {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=$appPackageName")
                )
            )
        } catch (anfe: ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                )
            )
        }
    }

    private fun spannableCreditFreepik() {
        val cFreepik = creditsFreepik.text.toString()
        val spannableStringFreepik = SpannableString(cFreepik)
        val clickableSpan1: ClickableSpan = object : ClickableSpan() {
            override fun onClick(textView: View) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(Constants.DESIGNER_FREEPIK)
                startActivity(intent)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
            }
        }
        val clickableSpan2: ClickableSpan = object : ClickableSpan() {
            override fun onClick(textView: View) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(Constants.FLATICON)
                startActivity(intent)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
            }
        }
        spannableStringFreepik.setSpan(clickableSpan1, 14, 21, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableStringFreepik.setSpan(clickableSpan2, 27, 43, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        creditsFreepik.text = spannableStringFreepik
        creditsFreepik.movementMethod = LinkMovementMethod.getInstance()
    }
}