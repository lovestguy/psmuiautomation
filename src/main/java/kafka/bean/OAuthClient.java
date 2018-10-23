package kafka.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"clientID",
"clientSecret",
"grantedRoles",
"trustScope"
})
public class OAuthClient {

@JsonProperty("clientID")
private String clientID;
@JsonProperty("clientSecret")
private String clientSecret;
@JsonProperty("trustScope")
private String trustScope;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("clientID")
public String getClientID() {
return clientID;
}

@JsonProperty("clientID")
public void setClientID(String clientID) {
this.clientID = clientID;
}

@JsonProperty("clientSecret")
public String getClientSecret() {
return clientSecret;
}

@JsonProperty("clientSecret")
public void setClientSecret(String clientSecret) {
this.clientSecret = clientSecret;
}



@JsonProperty("trustScope")
public String getTrustScope() {
return trustScope;
}

@JsonProperty("trustScope")
public void setTrustScope(String trustScope) {
this.trustScope = trustScope;
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