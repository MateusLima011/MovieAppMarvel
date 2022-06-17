package com.mgt.domian.model.stories

import com.mgt.domian.model.Image
import kotlinx.serialization.Serializable

@Serializable
data class Stories(
    val id: Int? = null,
    val title: String? = null,
    val description: String? = null,
    val thumbnail: Image? = null
)
