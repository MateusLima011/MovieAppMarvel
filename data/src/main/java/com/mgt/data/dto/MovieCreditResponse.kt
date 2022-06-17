package com.mgt.data.dto

import com.mgt.domian.model.movies.Cast
import com.mgt.domian.model.movies.Crew
import kotlinx.serialization.Serializable

@Serializable
data class MovieCreditResponse(
    val id: Int,
    val cast: List<Cast>,
    val crew: List<Crew>
)
