package kafka.bean;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"appID",
"appDisplayName",
"appName",
"appURL"
})
public class Customer {

@JsonProperty("appID")
private String appID;
@JsonProperty("appDisplayName")
private String appDisplayName;
@JsonProperty("appName")
private String appName;
@JsonProperty("appURL")
private String appURL;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("appID")
public String getAppID() {
return appID;
}

@JsonProperty("appID")
public void setAppID(String appID) {
this.appID = appID;
}

@JsonProperty("appDisplayName")
public String getAppDisplayName() {
return appDisplayName;
}

@JsonProperty("appDisplayName")
public void setAppDisplayName(String appDisplayName) {
this.appDisplayName = appDisplayName;
}

@JsonProperty("appName")
public String getAppName() {
return appName;
}

@JsonProperty("appName")
public void setAppName(String appName) {
this.appName = appName;
}

@JsonProperty("appURL")
public String getAppURL() {
return appURL;
}

@JsonProperty("appURL")
public void setAppURL(String appURL) {
this.appURL = appURL;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}