package com.mgt.domian.model.comics

import kotlinx.serialization.Serializable

@Serializable
data class ComicDataContainer(
    val results: List<Comics>? = null
)
