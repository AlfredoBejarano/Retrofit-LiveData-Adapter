package me.alfredobejarano.livedataconverterdemo.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 *
 * Model class that defines a to do for a User.
 *
 * @author Alfredo Bejarano
 * @since November 25, 2018 - 20:38
 * @version 1.0
 **/
data class ToDo(
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("userId")
    val userId: Int,
    @Expose
    @SerializedName("title")
    val title: String,
    @Expose
    @SerializedName("completed")
    val completed: Boolean
)