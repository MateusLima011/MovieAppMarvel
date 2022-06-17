package com.mgt.domian.model.stories

import kotlinx.serialization.Serializable

@Serializable
data class StoryDataContainer(
    val results : List<Stories>? = null
)
