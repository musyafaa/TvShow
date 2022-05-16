package com.example.tvshow.presentation.populartv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.tvshow.MainActivity
import com.example.tvshow.adapter.ItemTvPopularAdapter
import com.example.tvshow.databinding.ActivityPopularTvBinding

class PopularTvActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPopularTvBinding
    private lateinit var viewModel: PopularTvViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPopularTvBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initObserver()
        binding.btnLeft.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun initObserver() {
        viewModel = ViewModelProvider(this)[PopularTvViewModel::class.java]

        viewModel.getPopularTv()

        viewModel.errorMessage.observe(this){message ->
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }

        viewModel.listTvData.observe(this) {listTvData ->
            val listTvAdapter = ItemTvPopularAdapter(listTvData)
            binding.rvPopularTv.adapter = listTvAdapter

            listTvAdapter.onItemClick = { resultsItem ->
                val intent = Intent(this, DetailsActivity::class.java)
                intent.putExtra(DetailsActivity.EXTRA_TV_ID, resultsItem.id)
                startActivity(intent)
            }
        }
    }
}