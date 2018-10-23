package kafka.bean;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tags {

@SerializedName("items")
@Expose
private List<Object> items = null;
@SerializedName("totalResults")
@Expose
private Integer totalResults;
@SerializedName("hasMore")
@Expose
private Boolean hasMore;

public List<Object> getItems() {
return items;
}

public void setItems(List<Object> items) {
this.items = items;
}

public Integer getTotalResults() {
return totalResults;
}

public void setTotalResults(Integer totalResults) {
this.totalResults = totalResults;
}

public Boolean getHasMore() {
return hasMore;
}

public void setHasMore(Boolean hasMore) {
this.hasMore = hasMore;
}

}
