//package com.example.rlagk.ks_project001;
//
//import android.app.Activity;
//import android.graphics.Camera;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.FrameLayout;
//
//import com.example.rlagk.ks_project001.View.CameraPreview;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;
//
//public class CameraActivity extends Activity{
//    private static String TAG = CameraActivity.class.getName();
//    private Camera mCamera;
//    private CameraPreview mPreview;
//
//    private void getCameraInstance
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_camera_main);
//
//        // Create an instance of Camera
//        mCamera = getCameraInstance();
//
//        // Create our Preview view and set it as the content of our activity.
//        mPreview = new CameraPreview(this, mCamera);
//        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
//        preview.addView(mPreview);
//    }
//
//    private android.hardware.Camera.PictureCallback mPicture = new android.hardware.Camera.PictureCallback() {
//
//        @Override
//        public void onPictureTaken(byte[] data, android.hardware.Camera camera) {
//            File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
//            if (pictureFile == null){
//                Log.d(TAG, "Error creating media file, check storage permissions");
//                return;
//            }
//
//            try {
//                FileOutputStream fos = new FileOutputStream(pictureFile);
//                fos.write(data);
//                fos.close();
//            } catch (FileNotFoundException e) {
//                Log.d(TAG, "File not found: " + e.getMessage());
//            } catch (IOException e) {
//                Log.d(TAG, "Error accessing file: " + e.getMessage());
//            }
//        }
//    };
//}
