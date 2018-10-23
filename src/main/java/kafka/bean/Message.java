package kafka.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Message {

@SerializedName("activityDate")
@Expose
private String activityDate;
@SerializedName("message")
@Expose
private String message;

public String getActivityDate() {
return activityDate;
}

public void setActivityDate(String activityDate) {
this.activityDate = activityDate;
}

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

}
