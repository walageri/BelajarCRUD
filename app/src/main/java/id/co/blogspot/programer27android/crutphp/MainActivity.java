package id.co.blogspot.programer27android.crutphp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.blogspot.programer27android.crutphp.Model.ResponseModel;
import id.co.blogspot.programer27android.crutphp.ResApi.ResApi;
import id.co.blogspot.programer27android.crutphp.ResApi.RetroServer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.nama)
    EditText nama;
    @BindView(R.id.usia)
    EditText usia;
    @BindView(R.id.domisili)
    EditText domisili;
    @BindView(R.id.insert)
    Button insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(MainActivity.this, "Data disimpan", Toast.LENGTH_SHORT).show();
                try {


                String nma= nama.getText().toString();
                String us= usia.getText().toString();
                String dm= domisili.getText().toString();
                if (nma.isEmpty()){
                    nama.setError("masukkan nama");
                }else if (us.isEmpty()){
                    usia.setError("Masukkan usia");
                }else if (dm.isEmpty()){
                    domisili.setError("Masukkan domisili");
                }else {
                    ResApi resApi = RetroServer.getClient().create(ResApi.class);
                    Call<ResponseModel> sendBio = resApi.insert(nma, us, dm);
                    //untuk daptkan response metodenya adalah enqueue
                    //new ctrl + space
                        sendBio.enqueue(new Callback<ResponseModel>() {
                            @Override
                            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                                Log.e("onrespose", "benar" + response.body().toString());

                                String kode = response.body().getKode();
                                if (kode.equals(1)) {
                                    Toast.makeText(MainActivity.this, "Berhasil di insert"+response, Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(MainActivity.this,MainActivity.class));

                                } else {
                                    Toast.makeText(MainActivity.this, "Tidak Berhasil"+response, Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseModel> call, Throwable t) {
                                Log.d("onfailure", "error" + t.toString());
                            }
                        });
//                        funsinya untuk menanjgani error agar tidak force close


                }
            }catch (Exception e){
                    e.printStackTrace();
                }
            }

        });
    }
}
