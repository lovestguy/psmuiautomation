
package kafka.bean;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopicDetails {

@SerializedName("serviceId")
@Expose
private Integer serviceId;
@SerializedName("serviceUuid")
@Expose
private String serviceUuid;
@SerializedName("serviceLogicalUuid")
@Expose
private String serviceLogicalUuid;
@SerializedName("serviceName")
@Expose
private String serviceName;
@SerializedName("serviceType")
@Expose
private String serviceType;
@SerializedName("domainName")
@Expose
private String domainName;
@SerializedName("serviceVersion")
@Expose
private String serviceVersion;
@SerializedName("releaseVersion")
@Expose
private String releaseVersion;
@SerializedName("baseReleaseVersion")
@Expose
private String baseReleaseVersion;
@SerializedName("metaVersion")
@Expose
private String metaVersion;
@SerializedName("originalMetaVersion")
@Expose
private String originalMetaVersion;
@SerializedName("serviceDescription")
@Expose
private String serviceDescription;
@SerializedName("serviceLevel")
@Expose
private String serviceLevel;
@SerializedName("subscription")
@Expose
private String subscription;
@SerializedName("meteringFrequency")
@Expose
private String meteringFrequency;
@SerializedName("state")
@Expose
private String state;
@SerializedName("serviceStateDisplayName")
@Expose
private String serviceStateDisplayName;
@SerializedName("clone")
@Expose
private Boolean clone;
@SerializedName("creator")
@Expose
private String creator;
@SerializedName("creationDate")
@Expose
private String creationDate;
@SerializedName("serviceEntitlementId")
@Expose
private String serviceEntitlementId;
@SerializedName("isBYOL")
@Expose
private Boolean isBYOL;
@SerializedName("isSharedManaged")
@Expose
private Boolean isSharedManaged;
@SerializedName("isNonSharedmanaged")
@Expose
private Boolean isNonSharedmanaged;
@SerializedName("isDefaultManaged")
@Expose
private Boolean isDefaultManaged;
@SerializedName("isManaged")
@Expose
private Boolean isManaged;
@SerializedName("isOAuthForStorageConfigured")
@Expose
private Boolean isOAuthForStorageConfigured;
@SerializedName("iaasProvider")
@Expose
private String iaasProvider;
@SerializedName("capacities")
@Expose
private Capacities capacities;
@SerializedName("idcs")
@Expose
private Idcs idcs;
@SerializedName("attributes")
@Expose
private Attributes attributes;
@SerializedName("tags")
@Expose
private Tags tags;
@SerializedName("totalSSDStorage")
@Expose
private Integer totalSSDStorage;
@SerializedName("isMultipleSite")
@Expose
private Boolean isMultipleSite;
@SerializedName("activityLogs")
@Expose
private List<ActivityLog> activityLogs = null;
@SerializedName("layeringMode")
@Expose
private String layeringMode;
@SerializedName("serviceLevelDisplayName")
@Expose
private String serviceLevelDisplayName;
@SerializedName("meteringFrequencyDisplayName")
@Expose
private String meteringFrequencyDisplayName;
@SerializedName("topic")
@Expose
private String topic;
@SerializedName("connectUri")
@Expose
private String connectUri;
@SerializedName("retentionPeriod")
@Expose
private String retentionPeriod;
@SerializedName("evaluateZoneExpression")
@Expose
private String evaluateZoneExpression;
@SerializedName("numPartitions")
@Expose
private String numPartitions;
@SerializedName("topicMetricsUri")
@Expose
private String topicMetricsUri;
@SerializedName("replicationFactor")
@Expose
private String replicationFactor;
@SerializedName("logCleanupPolicy")
@Expose
private String logCleanupPolicy;
@SerializedName("restProxyUri")
@Expose
private String restProxyUri;
@SerializedName("totalSharedStorage")
@Expose
private Integer totalSharedStorage;
@SerializedName("allAssociations")
@Expose
private AllAssociations allAssociations;
@SerializedName("computeSiteName")
@Expose
private String computeSiteName;

public Integer getServiceId() {
return serviceId;
}

public void setServiceId(Integer serviceId) {
this.serviceId = serviceId;
}

public String getServiceUuid() {
return serviceUuid;
}

public void setServiceUuid(String serviceUuid) {
this.serviceUuid = serviceUuid;
}

public String getServiceLogicalUuid() {
return serviceLogicalUuid;
}

public void setServiceLogicalUuid(String serviceLogicalUuid) {
this.serviceLogicalUuid = serviceLogicalUuid;
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

public String getDomainName() {
return domainName;
}

public void setDomainName(String domainName) {
this.domainName = domainName;
}

public String getServiceVersion() {
return serviceVersion;
}

public void setServiceVersion(String serviceVersion) {
this.serviceVersion = serviceVersion;
}

public String getReleaseVersion() {
return releaseVersion;
}

public void setReleaseVersion(String releaseVersion) {
this.releaseVersion = releaseVersion;
}

public String getBaseReleaseVersion() {
return baseReleaseVersion;
}

public void setBaseReleaseVersion(String baseReleaseVersion) {
this.baseReleaseVersion = baseReleaseVersion;
}

public String getMetaVersion() {
return metaVersion;
}

public void setMetaVersion(String metaVersion) {
this.metaVersion = metaVersion;
}

public String getOriginalMetaVersion() {
return originalMetaVersion;
}

public void setOriginalMetaVersion(String originalMetaVersion) {
this.originalMetaVersion = originalMetaVersion;
}

public String getServiceDescription() {
return serviceDescription;
}

public void setServiceDescription(String serviceDescription) {
this.serviceDescription = serviceDescription;
}

public String getServiceLevel() {
return serviceLevel;
}

public void setServiceLevel(String serviceLevel) {
this.serviceLevel = serviceLevel;
}

public String getSubscription() {
return subscription;
}

public void setSubscription(String subscription) {
this.subscription = subscription;
}

public String getMeteringFrequency() {
return meteringFrequency;
}

public void setMeteringFrequency(String meteringFrequency) {
this.meteringFrequency = meteringFrequency;
}

public String getState() {
return state;
}

public void setState(String state) {
this.state = state;
}

public String getServiceStateDisplayName() {
return serviceStateDisplayName;
}

public void setServiceStateDisplayName(String serviceStateDisplayName) {
this.serviceStateDisplayName = serviceStateDisplayName;
}

public Boolean getClone() {
return clone;
}

public void setClone(Boolean clone) {
this.clone = clone;
}

public String getCreator() {
return creator;
}

public void setCreator(String creator) {
this.creator = creator;
}

public String getCreationDate() {
return creationDate;
}

public void setCreationDate(String creationDate) {
this.creationDate = creationDate;
}

public String getServiceEntitlementId() {
return serviceEntitlementId;
}

public void setServiceEntitlementId(String serviceEntitlementId) {
this.serviceEntitlementId = serviceEntitlementId;
}

public Boolean getIsBYOL() {
return isBYOL;
}

public void setIsBYOL(Boolean isBYOL) {
this.isBYOL = isBYOL;
}

public Boolean getIsSharedManaged() {
return isSharedManaged;
}

public void setIsSharedManaged(Boolean isSharedManaged) {
this.isSharedManaged = isSharedManaged;
}

public Boolean getIsNonSharedmanaged() {
return isNonSharedmanaged;
}

public void setIsNonSharedmanaged(Boolean isNonSharedmanaged) {
this.isNonSharedmanaged = isNonSharedmanaged;
}

public Boolean getIsDefaultManaged() {
return isDefaultManaged;
}

public void setIsDefaultManaged(Boolean isDefaultManaged) {
this.isDefaultManaged = isDefaultManaged;
}

public Boolean getIsManaged() {
return isManaged;
}

public void setIsManaged(Boolean isManaged) {
this.isManaged = isManaged;
}

public Boolean getIsOAuthForStorageConfigured() {
return isOAuthForStorageConfigured;
}

public void setIsOAuthForStorageConfigured(Boolean isOAuthForStorageConfigured) {
this.isOAuthForStorageConfigured = isOAuthForStorageConfigured;
}

public String getIaasProvider() {
return iaasProvider;
}

public void setIaasProvider(String iaasProvider) {
this.iaasProvider = iaasProvider;
}

public Capacities getCapacities() {
return capacities;
}

public void setCapacities(Capacities capacities) {
this.capacities = capacities;
}

public Attributes getAttributes() {
return attributes;
}

public void setAttributes(Attributes attributes) {
this.attributes = attributes;
}

public Tags getTags() {
return tags;
}

public void setTags(Tags tags) {
this.tags = tags;
}

public Integer getTotalSSDStorage() {
return totalSSDStorage;
}

public void setTotalSSDStorage(Integer totalSSDStorage) {
this.totalSSDStorage = totalSSDStorage;
}

public Boolean getIsMultipleSite() {
return isMultipleSite;
}

public void setIsMultipleSite(Boolean isMultipleSite) {
this.isMultipleSite = isMultipleSite;
}

public List<ActivityLog> getActivityLogs() {
return activityLogs;
}

public void setActivityLogs(List<ActivityLog> activityLogs) {
this.activityLogs = activityLogs;
}

public String getLayeringMode() {
return layeringMode;
}

public void setLayeringMode(String layeringMode) {
this.layeringMode = layeringMode;
}

public String getServiceLevelDisplayName() {
return serviceLevelDisplayName;
}

public void setServiceLevelDisplayName(String serviceLevelDisplayName) {
this.serviceLevelDisplayName = serviceLevelDisplayName;
}

public String getMeteringFrequencyDisplayName() {
return meteringFrequencyDisplayName;
}

public void setMeteringFrequencyDisplayName(String meteringFrequencyDisplayName) {
this.meteringFrequencyDisplayName = meteringFrequencyDisplayName;
}

public String getTopic() {
return topic;
}

public void setTopic(String topic) {
this.topic = topic;
}

public String getConnectUri() {
return connectUri;
}

public void setConnectUri(String connectUri) {
this.connectUri = connectUri;
}

public String getRetentionPeriod() {
return retentionPeriod;
}

public void setRetentionPeriod(String retentionPeriod) {
this.retentionPeriod = retentionPeriod;
}

public String getEvaluateZoneExpression() {
return evaluateZoneExpression;
}

public void setEvaluateZoneExpression(String evaluateZoneExpression) {
this.evaluateZoneExpression = evaluateZoneExpression;
}

public String getNumPartitions() {
return numPartitions;
}

public void setNumPartitions(String numPartitions) {
this.numPartitions = numPartitions;
}

public String getTopicMetricsUri() {
return topicMetricsUri;
}

public void setTopicMetricsUri(String topicMetricsUri) {
this.topicMetricsUri = topicMetricsUri;
}

public String getReplicationFactor() {
return replicationFactor;
}

public void setReplicationFactor(String replicationFactor) {
this.replicationFactor = replicationFactor;
}

public String getLogCleanupPolicy() {
return logCleanupPolicy;
}

public void setLogCleanupPolicy(String logCleanupPolicy) {
this.logCleanupPolicy = logCleanupPolicy;
}

public String getRestProxyUri() {
return restProxyUri;
}

public void setRestProxyUri(String restProxyUri) {
this.restProxyUri = restProxyUri;
}

public Integer getTotalSharedStorage() {
return totalSharedStorage;
}

public void setTotalSharedStorage(Integer totalSharedStorage) {
this.totalSharedStorage = totalSharedStorage;
}

public AllAssociations getAllAssociations() {
return allAssociations;
}

public void setAllAssociations(AllAssociations allAssociations) {
this.allAssociations = allAssociations;
}

public String getComputeSiteName() {
return computeSiteName;
}

public void setComputeSiteName(String computeSiteName) {
this.computeSiteName = computeSiteName;
}

public Idcs getIdcs() {
	return idcs;
}

public void setIdcs(Idcs idcs) {
	this.idcs = idcs;
}

}