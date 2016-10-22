package com.example.ashwin.facebooknotificationtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);

        Intent startingIntent = this.getIntent();
        Bundle pushData = startingIntent.getBundleExtra("push");
        if (pushData != null) {
            AppEventsLogger logger = AppEventsLogger.newLogger(this);
            logger.logPushNotificationOpen(pushData, startingIntent.getAction());
            Toast.makeText(this, "Notification opened!", Toast.LENGTH_LONG).show();
        }
    }

    public void test1Clicked(View view) {
        AppEventsLogger logger = AppEventsLogger.newLogger(this);
        // Add to a button click handler
        logger.logEvent("Test 1 button clicked");
    }
}
