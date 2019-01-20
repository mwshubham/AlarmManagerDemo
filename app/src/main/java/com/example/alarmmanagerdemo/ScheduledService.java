/***
 Copyright (c) 2012 CommonsWare, LLC
 Licensed under the Apache License, Version 2.0 (the "License"); you may not
 use this file except in compliance with the License. You may obtain a copy
 of the License at http://www.apache.org/licenses/LICENSE-2.0. Unless required
 by applicable law or agreed to in writing, software distributed under the
 License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 OF ANY KIND, either express or implied. See the License for the specific
 language governing permissions and limitations under the License.

 Covered in detail in the book _The Busy Coder's Guide to Android Development_
 https://commonsware.com/Android
 */

package com.example.alarmmanagerdemo;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.core.app.JobIntentService;
import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

public class ScheduledService extends JobIntentService {
    private static final int UNIQUE_JOB_ID = 1337;

    static void enqueueWork(Context context) {
        enqueueWork(context, ScheduledService.class, UNIQUE_JOB_ID, new Intent(context, ScheduledService.class));
    }

    @Override
    public void onHandleWork(@NotNull Intent intent) {
        Log.d(getClass().getSimpleName(), "I ran!");
        Log.d(getClass().getSimpleName(), Calendar.getInstance().getTime().toString());
    }
}
