package com.example.lambdadelta

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import mu.KotlinLogging

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //val screenSize = getScreenSize(this)

        super.onCreate(savedInstanceState)
        getLambdaCalculusExplanation()
        getHigherOrderFunctions()
        getFunctionalProgramming()

//        if (screenSize.first >= 1600) {
//            setContentView(R.layout.activity_main_tablet)
//        } else {
//            setContentView(R.layout.activity_main)
//        }

        val configuration = resources.configuration

        val screenSize = configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK

        when (screenSize) {
            Configuration.SCREENLAYOUT_SIZE_XLARGE -> {
                setContentView(R.layout.activity_main_tablet)
            }
            Configuration.SCREENLAYOUT_SIZE_NORMAL -> {
                setContentView(R.layout.activity_main)
            }
        }
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.18.4:5000")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(ApiService::class.java)

    private fun getLambdaCalculusExplanation() {
        val call = apiService.getLambdaCalculusExplanation()

        call.enqueue(object : Callback<ExplanationResponse> {
            override fun onResponse(call: Call<ExplanationResponse>, response: Response<ExplanationResponse>) {
                if (response.isSuccessful) {
                    val explanation = response.body()?.explanation
                    val id = findViewById<TextView>(R.id.lc_content)
                    id.setText(explanation)
                } else {
                    val id = findViewById<TextView>(R.id.lc_content)
                    id.setText("Kesalahan menampilkan data")
                }
            }

            override fun onFailure(call: Call<ExplanationResponse>, t: Throwable) {
                val id = findViewById<TextView>(R.id.lc_content)
                id.setText(t.toString())
            }
        })
    }

    private fun getHigherOrderFunctions() {
        val call = apiService.getHigherOrderFunctionsExplanation()

        call.enqueue(object : Callback<ExplanationResponse> {
            override fun onResponse(call: Call<ExplanationResponse>, response: Response<ExplanationResponse>) {
                if (response.isSuccessful) {
                    val explanation = response.body()?.explanation
                    val id = findViewById<TextView>(R.id.hof_content)
                    id.setText(explanation)
                } else {
                    val id = findViewById<TextView>(R.id.hof_content)
                    id.setText("Kesalahan menampilkan data")
                }
            }

            override fun onFailure(call: Call<ExplanationResponse>, t: Throwable) {
                val id = findViewById<TextView>(R.id.hof_content)
                id.setText("Kesalahan menampilkan data")
            }
        })
    }

    private fun getFunctionalProgramming() {
        val call = apiService.getFunctionalProgrammingBasicsExplanation()

        call.enqueue(object : Callback<ExplanationResponse> {
            override fun onResponse(call: Call<ExplanationResponse>, response: Response<ExplanationResponse>) {
                if (response.isSuccessful) {
                    val explanation = response.body()?.explanation
                    val id = findViewById<TextView>(R.id.fp_content)
                    id.setText(explanation)
                } else {
                    val id = findViewById<TextView>(R.id.fp_content)
                    id.setText("Kesalahan menampilkan data")
                }
            }

            override fun onFailure(call: Call<ExplanationResponse>, t: Throwable) {
                val id = findViewById<TextView>(R.id.fp_content)
                id.setText("Kesalahan menampilkan data")
            }
        })
    }
}

