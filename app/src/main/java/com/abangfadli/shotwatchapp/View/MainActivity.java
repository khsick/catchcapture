package com.abangfadli.shotwatchapp.View;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.abangfadli.shotwatch.ScreenshotData;
import com.abangfadli.shotwatch.ShotWatch;
import com.abangfadli.shotwatchapp.service.CaputreService;
import com.abangfadli.shotwatchapp.R;
import com.abangfadli.shotwatchapp.util.FileUtils;
import com.akexorcist.screenshotdetection.ScreenshotDetectionDelegate;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import iamutkarshtiwari.github.io.ananas.editimage.EditImageActivity;
import iamutkarshtiwari.github.io.ananas.editimage.ImageEditorIntentBuilder;


public class MainActivity extends AppCompatActivity {

    public static ShotWatch mShotWatch;

    public static TextView mText;
    public static  ImageView mImage;
    private final static int REQUEST_CODE_ASK_PERMISSIONS = 1;
    private static final String[] REQUIRED_SDK_PERMISSIONS = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE};
    private CaputreService captureService;
    private Intent intent;
    public static Context mContext;
    public static ContentResolver contentResolver;
    public static ScreenshotDetectionDelegate screenshotDetectionDelegate;
    public boolean iskill = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //권한요청

        checkPermissions();

        Button button = findViewById(R.id.end_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });
        capture();

            if (null == CaputreService.Companion.getServiceIntent()) {
                intent = new Intent(this, CaputreService.class);
                startService(intent);
                //  Toast.makeText(getApplicationContext(), "start service", Toast.LENGTH_LONG).show();
            } else {
                intent = CaputreService.Companion.getServiceIntent();
                //  Toast.makeText(getApplicationContext(), "already", Toast.LENGTH_LONG).show();
            }


    }


    @Override
    protected void onDestroy() {


        if (null != intent) {
            stopService(intent);
            intent = null;
        }

        super.onDestroy();
    }




    public void capture(){

        Log.i("khs","capture() 이값이오는가");

        mShotWatch = new ShotWatch(contentResolver, new ShotWatch.Listener() {
            @Override
            public void onScreenShotTaken(ScreenshotData screenshotData) {
              //  mText.setText(screenshotData.getFileName());
                Uri uri = Uri.parse(screenshotData.getPath());
             //   mImage.setImageURI(uri);
            }
        });
    }

    protected void checkPermissions() {
        final List<String> missingPermissions = new ArrayList<String>();
        // check all required dynamic permissions
        for (final String permission : REQUIRED_SDK_PERMISSIONS) {
            final int result = ContextCompat.checkSelfPermission(this, permission);
            if (result != PackageManager.PERMISSION_GRANTED) {
                missingPermissions.add(permission);
            }
        }
        if (!missingPermissions.isEmpty()) {
            // request all missing permissions
            final String[] permissions = missingPermissions
                    .toArray(new String[missingPermissions.size()]);
            ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE_ASK_PERMISSIONS);
        } else {
            final int[] grantResults = new int[REQUIRED_SDK_PERMISSIONS.length];
            Arrays.fill(grantResults, PackageManager.PERMISSION_GRANTED);
            onRequestPermissionsResult(REQUEST_CODE_ASK_PERMISSIONS, REQUIRED_SDK_PERMISSIONS,
                    grantResults);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                for (int index = permissions.length - 1; index >= 0; --index) {
                    if (grantResults[index] != PackageManager.PERMISSION_GRANTED) {
                        // exit the app if one permission is not granted
                        Toast.makeText(this, "Required permission '" + permissions[index]
                                + "' not granted, exiting", Toast.LENGTH_LONG).show();
                        finish();
                        return;
                    }
                }
                // all permissions were granted
                //initialize();
                break;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
      //  mShotWatch.register();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

}
