package com.mgt.data.dto

import com.mgt.domian.model.stories.StoryDataContainer
import kotlinx.serialization.Serializable

@Serializable
data class StoriesDataWrapper(
    val data: StoryDataContainer? = null
)
