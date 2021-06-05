package com.smartmobileproject.function;

import android.media.ExifInterface;

public class ExifInfo {
    ExifInterface exif ;
    public ExifInfo(ExifInterface exif){
       this.exif = exif;
    }

    public String ExifDate(){
        String dateinfo_s = exif.TAG_DATETIME;
        String dateinfo = exif.getAttribute(dateinfo_s);
        return dateinfo;
    }

    public Float Exiflatitude(){
        String latitude_s = exif.TAG_GPS_LATITUDE;
        String latitude_e = exif.getAttribute(latitude_s);
        Float latitude;
        if(latitude_e != null){
            latitude = convertToDegree(latitude_e);
        }
        else{
            latitude = 0.00f;
        }

        return latitude;
    }

    public Float Exiflongtitude(){
        String longtitude_s = exif.TAG_GPS_LONGITUDE;
        String longtitude_e = exif.getAttribute(longtitude_s);
        Float longtitude;
        if(longtitude_e != null) {
           longtitude = convertToDegree(longtitude_e);
        }
        else{
            longtitude = 0.00f;
        }

        return longtitude;
    }

    private Float convertToDegree(String stringDMS) {
        Float result = null;

        String[] DMS = stringDMS.split(",", 3);

        String[] stringD = DMS[0].split("/", 2);
        Double D0 = new Double(stringD[0]);
        Double D1 = new Double(stringD[1]);
        Double FloatD = D0 / D1;

        String[] stringM = DMS[1].split("/", 2);
        Double M0 = new Double(stringM[0]);
        Double M1 = new Double(stringM[1]);
        Double FloatM = M0 / M1;

        String[] stringS = DMS[2].split("/", 2);
        Double S0 = new Double(stringS[0]);
        Double S1 = new Double(stringS[1]);
        Double FloatS = S0 / S1;

        result = new Float(FloatD + (FloatM / 60) + (FloatS / 3600));

        return result;

    };
}
