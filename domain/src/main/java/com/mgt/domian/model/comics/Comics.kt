package com.mgt.domian.model.comics

import com.mgt.domian.model.Image
import kotlinx.serialization.Serializable

@Serializable
data class Comics(
    val id: Int? = null,
    val title: String? = null,
    val thumbnail: Image? = null
)