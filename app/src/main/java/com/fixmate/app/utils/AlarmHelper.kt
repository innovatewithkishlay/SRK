package com.fixmate.app.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/**
 * UNIT II & III: AlarmManager / JobScheduler dummy implementation
 */

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        NotificationHelper.showBookingNotification(
            context,
            "Reminder",
            "You have a service booking in 1 hour!"
        )
    }
}

object AlarmHelper {
    fun scheduleReminder(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context, 0, intent,
            PendingIntent.FLAG_IMMUTABLE
        )
        
        // UNIT III: Schedule a dummy notification after 5 seconds
        val triggerTime = System.currentTimeMillis() + 5000
        alarmManager.set(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent)
    }
}
