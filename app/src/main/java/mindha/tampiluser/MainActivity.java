package mindha.tampiluser;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import mindha.tampiluser.models.User;
import mindha.tampiluser.rest.RestApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static final String ROOT_URL = "https://api.github.com/";

    private TextView txt_id;
    private  TextView txt_nama;
    private  TextView txt_company;
    private  TextView txt_bio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_id = (TextView)findViewById(R.id.txt_id);
        txt_nama = (TextView)findViewById(R.id.txt_nama);
        txt_company = (TextView)findViewById(R.id.txt_company);
        txt_bio = (TextView)findViewById(R.id.txt_bio);

        getData();
    }

    private void getData(){
        final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data","Please wait..",false,false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RestApi service = retrofit.create(RestApi.class);
        Call<User> call = service.getDataUser();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                try{
                    loading.dismiss();

                    String id = response.body().getId().toString();
                    String name = response.body().getName();
                    String bio = response.body().getBio();
                    String company = response.body().getCompany();

                    Toast.makeText(MainActivity.this, id, Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();


                    txt_id.setText("ID : "+ id);
                    txt_nama.setText("Nama : "+name);
                    txt_bio.setText("Bio : "+bio);
                    txt_company.setText("Company : "+company);


                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Toast Success", Toast.LENGTH_SHORT).show();

            }
        });

    }


}
