
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
"activityLogId",
"serviceName",
"serviceType",
"identityDomain",
"serviceId",
"jobId",
"computeSiteName",
"startDate",
"endDate",
"status",
"operationId",
"operationType",
"summaryMessage",
"authDomain",
"authUser",
"initiatedBy",
"messages"
})
public class KafkaJobStatus {

@JsonProperty("activityLogId")
private Integer activityLogId;
@JsonProperty("serviceName")
private String serviceName;
@JsonProperty("serviceType")
private String serviceType;
@JsonProperty("identityDomain")
private String identityDomain;
@JsonProperty("serviceId")
private Integer serviceId;
@JsonProperty("jobId")
private Integer jobId;
@JsonProperty("computeSiteName")
private String computeSiteName;
@JsonProperty("startDate")
private String startDate;
@JsonProperty("endDate")
private String endDate;
@JsonProperty("status")
private String status;
@JsonProperty("operationId")
private Integer operationId;
@JsonProperty("operationType")
private String operationType;
@JsonProperty("summaryMessage")
private String summaryMessage;
@JsonProperty("authDomain")
private String authDomain;
@JsonProperty("authUser")
private String authUser;
@JsonProperty("initiatedBy")
private String initiatedBy;
@JsonProperty("messages")
private List<Message> messages = null;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("activityLogId")
public Integer getActivityLogId() {
return activityLogId;
}

@JsonProperty("activityLogId")
public void setActivityLogId(Integer activityLogId) {
this.activityLogId = activityLogId;
}

@JsonProperty("serviceName")
public String getServiceName() {
return serviceName;
}

@JsonProperty("serviceName")
public void setServiceName(String serviceName) {
this.serviceName = serviceName;
}

@JsonProperty("serviceType")
public String getServiceType() {
return serviceType;
}

@JsonProperty("serviceType")
public void setServiceType(String serviceType) {
this.serviceType = serviceType;
}

@JsonProperty("identityDomain")
public String getIdentityDomain() {
return identityDomain;
}

@JsonProperty("identityDomain")
public void setIdentityDomain(String identityDomain) {
this.identityDomain = identityDomain;
}

@JsonProperty("serviceId")
public Integer getServiceId() {
return serviceId;
}

@JsonProperty("serviceId")
public void setServiceId(Integer serviceId) {
this.serviceId = serviceId;
}

@JsonProperty("jobId")
public Integer getJobId() {
return jobId;
}

@JsonProperty("jobId")
public void setJobId(Integer jobId) {
this.jobId = jobId;
}

@JsonProperty("computeSiteName")
public String getComputeSiteName() {
return computeSiteName;
}

@JsonProperty("computeSiteName")
public void setComputeSiteName(String computeSiteName) {
this.computeSiteName = computeSiteName;
}

@JsonProperty("startDate")
public String getStartDate() {
return startDate;
}

@JsonProperty("startDate")
public void setStartDate(String startDate) {
this.startDate = startDate;
}

@JsonProperty("endDate")
public String getEndDate() {
return endDate;
}

@JsonProperty("endDate")
public void setEndDate(String endDate) {
this.endDate = endDate;
}

@JsonProperty("status")
public String getStatus() {
return status;
}

@JsonProperty("status")
public void setStatus(String status) {
this.status = status;
}

@JsonProperty("operationId")
public Integer getOperationId() {
return operationId;
}

@JsonProperty("operationId")
public void setOperationId(Integer operationId) {
this.operationId = operationId;
}

@JsonProperty("operationType")
public String getOperationType() {
return operationType;
}

@JsonProperty("operationType")
public void setOperationType(String operationType) {
this.operationType = operationType;
}

@JsonProperty("summaryMessage")
public String getSummaryMessage() {
return summaryMessage;
}

@JsonProperty("summaryMessage")
public void setSummaryMessage(String summaryMessage) {
this.summaryMessage = summaryMessage;
}

@JsonProperty("authDomain")
public String getAuthDomain() {
return authDomain;
}

@JsonProperty("authDomain")
public void setAuthDomain(String authDomain) {
this.authDomain = authDomain;
}

@JsonProperty("authUser")
public String getAuthUser() {
return authUser;
}

@JsonProperty("authUser")
public void setAuthUser(String authUser) {
this.authUser = authUser;
}

@JsonProperty("initiatedBy")
public String getInitiatedBy() {
return initiatedBy;
}

@JsonProperty("initiatedBy")
public void setInitiatedBy(String initiatedBy) {
this.initiatedBy = initiatedBy;
}

@JsonProperty("messages")
public List<Message> getMessages() {
return messages;
}

@JsonProperty("messages")
public void setMessages(List<Message> messages) {
this.messages = messages;
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
