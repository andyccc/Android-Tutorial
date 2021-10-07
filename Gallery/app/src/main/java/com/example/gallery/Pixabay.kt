package com.example.gallery
import com.google.gson.annotations.SerializedName;

data class Pixabay(
    val totalHits: Int,
    val total: Int,
    val hits: Array<PhotoItem>
) {
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

data class PhotoItem(
    @SerializedName("webformatURL") val previewUrl: String,
    val id: Int,
    val largeImageURL: String
)