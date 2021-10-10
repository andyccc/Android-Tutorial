package com.example.pagergallery
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize data class Pixabay(
    val totalHits: Int,
    val total: Int,
    val hits: Array<PhotoItem>
): Parcelable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Pixabay

        if (totalHits != other.totalHits) return false
        if (total != other.total) return false
        if (!hits.contentEquals(other.hits)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = totalHits
        result = 31 * result + total
        result = 31 * result + hits.contentHashCode()
        return result
    }
}

@Parcelize data class PhotoItem(
    @SerializedName("webformatURL") val previewUrl: String,
    @SerializedName("id") val photoId: Int,
    @SerializedName("largeImageURL") val fullUrl: String,
    @SerializedName("webformatHeight") val photoHeight: Int,
    @SerializedName("user") val photoUser: String,
    @SerializedName("likes") val photoLikes: Int,
    @SerializedName("collections") val photoFavorites: Int



//    val webformatURL: String,
//    val id: Int,
//    val largeImageURL: String
): Parcelable