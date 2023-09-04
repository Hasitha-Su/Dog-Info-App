package com.hasitha.daggethilttest2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.hasitha.daggethilttest2.databinding.ActivityMainBinding
import com.hasitha.daggethilttest2.views.DogViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: DogViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this)[DogViewModel::class.java]

        binding.dogVM = viewModel
        binding.lifecycleOwner = this

        binding.button.setOnClickListener {
            viewModel.getDogInfo()
        }

        viewModel.dogData.observe(this){
            it?.let {
                Glide.with(this).load(it.message).into(binding.imageView)
            }
        }
    }
}