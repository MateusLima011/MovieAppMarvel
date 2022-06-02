package com.mgt.data.DAO

import com.mgt.domian.ComicDataContainer
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDataWrapper(
    val data: ComicDataContainer? = null
)
