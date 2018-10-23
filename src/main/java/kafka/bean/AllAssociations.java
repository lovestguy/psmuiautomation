package kafka.bean;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllAssociations {

@SerializedName("toAssociations")
@Expose
private List<ToAssociation> toAssociations = null;
@SerializedName("fromAssociations")
@Expose
private List<Object> fromAssociations = null;

public List<ToAssociation> getToAssociations() {
return toAssociations;
}

public void setToAssociations(List<ToAssociation> toAssociations) {
this.toAssociations = toAssociations;
}

public List<Object> getFromAssociations() {
return fromAssociations;
}

public void setFromAssociations(List<Object> fromAssociations) {
this.fromAssociations = fromAssociations;
}

}


