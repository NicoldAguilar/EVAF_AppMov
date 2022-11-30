package com.example.exfinalac;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.exfinalac.database.dataBConfig;
import com.example.exfinalac.entities.CuentaEnti;
import com.example.exfinalac.entities.Image;
import com.example.exfinalac.entities.ImageResponse;
import com.example.exfinalac.entities.MovimientoBEnti;
import com.example.exfinalac.servicios.ImageService;
import com.example.exfinalac.servicios.MovimientosService;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FormMovimientosActivity extends AppCompatActivity {

    EditText etLatitud, etimgURL, etLongitud, etMotivo,etMonto;
    private ImageView ivBoletaImg;
    private Button btnRegistrarM,btnOpenGallery, btnUbicacion;
    Spinner spTipoMovimiento;
    //dataBConfig data = dataBConfig.getInstance(this);

    private final static int CAMERA_REQUEST = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_movimientos);

        CuentaEnti accsetter =new CuentaEnti();
        accsetter.idCuentaE =getIntent().getIntExtra("idCuentaE",0);
        accsetter.nombre =getIntent().getStringExtra("nombre");
        accsetter.saldoBan = getIntent().getIntExtra("saldoBan", 0);

        etMotivo = findViewById(R.id.etMotivo);
        etMonto = findViewById(R.id.etMonto);
        etLongitud = findViewById(R.id.etLongitud);
        etLatitud = findViewById(R.id.etLatitud);
        etimgURL = findViewById(R.id.etimgURL);

        btnOpenGallery = findViewById(R.id.btnOpenGallery);
        btnRegistrarM = findViewById(R.id.btnRegistrarM);
        btnUbicacion = findViewById(R.id.btnUbicacion);

        //Spinner
        spTipoMovimiento = findViewById(R.id.spTipoMovimiento);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tipo_movimiento, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTipoMovimiento.setAdapter(adapter);

        btnUbicacion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                //Llamar al mapa
            }
        });

        btnRegistrarM.setOnClickListener(v -> {
            onClick(v);
        });

        //Datos de la camara
        btnOpenGallery = findViewById(R.id.btnOpenGallery);
        ivBoletaImg = findViewById(R.id.ivBoletaImg);
        btnOpenGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    openGallery();
                }
                else{
                    requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 101 );
                }

            }
        });

    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 1001);
    }

    private void onClick(View v) {
        enviar(v);
        Intent intent = new Intent(this, ListarCuentasActivity.class);
        startActivity(intent);

    }

    public void enviar(View v) {
        String motivo = etMotivo.getText().toString();
        float monto = Float.valueOf(etMonto.getText().toString());
        String longi = etLongitud.getText().toString();
        String lat = etLatitud.getText().toString();
        String imgURL = etimgURL.getText().toString();

        MovimientoBEnti movi = new MovimientoBEnti();
        movi.monto = monto;
        movi.motivo = motivo;
        movi.latitud = lat;
        movi.longitud = longi;
        movi.imagenCuenta = imgURL;
        postRetrofit(movi);
        //data.moviemientoDao().crearMovimiento(movi);
    }

    public void postRetrofit(MovimientoBEnti contactosrg) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://63023872c6dda4f287b57f7c.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovimientosService service = retrofit.create(MovimientosService.class);
        service.create(contactosrg).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i("MAIN_APP", "RESPONSE" + response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode== CAMERA_REQUEST && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ivBoletaImg.setImageBitmap(imageBitmap);
        }

        if(requestCode==1001){
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(data.getData(),filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            Bitmap imageBitmap = BitmapFactory.decodeFile(picturePath);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();

            String imgBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.imgur.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ImageService imageService = retrofit.create(ImageService.class);
            Image image = new Image();
            image.image = imgBase64;

            imageService.sendImage(image).enqueue(new Callback<ImageResponse>() {
                @Override
                public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                    String imR = response.body().data.link;
                    etimgURL.setText(imR);
                }

                @Override
                public void onFailure(Call<ImageResponse> call, Throwable t) {

                }
            });
        }
    }
}