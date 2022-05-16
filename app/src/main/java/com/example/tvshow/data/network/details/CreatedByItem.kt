package com.example.tvshow.data.network.details

import com.google.gson.annotations.SerializedName

data class CreatedByItem(

	@field:SerializedName("gender")
	val gender: Int,

	@field:SerializedName("credit_id")
	val creditId: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("profile_path")
	val profilePath: Any,

	@field:SerializedName("id")
	val id: Int
)