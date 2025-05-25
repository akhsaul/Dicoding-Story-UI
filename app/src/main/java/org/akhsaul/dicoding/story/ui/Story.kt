package org.akhsaul.dicoding.story.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.Instant

@Parcelize
data class Story(
    val id: String,
    val name: String,
    val description: String,
    val photoUrl: String,
    val createdAt: Instant,
    val lat: Double,
    val lon: Double
) : Parcelable
