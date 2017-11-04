package sv.com.tecnocompu.tecnocompuapp.webapi;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TecnoCompuAPIControler {

    public static TecnoCompuAPI getClient() {

        String BASEURL = "http://192.168.0.18/api/";
        String URL_FINAL = "http://192.168.8.102/tecnocompuwebservice/public/index.php/api/";
        String ALT_URL = "https://gitlab.com/snippets/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_FINAL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        return retrofit.create(TecnoCompuAPI.class);
    }
}
