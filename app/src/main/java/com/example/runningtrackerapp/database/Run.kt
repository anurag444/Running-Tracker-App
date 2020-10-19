package com.example.runningtrackerapp.database

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "run_table")
data class Run(
    //Nullable Bitmap
    var image: Bitmap? = null,
    //We are using long for timestamp instead of date because it will be easier to sort
    var timestamp: Long = 0L,
    var avgSpeedInKMH: Float = 0f,
    var distanceInM: Int = 0,
    var timeInMillis: Long = 0L, //describes how long the run was
    var burnedCalories: Int = 0
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}