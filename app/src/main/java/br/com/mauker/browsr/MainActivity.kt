package br.com.mauker.browsr

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.mauker.browsr.lib.BrowsrLib
import br.com.mauker.browsr.lib.BrowsrSDK
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    private val sdk: BrowsrLib by lazy {
        BrowsrSDK(applicationContext)
    }

    private lateinit var txtResult: TextView

    private var parentJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtResult = findViewById(R.id.txtResult)
    }

    override fun onResume() {
        super.onResume()

        // Very simple way of requesting the data. I just want to test if the SDK works.
        parentJob = CoroutineScope(Dispatchers.IO).launch {
            val organizations = sdk.getOrganizations()

            withContext(Dispatchers.Main) {
                txtResult.text = organizations.toString()
            }
        }
    }

    override fun onStop() {
        super.onStop()

        parentJob?.cancel()
    }
}