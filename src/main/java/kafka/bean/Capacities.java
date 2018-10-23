package kafka.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Capacities {

@SerializedName("allocated")
@Expose
private Allocated allocated;
@SerializedName("blocked")
@Expose
private Blocked blocked;

public Allocated getAllocated() {
return allocated;
}

public void setAllocated(Allocated allocated) {
this.allocated = allocated;
}

public Blocked getBlocked() {
return blocked;
}

public void setBlocked(Blocked blocked) {
this.blocked = blocked;
}

}