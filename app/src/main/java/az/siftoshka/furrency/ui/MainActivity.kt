package az.siftoshka.furrency.ui

import androidx.appcompat.app.AppCompatActivity
import az.siftoshka.furrency.R
import az.siftoshka.furrency.UtilsListener

class MainActivity : AppCompatActivity(R.layout.activity_main), UtilsListener {

    override fun back() {
        onBackPressed()
    }


}