package com.example.tvshow.presentation.today

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.tvshow.BuildConfig
import com.example.tvshow.R
import com.example.tvshow.databinding.ActivityTvTodayDetailBinding

class TvTodayDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTvTodayDetailBinding
    private lateinit var viewModel: TodayTvDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTvTodayDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLeftToday.setOnClickListener {
            val intent = Intent(this, TodayTvActivity::class.java)
            startActivity(intent)
        }
        initView()
    }

    private fun initView() {
        viewModel = ViewModelProvider(this)[TodayTvDetailViewModel::class.java]

        val id = intent.getIntExtra(EXTRA_TV_ID, 0)
        viewModel.getTvDetailsById(id.toString())

        viewModel.errorMessage.observe(this){message ->
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
        viewModel.TvDetailTodayData.observe(this){ data ->
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
    companion object{
        const val EXTRA_TV_ID = "extra_tv_id"
    }
}