Hello, I have completed the exam!

Attached is my project from android studio!

Tasks 1, 2 and 3 have been completed!
The app itself can work bringing up the project on android studio and emulating it.

Thank you so much for your time! This was a very fun exam to work on and I would love feedback so I can learn from my mistakes!

Practice Project:

# Peaksware Android Code Test

Please build an Android application that formats and displays workout data that is parsed from the provided REST endpoint.  

REST endpoint: https://tpapi.trainingpeaks.com/public/v1/workouts/{workoutTag}

The REST endpoint requires an HTTP request header of `Accept: application/json`

Example workoutTag: EDVCA4AJL56V27I3UAUKM72C74

Example endpoint: https://tpapi.trainingpeaks.com/public/v1/workouts/EDVCA4AJL56V27I3UAUKM72C74

You will be given a list of tasks. Each task will be built on the previous task and therefore it is required that the tasks are completed in order.

## Task 1

Create a new activity (Activity 1) with an EditText field and a button below the EditText field.
- The EditText field should have a label or hint text that says “Workout Tag”
- The Button text should say “Get Workout”.
- Pressing the button should take the workoutTag that is entered in the EditText field and pass it to a new activity (Activity 2), this new activity will retrieve data from the endpoint: https://tpapi.trainingpeaks.com/public/v1/workouts/{workoutTag}

Use the workoutTag EDVCA4AJL56V27I3UAUKM72C74 to test your app.


## Task 2

In the new activity (Activity 2) retrieve data from the endpoint https://tpapi.trainingpeaks.com/public/v1/workouts/{workoutTag} using the workoutTag that was passed from the previous activity.
After retrieving the data, parse the PeakHeartRates and PeakSpeeds from the data. The PeakHeartRates will be found in the "peakHeartRates" field and the PeakSpeeds will be found in the "peakSpeeds" field.

#### The following is a description of the PeakHeartRate data properties:

“begin” - the timestamp in milliseconds for the start of the given PeakHeartRate

“end” - the timestamp in milliseconds for the end of the given PeakHeartRate

“interval” - the duration length of the PeakHeartRate in seconds

“value” - the value of the PeakHeartRate in Beats Per Minute (bpm)


#### The following is a description of the PeakSpeed data properties:

“begin” - the timestamp in milliseconds for the start of the given PeakSpeed

“end” - the timestamp in milliseconds for the end of the given PeakSpeed

“interval” - the duration length of the PeakSpeed in seconds

“value” - the value of the PeakSpeed in Meters Per Second


## Task 3

In Activity 2, create two tabs that each have a RecyclerView. The first tab should format and display the PeakHeartRates list that was parsed in the previous task. The second tab should format and display the PeakSpeeds lists as peak pace.

Each row should be displayed in the following format:

Peak \<INTERVAL\> \<VALUE\> \<UNIT\>

The value and units for heart rate should be shown in Beats Per Minute (bpm). The PeakSpeeds list should be shown as peak pace, therefore the value and units should be shown in Minutes Per Mile (min/mi).

You should sort the items in the list from least to greatest based on the interval value. Remove any duplicate values in the list.

If the interval is less than a minute then it should be displayed in only seconds using the format \"X sec\". If the interval is exactly a number of minutes then it should be displayed using the format \"X min\". If the interval is exactly a number of hours then it should be displayed using the format \"X hr\". Any other interval computed times should be displayed using the format \"MM:SS\" or \"HH:MM:SS\".

The items in the list do not need to be selectable.


#### The following is an example of how the PeakHeartRates list should be displayed:

Peak 2 sec 188 bpm

Peak 5 sec 188 bpm

Peak 10 sec 188 bpm

Peak 12 sec 188 bpm

Peak 20 sec 187 bpm

Peak 30 sec 186 bpm

Peak 1 min 185 bpm

Peak 01:12 176 bpm

Peak 2 min 183 bpm

Peak 5 min 181 bpm

Peak 6 min 180 bpm

Peak 10 min 177 bpm

Peak 12 min 176 bpm

Peak 13:39 175 bpm


#### The following is an example of how the PeakSpeeds list should be displayed:

Peak 2 sec 03:51 min/mi

Peak 5 sec 03:51 min/mi

Peak 10 sec 03:52 min/mi

Peak 12 sec 03:52 min/mi

Peak 20 sec 03:54 min/mi

Peak 30 sec 03:59 min/mi

Peak 1 min 04:19 min/mi

Peak 2 min 04:40 min/mi

Peak 5 min 05:01 min/mi

Peak 6 min 05:03 min/mi

Peak 10 min 05:06 min/mi

Peak 12 min 05:07 min/mi


(Note: the above is simply an example, there may be more or less peak values in the list than shown above)

## Hints

Use the workoutTag EDVCA4AJL56V27I3UAUKM72C74 to test your app. We suggest you temporarily hardcode this value while working to save time, however once your app is submitted it should work for any valid workoutTag that is entered in the EditText of Activity 1.

This app needs to respect all orientations, please account for rotation when completing each task.

The endpoint is public and is not behind any type of authentication.

You do NOT need to parse the entire data set that comes from the REST endpoint. You should only parse the pieces of data that you need. We suggest you do not waste time parsing additional properties that are not used that come down in the JSON.

The purpose of this test is to demonstrate your understanding of Android patterns and best practices, efficient algorithms, and general clean coding habits. You are free to use whatever frameworks and libraries you like.

## Submission

Please submit your test as an emailed zip file or link to a private repo or private file sharing system.
