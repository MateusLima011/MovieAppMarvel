package com.mgt.domian.model.series

import com.mgt.domian.model.Image
import kotlinx.serialization.Serializable

@Serializable
data class Series(
    val id: Int? = null,
    val title: String? = null,
    val description: String? = null,
    val thumbnail: Image? = null
)
