package com.example.runningtrackerapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

// Interface that describes all actions we will take with our db
@Dao
interface RunDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRun(run: Run)

    @Delete
    suspend fun deleteRun(run: Run)

    @Query("SELECT * FROM run_table ORDER BY timestamp DESC")
    fun getAllRunsByDate(): LiveData<List<Run>>

    @Query("SELECT * FROM run_table ORDER BY timeInMillis DESC")
    fun getAllRunsByTimeMillis(): LiveData<List<Run>>

    @Query("SELECT * FROM run_table ORDER BY avgSpeedInKMH DESC")
    fun getAllRunsByAvgSpeed(): LiveData<List<Run>>

    @Query("SELECT * FROM run_table ORDER BY burnedCalories DESC")
    fun getAllRunsByCalories(): LiveData<List<Run>>

    @Query("SELECT * FROM run_table ORDER BY distanceInM DESC")
    fun getAllRunsByDistance(): LiveData<List<Run>>

    // Functions for statistics fragment
    @Query("SELECT SUM(timeInMillis) FROM run_table")
    fun getTotalTimeInMillis(): LiveData<Long>

    @Query("SELECT SUM(burnedCalories) FROM run_table")
    fun getTotalCalories(): LiveData<Int>

    @Query("SELECT AVG(avgSpeedInKMH) FROM run_table")
    fun getTotalAvgSpeed(): LiveData<Float>

    @Query("SELECT SUM(distanceInM) FROM run_table")
    fun getTotalDistance(): LiveData<Int>
}