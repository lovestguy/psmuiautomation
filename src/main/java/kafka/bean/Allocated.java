package kafka.bean;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Allocated {

@SerializedName("numPartitions")
@Expose
private Integer numPartitions;

public Integer getNumPartitions() {
return numPartitions;
}

public void setNumPartitions(Integer numPartitions) {
this.numPartitions = numPartitions;
}

}