package com.mgt.domian.model

import kotlinx.serialization.Serializable

@Serializable
data class Comics(
    val id: Int? = null,
    val title: String? = null,
    val thumbnail: Image? = null
)