/***
 * Copyright (c) 2012 CommonsWare, LLC
 * Licensed under the Apache License, Version 2.0 (the "License") you may not
 * use this file except in compliance with the License. You may obtain a copy
 * of the License at http://www.apache.org/licenses/LICENSE-2.0. Unless required
 * by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * Covered in detail in the book _The Busy Coder's Guide to Android Development_
 * https://commonsware.com/Android
 */

package com.example.alarmmanagerdemo

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.util.*

class PollReceiver : BroadcastReceiver() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "Tick Tick Tick", Toast.LENGTH_LONG).show()
        Log.d(TAG, "onReceive()")
        Log.d(TAG, Calendar.getInstance().time.toString())
        ScheduledService.enqueueWork(context)
    }

    companion object {
        val TAG = PollReceiver::class.java.simpleName

        @RequiresApi(Build.VERSION_CODES.M)
        internal fun scheduleAlarms(context: Context) {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, PollReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

            val calendar = Calendar.getInstance()
            calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) + 15)

            // for testing: adb shell dumpsys deviceidle | grep mState
            // for testing: adb shell dumpsys battery unplug

            // DEMO : setAlarmClock
//            alarmManager.setAlarmClock(
//                AlarmManager.AlarmClockInfo(calendar.timeInMillis, pendingIntent),
//                pendingIntent
//            )

            // DEMO : AlarmManager.RTC
            //  This alarm does not wake the device up; if it goes off while the device is asleep, it will not be
            // delivered until the next time the device wakes up.
            // DEMO : AlarmManager.RTC_WAKEUP : Opposite to  AlarmManager.RTC it will wake device

            /**
             *  AlarmManager.set():  "batch" alarm
             */
//            alarmManager.set(AlarmManager.RTC, calendar.timeInMillis, pendingIntent)
//            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)

            /**
             *  AlarmManager.setExact(): No batch alarm
             */
//            alarmManager.setExact(AlarmManager.RTC, calendar.timeInMillis, pendingIntent)
//            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)

            /**
             *  AlarmManager.setExactAndAllowWhileIdle()
             */
//            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)

            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)

            Log.d(TAG, "scheduleAlarms()")
            Log.d(TAG, calendar.time.toString())
        }
    }
}
