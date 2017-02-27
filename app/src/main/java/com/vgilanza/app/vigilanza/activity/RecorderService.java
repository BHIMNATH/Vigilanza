package com.vgilanza.app.vigilanza.activity;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.graphics.Camera;
import android.hardware.camera2.CameraDevice;
import android.media.MediaRecorder;
import android.os.IBinder;
import android.view.SurfaceHolder;

import java.io.File;

public class RecorderService extends Service {
    private SurfaceHolder surfaceHolder;
    //public static android.hardware.camera2. camera;
    public static boolean recordingStatus;
    private MediaRecorder mediaRecorder;
    private File directory;
    private int cameraType =0;

    @Override
    public void onCreate() {
        recordingStatus=false;
       // camera = Capture.mCamera;
//        surfaceHolder=Capture.mSurfaceHolder;

        super.onCreate();
    }

    public RecorderService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
        //if(recordingStatus){
          // startRecording();
        //}
        //return START_STICKY;
    }

    @Override
    public void onDestroy() {
        //stopRecording();
        //camera.release();
        recordingStatus=false;
        super.onDestroy();
    }
//    public boolean startRecording(){
//        try {
//            recordingStatus=true;
//            //camera= android.hardware.Camera.open();
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//    }
}
