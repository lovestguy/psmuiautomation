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
"OAuthClient",
"OAuthResource",
"Roles"
})
public class IdcsArtifacts {

@JsonProperty("OAuthClient")
private OAuthClient oAuthClient;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("OAuthClient")
public OAuthClient getOAuthClient() {
return oAuthClient;
}

@JsonProperty("OAuthClient")
public void setOAuthClient(OAuthClient oAuthClient) {
this.oAuthClient = oAuthClient;
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
