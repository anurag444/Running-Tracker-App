package com.example.runningtrackerapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.runningtrackerapp.converters.BitmapTypeConverter

@Database(
    entities = [Run::class],
    version = 1
)
@TypeConverters(BitmapTypeConverter::class)
abstract class RunDatabase: RoomDatabase() {

    abstract fun getRunDao():RunDAO
}