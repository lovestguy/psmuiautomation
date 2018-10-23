package kafka.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LogCleanupPolicy {

@SerializedName("displayName")
@Expose
private String displayName;
@SerializedName("type")
@Expose
private String type;
@SerializedName("value")
@Expose
private String value;
@SerializedName("displayValue")
@Expose
private String displayValue;
@SerializedName("isKeyBinding")
@Expose
private Boolean isKeyBinding;

public String getDisplayName() {
return displayName;
}

public void setDisplayName(String displayName) {
this.displayName = displayName;
}

public String getType() {
return type;
}

public void setType(String type) {
this.type = type;
}

public String getValue() {
return value;
}

public void setValue(String value) {
this.value = value;
}

public String getDisplayValue() {
return displayValue;
}

public void setDisplayValue(String displayValue) {
this.displayValue = displayValue;
}

public Boolean getIsKeyBinding() {
return isKeyBinding;
}

public void setIsKeyBinding(Boolean isKeyBinding) {
this.isKeyBinding = isKeyBinding;
}

}
