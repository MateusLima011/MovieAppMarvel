package com.mgt.data.dto

import com.mgt.domian.model.movies.Movie
import kotlinx.serialization.Serializable

@Serializable
data class MoviesListPageResult(
    val page: Int? = null,
    val results: List<Movie>? = null,
    val total_pages: Int? = null,
    val total_results: Int? = null,
    val items: List<Movie>? = null
)
