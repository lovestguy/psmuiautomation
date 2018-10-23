package kafka.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ToAssociation {

@SerializedName("associationId")
@Expose
private Integer associationId;
@SerializedName("displayName")
@Expose
private String displayName;
@SerializedName("description")
@Expose
private String description;
@SerializedName("associationType")
@Expose
private String associationType;
@SerializedName("associationName")
@Expose
private String associationName;
@SerializedName("associationState")
@Expose
private String associationState;
@SerializedName("assocStatusMessage")
@Expose
private String assocStatusMessage;
@SerializedName("isRequired")
@Expose
private Boolean isRequired;
@SerializedName("creationDate")
@Expose
private String creationDate;
@SerializedName("domain")
@Expose
private String domain;
@SerializedName("destServiceType")
@Expose
private String destServiceType;
@SerializedName("destServiceName")
@Expose
private String destServiceName;
@SerializedName("serviceStatus")
@Expose
private String serviceStatus;
@SerializedName("serviceStateDisplayName")
@Expose
private String serviceStateDisplayName;
@SerializedName("edition")
@Expose
private String edition;
@SerializedName("version")
@Expose
private String version;
@SerializedName("level")
@Expose
private String level;
@SerializedName("ocpu")
@Expose
private Integer ocpu;
@SerializedName("associationStateDisplayName")
@Expose
private String associationStateDisplayName;
@SerializedName("assocTypeDisplayName")
@Expose
private String assocTypeDisplayName;
@SerializedName("assocTypeDescription")
@Expose
private String assocTypeDescription;

public Integer getAssociationId() {
return associationId;
}

public void setAssociationId(Integer associationId) {
this.associationId = associationId;
}

public String getDisplayName() {
return displayName;
}

public void setDisplayName(String displayName) {
this.displayName = displayName;
}

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

public String getAssociationType() {
return associationType;
}

public void setAssociationType(String associationType) {
this.associationType = associationType;
}

public String getAssociationName() {
return associationName;
}

public void setAssociationName(String associationName) {
this.associationName = associationName;
}

public String getAssociationState() {
return associationState;
}

public void setAssociationState(String associationState) {
this.associationState = associationState;
}

public String getAssocStatusMessage() {
return assocStatusMessage;
}

public void setAssocStatusMessage(String assocStatusMessage) {
this.assocStatusMessage = assocStatusMessage;
}

public Boolean getIsRequired() {
return isRequired;
}

public void setIsRequired(Boolean isRequired) {
this.isRequired = isRequired;
}

public String getCreationDate() {
return creationDate;
}

public void setCreationDate(String creationDate) {
this.creationDate = creationDate;
}

public String getDomain() {
return domain;
}

public void setDomain(String domain) {
this.domain = domain;
}

public String getDestServiceType() {
return destServiceType;
}

public void setDestServiceType(String destServiceType) {
this.destServiceType = destServiceType;
}

public String getDestServiceName() {
return destServiceName;
}

public void setDestServiceName(String destServiceName) {
this.destServiceName = destServiceName;
}

public String getServiceStatus() {
return serviceStatus;
}

public void setServiceStatus(String serviceStatus) {
this.serviceStatus = serviceStatus;
}

public String getServiceStateDisplayName() {
return serviceStateDisplayName;
}

public void setServiceStateDisplayName(String serviceStateDisplayName) {
this.serviceStateDisplayName = serviceStateDisplayName;
}

public String getEdition() {
return edition;
}

public void setEdition(String edition) {
this.edition = edition;
}

public String getVersion() {
return version;
}

public void setVersion(String version) {
this.version = version;
}

public String getLevel() {
return level;
}

public void setLevel(String level) {
this.level = level;
}

public Integer getOcpu() {
return ocpu;
}

public void setOcpu(Integer ocpu) {
this.ocpu = ocpu;
}

public String getAssociationStateDisplayName() {
return associationStateDisplayName;
}

public void setAssociationStateDisplayName(String associationStateDisplayName) {
this.associationStateDisplayName = associationStateDisplayName;
}

public String getAssocTypeDisplayName() {
return assocTypeDisplayName;
}

public void setAssocTypeDisplayName(String assocTypeDisplayName) {
this.assocTypeDisplayName = assocTypeDisplayName;
}

public String getAssocTypeDescription() {
return assocTypeDescription;
}

public void setAssocTypeDescription(String assocTypeDescription) {
this.assocTypeDescription = assocTypeDescription;
}

}
