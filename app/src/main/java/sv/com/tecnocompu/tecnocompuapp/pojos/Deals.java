package sv.com.tecnocompu.tecnocompuapp.pojos;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Deals {

    @SerializedName("deals")
    @Expose
    private List<Deal> deals = null;

    public List<Deal> getDeals() {
        return deals;
    }

    public void setDeals(List<Deal> deals) {
        this.deals = deals;
    }

}