package com.nexus.nurseryteacher.model;

import android.os.Parcelable;

/**
 * Created by Sara on 7/10/2017.
 */

public class TimeLineModel  {
    private String activityName;
    private String description;

    public TimeLineModel(String activityName, String description) {
        this.activityName = activityName;
        this.description = description;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
