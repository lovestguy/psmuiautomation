package kafka.bean;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActivityLog {

@SerializedName("activityLogId")
@Expose
private Integer activityLogId;
@SerializedName("serviceName")
@Expose
private String serviceName;
@SerializedName("serviceType")
@Expose
private String serviceType;
@SerializedName("identityDomain")
@Expose
private String identityDomain;
@SerializedName("serviceId")
@Expose
private Integer serviceId;
@SerializedName("jobId")
@Expose
private Integer jobId;
@SerializedName("computeSiteName")
@Expose
private String computeSiteName;
@SerializedName("startDate")
@Expose
private String startDate;
@SerializedName("endDate")
@Expose
private String endDate;
@SerializedName("status")
@Expose
private String status;
@SerializedName("operationId")
@Expose
private Integer operationId;
@SerializedName("operationType")
@Expose
private String operationType;
@SerializedName("summaryMessage")
@Expose
private String summaryMessage;
@SerializedName("authDomain")
@Expose
private String authDomain;
@SerializedName("authUser")
@Expose
private String authUser;
@SerializedName("initiatedBy")
@Expose
private String initiatedBy;
@SerializedName("messages")
@Expose
private List<Message> messages = null;

public Integer getActivityLogId() {
return activityLogId;
}

public void setActivityLogId(Integer activityLogId) {
this.activityLogId = activityLogId;
}

public String getServiceName() {
return serviceName;
}

public void setServiceName(String serviceName) {
this.serviceName = serviceName;
}

public String getServiceType() {
return serviceType;
}

public void setServiceType(String serviceType) {
this.serviceType = serviceType;
}

public String getIdentityDomain() {
return identityDomain;
}

public void setIdentityDomain(String identityDomain) {
this.identityDomain = identityDomain;
}

public Integer getServiceId() {
return serviceId;
}

public void setServiceId(Integer serviceId) {
this.serviceId = serviceId;
}

public Integer getJobId() {
return jobId;
}

public void setJobId(Integer jobId) {
this.jobId = jobId;
}

public String getComputeSiteName() {
return computeSiteName;
}

public void setComputeSiteName(String computeSiteName) {
this.computeSiteName = computeSiteName;
}

public String getStartDate() {
return startDate;
}

public void setStartDate(String startDate) {
this.startDate = startDate;
}

public String getEndDate() {
return endDate;
}

public void setEndDate(String endDate) {
this.endDate = endDate;
}

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

public Integer getOperationId() {
return operationId;
}

public void setOperationId(Integer operationId) {
this.operationId = operationId;
}

public String getOperationType() {
return operationType;
}

public void setOperationType(String operationType) {
this.operationType = operationType;
}

public String getSummaryMessage() {
return summaryMessage;
}

public void setSummaryMessage(String summaryMessage) {
this.summaryMessage = summaryMessage;
}

public String getAuthDomain() {
return authDomain;
}

public void setAuthDomain(String authDomain) {
this.authDomain = authDomain;
}

public String getAuthUser() {
return authUser;
}

public void setAuthUser(String authUser) {
this.authUser = authUser;
}

public String getInitiatedBy() {
return initiatedBy;
}

public void setInitiatedBy(String initiatedBy) {
this.initiatedBy = initiatedBy;
}

public List<Message> getMessages() {
return messages;
}

public void setMessages(List<Message> messages) {
this.messages = messages;
}

}