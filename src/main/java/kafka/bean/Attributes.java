package kafka.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attributes {

@SerializedName("numPartitions")
@Expose
private NumPartitions numPartitions;
@SerializedName("connectUri")
@Expose
private ConnectUri connectUri;
@SerializedName("topic")
@Expose
private Topic topic;
@SerializedName("logCleanupPolicy")
@Expose
private LogCleanupPolicy logCleanupPolicy;
@SerializedName("topicMetricsUri")
@Expose
private TopicMetricsUri topicMetricsUri;
@SerializedName("retentionPeriod")
@Expose
private RetentionPeriod retentionPeriod;
@SerializedName("restProxyUri")
@Expose
private RestProxyUri restProxyUri;

public NumPartitions getNumPartitions() {
return numPartitions;
}

public void setNumPartitions(NumPartitions numPartitions) {
this.numPartitions = numPartitions;
}

public ConnectUri getConnectUri() {
return connectUri;
}

public void setConnectUri(ConnectUri connectUri) {
this.connectUri = connectUri;
}

public Topic getTopic() {
return topic;
}

public void setTopic(Topic topic) {
this.topic = topic;
}

public LogCleanupPolicy getLogCleanupPolicy() {
return logCleanupPolicy;
}

public void setLogCleanupPolicy(LogCleanupPolicy logCleanupPolicy) {
this.logCleanupPolicy = logCleanupPolicy;
}

public TopicMetricsUri getTopicMetricsUri() {
return topicMetricsUri;
}

public void setTopicMetricsUri(TopicMetricsUri topicMetricsUri) {
this.topicMetricsUri = topicMetricsUri;
}

public RetentionPeriod getRetentionPeriod() {
return retentionPeriod;
}

public void setRetentionPeriod(RetentionPeriod retentionPeriod) {
this.retentionPeriod = retentionPeriod;
}

public RestProxyUri getRestProxyUri() {
return restProxyUri;
}

public void setRestProxyUri(RestProxyUri restProxyUri) {
this.restProxyUri = restProxyUri;
}

}
