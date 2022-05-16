package com.example.tvshow.presentation.populartv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.tvshow.BuildConfig
import com.example.tvshow.R
import com.example.tvshow.databinding.ActivityDetailsBinding
import com.example.tvshow.presentation.onair.OnAirTvActivity

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var viewModel: PopularTvDetailsViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLeft.setOnClickListener {
            val intent = Intent(this, PopularTvActivity::class.java)
            startActivity(intent)
        }
        initView()

    }

    private fun initView() {
        viewModel = ViewModelProvider(this)[PopularTvDetailsViewModel::class.java]

        val id = intent.getIntExtra(EXTRA_TV_ID, 0)
        viewModel.getTvDetailsById(id.toString())

        viewModel.errorMessage.observe(this){message ->
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
        viewModel.tvDetailsData.observe(this){ data ->
            binding.apply {
                tvName.text = data.name
                tvTitle.text = data.name
                tvDesc.text = data.overview
                tvDate.text = data.firstAirDate
                tvCountry.text = data.originCountry.toString()

                ivDetail.load("${BuildConfig.BASE_IMAGE_URL}${data.posterPath}"){
                    error(R.drawable.knight)
                }
            }

        }
    }
    companion object {
        const val EXTRA_TV_ID = "extra_tv_id"

    }
}