package com.mgt.data.dto

import com.mgt.domian.model.ComicDataContainer
import kotlinx.serialization.Serializable

@Serializable
data class ComicsDataWrapper(
    val data: ComicDataContainer? = null
)
