package com.docwei.mvvmdemokt.view

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.docwei.mvvmdemokt.R
import com.docwei.mvvmdemokt.databinding.ActivityMainBinding


import com.docwei.mvvmdemokt.repository.db.CoolWeatherDatabase
import com.docwei.mvvmdemokt.repository.CoolWeatherNetwork
import com.docwei.mvvmdemokt.repository.WeatherRepository
import com.docwei.mvvmdemokt.viewmodel.WeatherViewModel

class MainActivity : AppCompatActivity() {

    val viewModel by lazy {
        ViewModelProvider(this,object:ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return WeatherViewModel(
                    WeatherRepository.getInstance(
                        CoolWeatherDatabase.getWeatherDao(),
                        CoolWeatherNetwork.getInstance()
                    )
                ) as T
            }

        })
            .get(WeatherViewModel::class.java)
    }

    private val binding by lazy { DataBindingUtil.setContentView<ActivityMainBinding>(this,
        R.layout.activity_main
    )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= 21) {
            val decorView = window.decorView
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.statusBarColor = Color.TRANSPARENT
        }
        binding.viewModel = viewModel
        binding.resId = R.color.colorPrimary

        //跟livedata.addObserver作用一样
        binding.lifecycleOwner = this

        viewModel.weatherId ="CN101280601"
        viewModel.getWeather()
    }
}
