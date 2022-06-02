package com.mgt.domian

import android.media.Image
import kotlinx.serialization.Serializable

@Serializable
data class Comics(
val id: Int? = null,
val name: String? = null,
val thumbnail: Image? = null
)