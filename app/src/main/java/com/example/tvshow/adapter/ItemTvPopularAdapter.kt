package com.example.tvshow.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.tvshow.BuildConfig
import com.example.tvshow.R
import com.example.tvshow.data.network.response.ResultsItem
import com.example.tvshow.databinding.ItemPopularTvBinding

class ItemTvPopularAdapter(private val dataSet: List<ResultsItem>):
    RecyclerView.Adapter<ItemTvPopularAdapter.ViewHolder>(){

    var onItemClick:((ResultsItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemIndicatorBinding = ItemPopularTvBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemIndicatorBinding)
    }
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int){
        val data = dataSet[position]
        viewHolder.bind(data)
    }

    override fun getItemCount() = dataSet.size

    inner class ViewHolder(private val binding: ItemPopularTvBinding):
            RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ResultsItem) {
            binding.apply {
                tvName.text = data.name
                tvDescPopular.text = data.overview
                tvPopular.text = data.popularity.toString()
                tvTanggal.text = data.firstAirDate

                ivMoonKnight.load("${BuildConfig.BASE_IMAGE_URL}${data.posterPath}"){
                    error(R.drawable.knight)
                }

            }
            binding.root.setOnClickListener {
                onItemClick?.invoke(dataSet[layoutPosition])
            }
        }
    }

}