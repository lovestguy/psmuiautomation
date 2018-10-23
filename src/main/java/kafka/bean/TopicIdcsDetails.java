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
"opcTenantCertChain",
"idcsArtifacts",
"idcsAppDisplayName",
"opcServiceInstanceGUID",
"opcTenantPublicKey",
"idcsServiceURI",
"idcsAppId",
"idcsAppName",
"idcsAppURL",
"webTierPolicyJson",
"selfUpdateURL"
})
public class TopicIdcsDetails {

@Override
	public String toString() {
		return "TopicIdcsDetails [opcTenantCertChain=" + opcTenantCertChain + ", idcsArtifacts=" + idcsArtifacts
				+ ", idcsAppDisplayName=" + idcsAppDisplayName + ", opcServiceInstanceGUID=" + opcServiceInstanceGUID
				+ ", opcTenantPublicKey=" + opcTenantPublicKey + ", idcsServiceURI=" + idcsServiceURI + ", idcsAppId="
				+ idcsAppId + ", idcsAppName=" + idcsAppName + ", idcsAppURL=" + idcsAppURL + ", webTierPolicyJson="
				+ webTierPolicyJson + ", selfUpdateURL=" + selfUpdateURL + ", additionalProperties="
				+ additionalProperties + "]";
	}

@JsonProperty("opcTenantCertChain")
private List<String> opcTenantCertChain = null;
@JsonProperty("idcsArtifacts")
private IdcsArtifacts idcsArtifacts;
@JsonProperty("idcsAppDisplayName")
private String idcsAppDisplayName;
@JsonProperty("opcServiceInstanceGUID")
private String opcServiceInstanceGUID;
@JsonProperty("opcTenantPublicKey")
private String opcTenantPublicKey;
@JsonProperty("idcsServiceURI")
private String idcsServiceURI;
@JsonProperty("idcsAppId")
private String idcsAppId;
@JsonProperty("idcsAppName")
private String idcsAppName;
@JsonProperty("idcsAppURL")
private String idcsAppURL;
@JsonProperty("webTierPolicyJson")
private String webTierPolicyJson;
@JsonProperty("selfUpdateURL")
private String selfUpdateURL;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("opcTenantCertChain")
public List<String> getOpcTenantCertChain() {
return opcTenantCertChain;
}

@JsonProperty("opcTenantCertChain")
public void setOpcTenantCertChain(List<String> opcTenantCertChain) {
this.opcTenantCertChain = opcTenantCertChain;
}

@JsonProperty("idcsArtifacts")
public IdcsArtifacts getIdcsArtifacts() {
return idcsArtifacts;
}

@JsonProperty("idcsArtifacts")
public void setIdcsArtifacts(IdcsArtifacts idcsArtifacts) {
this.idcsArtifacts = idcsArtifacts;
}

@JsonProperty("idcsAppDisplayName")
public String getIdcsAppDisplayName() {
return idcsAppDisplayName;
}

@JsonProperty("idcsAppDisplayName")
public void setIdcsAppDisplayName(String idcsAppDisplayName) {
this.idcsAppDisplayName = idcsAppDisplayName;
}

@JsonProperty("opcServiceInstanceGUID")
public String getOpcServiceInstanceGUID() {
return opcServiceInstanceGUID;
}

@JsonProperty("opcServiceInstanceGUID")
public void setOpcServiceInstanceGUID(String opcServiceInstanceGUID) {
this.opcServiceInstanceGUID = opcServiceInstanceGUID;
}

@JsonProperty("opcTenantPublicKey")
public String getOpcTenantPublicKey() {
return opcTenantPublicKey;
}

@JsonProperty("opcTenantPublicKey")
public void setOpcTenantPublicKey(String opcTenantPublicKey) {
this.opcTenantPublicKey = opcTenantPublicKey;
}

@JsonProperty("idcsServiceURI")
public String getIdcsServiceURI() {
return idcsServiceURI;
}

@JsonProperty("idcsServiceURI")
public void setIdcsServiceURI(String idcsServiceURI) {
this.idcsServiceURI = idcsServiceURI;
}

@JsonProperty("idcsAppId")
public String getIdcsAppId() {
return idcsAppId;
}

@JsonProperty("idcsAppId")
public void setIdcsAppId(String idcsAppId) {
this.idcsAppId = idcsAppId;
}

@JsonProperty("idcsAppName")
public String getIdcsAppName() {
return idcsAppName;
}

@JsonProperty("idcsAppName")
public void setIdcsAppName(String idcsAppName) {
this.idcsAppName = idcsAppName;
}

@JsonProperty("idcsAppURL")
public String getIdcsAppURL() {
return idcsAppURL;
}

@JsonProperty("idcsAppURL")
public void setIdcsAppURL(String idcsAppURL) {
this.idcsAppURL = idcsAppURL;
}

@JsonProperty("webTierPolicyJson")
public String getWebTierPolicyJson() {
return webTierPolicyJson;
}

@JsonProperty("webTierPolicyJson")
public void setWebTierPolicyJson(String webTierPolicyJson) {
this.webTierPolicyJson = webTierPolicyJson;
}

@JsonProperty("selfUpdateURL")
public String getSelfUpdateURL() {
return selfUpdateURL;
}

@JsonProperty("selfUpdateURL")
public void setSelfUpdateURL(String selfUpdateURL) {
this.selfUpdateURL = selfUpdateURL;
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