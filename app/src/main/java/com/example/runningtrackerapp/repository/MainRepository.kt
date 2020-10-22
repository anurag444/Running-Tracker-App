package com.example.runningtrackerapp.repository

import com.example.runningtrackerapp.database.Run
import com.example.runningtrackerapp.database.RunDAO
import javax.inject.Inject

class MainRepository @Inject constructor(
    val runDao: RunDAO
) {
    suspend fun insertRun(run: Run) = runDao.insertRun(run)

    suspend fun deleteRun(run: Run) = runDao.deleteRun(run)

    fun getAllSortByDate() = runDao.getAllRunsByDate()

    fun getAllSortByDistance() = runDao.getAllRunsByDistance()

    fun getAllSortByAvgSpeed() = runDao.getAllRunsByAvgSpeed()

    fun getAllSortByTimeInMillis() = runDao.getAllRunsByTimeMillis()

    fun getAllSortByCalories() = runDao.getAllRunsByCalories()

    fun getTotalAvgSpeed() = runDao.getTotalAvgSpeed()

    fun getTotalDistance() = runDao.getTotalDistance()

    fun getTotalTimeInMillis() = runDao.getTotalTimeInMillis()

    fun getTotalCalories() = runDao.getTotalCalories()
}