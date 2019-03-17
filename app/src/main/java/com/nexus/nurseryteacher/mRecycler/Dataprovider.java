package com.nexus.nurseryteacher.mRecycler;

import android.content.Context;
import android.text.format.DateUtils;

import java.util.Date;

/**
 * Created by Yousef on 15/05/2017.
 */

public class Dataprovider {

        public Dataprovider(Context context, int c_image, int p_image , String eventname, Date date, String teachername, String description) {
            this.setC_image(c_image);
            this.setP_image(p_image);
            this.setEventname(eventname);
//            this.setDate(""+DateUtils.getRelativeTimeSpanString(date.getTime(), System.currentTimeMillis(),DateUtils.SECOND_IN_MILLIS,DateUtils.FORMAT_ABBREV_RELATIVE));
            this.setDate(""+DateUtils.getRelativeTimeSpanString(date.getTime(), System.currentTimeMillis(), 0L, DateUtils.FORMAT_ABBREV_RELATIVE));
            this.setTeachername(teachername);
            this.setDescription(description);
        }
        private int c_image;

    public int getP_image() {
        return p_image;
    }

    public void setP_image(int p_image) {
        this.p_image = p_image;
    }

    private int p_image;
        private  String eventname;
        private String date;
        private String teachername;

        public int getC_image() {
            return c_image;
        }

        public void setC_image(int c_image) {
            this.c_image = c_image;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTeachername() {
            return teachername;
        }

        public void setTeachername(String teachername) {
            this.teachername = teachername;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getEventname() {
            return eventname;
        }

        public void setEventname(String eventname) {
            this.eventname = eventname;
        }

        private String description;
    }

