package sv.com.tecnocompu.tecnocompuapp.webapi;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import sv.com.tecnocompu.tecnocompuapp.pojos.Deal;
import sv.com.tecnocompu.tecnocompuapp.pojos.Deals;
import sv.com.tecnocompu.tecnocompuapp.pojos.Product;
import sv.com.tecnocompu.tecnocompuapp.pojos.Products;

public interface TecnoCompuAPI {
    @GET("deals")
    Call<Deals> getDeals();

    @GET("products")
    Call<Products> getProducts();
}
