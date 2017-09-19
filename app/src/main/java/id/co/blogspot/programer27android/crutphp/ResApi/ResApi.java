package id.co.blogspot.programer27android.crutphp.ResApi;

import id.co.blogspot.programer27android.crutphp.Model.ResponseModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by kcg on 9/16/2017.
 */

public interface ResApi {
//    setiap insert kita menggunakan @formURL EndCode
    @FormUrlEncoded
    //karan dia menggunakan php tetap ada format .php
    @POST("insert.php/")
    Call<ResponseModel> insert(@Field("nama")String nama,
                               @Field("usia")String usia,
                               @Field("domisili")String domisili);

}
