package com.example.olivia.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Julian on 2/26/2017.
 */

public class PurityReport implements Parcelable {
    final private String date;
    final private String time;
    final private int reportNumber;
    final private String workerName;
    final private String location;
    final private String condition;
    final private int virusPPM;
    //private String contamination;


//    public PurityReport(String _date, String _time, int _reportNumber,
//                        String _workerName, String _location,
//                        String _condition, int _virusPPM) {
//
//        date = _date;
//        time = _time;
//        reportNumber = _reportNumber;
//        workerName = _workerName;
//        location = _location;
//        condition = _condition;
//        virusPPM = _virusPPM;
//    }
    private PurityReport(Parcel in) {
        date = in.readString();
        time = in.readString();
        reportNumber = in.readInt();
        workerName = in.readString();
        location = in.readString();
        condition = in.readString();
        virusPPM = in.readInt();
    }
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(time);
        dest.writeInt(reportNumber);
        dest.writeString(workerName);
        dest.writeString(location);
        dest.writeString(condition);
        dest.writeString(String.valueOf(virusPPM));
    }

    public static final Parcelable.Creator<PurityReport> CREATOR
            = new Parcelable.Creator<PurityReport>() {
            public PurityReport createFromParcel(Parcel in) {
               return new PurityReport(in);
            }

            public PurityReport[] newArray(int size) {
                return new PurityReport[size];
            }
        };

//    public String getWorkerName() {
//        return workerName;
//    }
   // public int getVirusPPM() {
   //     return virusPPM;
   // }
//    public int getReportNumber() {
//        return reportNumber;
//    }
    public String getTime() {
        return time;
    }
//    public String getDate() {
//        return date;
//    }
//    public String getLocation() {
//        return location;
//    }
//    public String getCondition() {
//        return condition;
//    }
//    public String toString() {
//        return date + " " + location + " " + workerName;
//    }

}
