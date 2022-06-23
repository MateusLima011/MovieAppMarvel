package com.mgt.domian.model.series

import kotlinx.serialization.Serializable

@Serializable
data class SeriesDataContainer(
    val results: List<Series>? = null
)
