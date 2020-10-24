package com.example.runningtrackerapp.other

import android.Manifest
import android.content.Context
import android.location.Location
import android.os.Build
import android.os.Build.VERSION_CODES.Q
import com.example.runningtrackerapp.services.Polyline
import pub.devrel.easypermissions.EasyPermissions
import java.util.concurrent.TimeUnit

object TrackingUtility {
    // only run this fun for android version Q and above
    fun hasLocationPermissions(context: Context) =
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.Q){
            EasyPermissions.hasPermissions(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        }else{
            EasyPermissions.hasPermissions(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION
            )
        }

    fun getFormatedTime(ms: Long, includeMillis: Boolean = false): String{
        var millisec = ms
        val hours = TimeUnit.MILLISECONDS.toHours(millisec)
        millisec -= TimeUnit.HOURS.toMillis(hours)
        val min = TimeUnit.MILLISECONDS.toMinutes(millisec)
        millisec -= TimeUnit.MINUTES.toMillis(min)
        val seconds = TimeUnit.MILLISECONDS.toSeconds(millisec)
        if (!includeMillis){
            return "${if (hours<10) "0" else ""}$hours:" +
                    "${if (min<10) "0" else ""}$min:" +
                    "${if (seconds<10) "0" else ""}$seconds"
        }
        millisec -= TimeUnit.SECONDS.toMillis(seconds)
        millisec /= 10
        return "${if (hours<10) "0" else ""}$hours:" +
                "${if (min<10) "0" else ""}$min:" +
                "${if (seconds<10) "0" else ""}$seconds:" +
                "${if(millisec < 10) "0" else ""}$millisec"
    }

    fun calPolylineLength(polyline: Polyline): Float{
        var distance = 0f
        for(i in 0..polyline.size -2){
            val pos1 = polyline[i]
            val pos2 =  polyline[i+1]

            val result = FloatArray(1)
            Location.distanceBetween(
                pos1.latitude,
                pos1.longitude,
                pos2.latitude,
                pos2.longitude,
                result
            )
            distance += result[0]
        }
        return distance
    }
}