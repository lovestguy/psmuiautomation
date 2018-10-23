package kafka.idcs;

public class IdcsTopicDetails {
	
	private String client_appid;
	private String client_secret;
	private String clientid;
	private String bearertoken;
	private String topicurl;
    private String basicauth;
    private String scope;
    private String searchbearertoken;
    
 public String getClient_appid() {
	return client_appid;
}
public String getClient_secret() {
	return client_secret;
}
public String getClientid() {
	return clientid;
}

public void setClient_appid(String client_appid) {
	this.client_appid = client_appid;
}
public void setClient_secret(String client_secret) {
	this.client_secret = client_secret;
}
public void setClientid(String clientid) {
	this.clientid = clientid;
}

public String getBearertoken() {
	return bearertoken;
}
public String getTopicurl() {
	return topicurl;
}
public void setBearertoken(String bearertoken) {
	this.bearertoken = bearertoken;
}
public void setTopicurl(String topicurl) {
	this.topicurl = topicurl;
}
public String getBasicauth() {
	return basicauth;
}
public String getScope() {
	return scope;
}
public void setBasicauth(String basicauth) {
	this.basicauth = basicauth;
}
public void setScope(String scope) {
	this.scope = scope;
}
public String getSearchbearertoken() {
	return searchbearertoken;
}
public void setSearchbearertoken(String searchbearertoken) {
	this.searchbearertoken = searchbearertoken;
}
@Override
public String toString() {
	return "IdcsTopicDetails [client_appid=" + client_appid + ", client_secret=" + client_secret + ", clientid="
			+ clientid + ", bearertoken=" + bearertoken + ", topicurl=" + topicurl + ", basicauth=" + basicauth
			+ ", scope=" + scope + "]";
}

}
