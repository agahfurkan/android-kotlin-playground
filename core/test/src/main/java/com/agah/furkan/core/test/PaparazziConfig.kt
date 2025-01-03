package com.agah.furkan.core.test

import com.android.ide.common.rendering.api.SessionParams

data class PaparazziConfig(
    val renderMode: SessionParams.RenderingMode = SessionParams.RenderingMode.SHRINK,
    val theme: String? = null
)