package com.smartmobileproject.function;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

public class getFileList {

    public String[] getimageList() {

        File dir = new File(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsoluteFile())+"/CAMERA");
        Log.d("dir",dir.getAbsolutePath());
        if (! dir.isDirectory()){
            if (! dir.mkdirs()){
                    Log.d("사진 파일","error") ;}
        }



        String[] str = dir.list();
        return str;

    }






}
