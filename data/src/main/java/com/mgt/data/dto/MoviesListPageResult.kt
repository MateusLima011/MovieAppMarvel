package com.mgt.data.dto

import com.mgt.domian.model.movies.Movie
import kotlinx.serialization.Serializable

@Serializable
data class MoviesListPageResult(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)
