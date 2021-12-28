package com.agah.furkan.androidplayground.domain.model.result

import com.agah.furkan.androidplayground.base.ListModel
import kotlin.random.Random

data class Announcement(val description: String, override val id: Long = Random.nextLong()) :
    ListModel
