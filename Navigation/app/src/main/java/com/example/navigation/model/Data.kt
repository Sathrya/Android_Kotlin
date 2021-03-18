package com.example.navigation.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
    @SerializedName("created_at")
    var createdAt: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("gender")
    var gender: String,
    @SerializedName("id")
    var id: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("status")
    var status: String,
    @SerializedName("updated_at")
    var updatedAt: String,
    var avatar: Int?,
    var deleteIcon: Int?
):Parcelable