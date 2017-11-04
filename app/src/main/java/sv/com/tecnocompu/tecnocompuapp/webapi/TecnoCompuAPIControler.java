package sv.com.tecnocompu.tecnocompuapp.webapi;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TecnoCompuAPIControler {

    public static TecnoCompuAPI getClient() {

        String BASEURL = "http://192.168.0.18/api/";
        String ALT_URL = "https://gitlab.com/snippets/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ALT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        return retrofit.create(TecnoCompuAPI.class);
    }
}
