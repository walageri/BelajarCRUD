package id.co.blogspot.programer27android.crutphp.ResApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kcg on 9/16/2017.
 */
//yang isinya adalah Url dari server
public class RetroServer {
    //menghubungkan ip server. dan ke folder didalam htdoct
    private static String BASEURL = "http://192.168.42.192/web_siswa/";
    private static Retrofit retrofit;
//    buat class baru yang bair gak dipanggil di methode insert
    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASEURL)
//                     fungsi untuk memakai librari Gson
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
//        kembali ke retrofit
        return retrofit;
    }
}

