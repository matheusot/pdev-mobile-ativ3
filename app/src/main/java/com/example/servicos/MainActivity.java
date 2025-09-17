package com.example.servicos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.servicos.api.InvertextoApi;
import com.example.servicos.model.Logradouro;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Cria as variáveis ligadas a View
        EditText etCep = findViewById(R.id.etCep);
        Button btBuscar = findViewById(R.id.btBuscar);

        btBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strCEPouIP = etCep.getText().toString();
                if (strCEPouIP.contains("-") || (strCEPouIP.matches("\\d{8}") && !strCEPouIP.contains("."))) {
                    consultar(strCEPouIP, "CEP");
                } else if (strCEPouIP.contains(".")) {
                    consultar(strCEPouIP, "IP");
                } else {
                    Toast.makeText(
                            MainActivity.this,
                            "O texto deve ser um CEP ou IP válido.",
                            Toast.LENGTH_LONG
                    ).show();
                }
            }
        });
    }

    private void consultar(String str, String tipo) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TextView tvInfo = findViewById(R.id.tvInfo);
        tvInfo.setText("Carregando...");

        InvertextoApi invertextoApi = retrofit.create(InvertextoApi.class);

        Call<Logradouro> call;
        if (tipo == "CEP") {
            call = invertextoApi.getLogradouro(str, BuildConfig.API_TOKEN);
        } else {
            call = invertextoApi.getIP(str, BuildConfig.API_TOKEN);
        }

        call.enqueue(new Callback<Logradouro>() {
            @Override
            public void onResponse(Call<Logradouro> call, Response<Logradouro> response) {
                if (response.isSuccessful()) {
                    Logradouro logradouro = response.body();
                    tvInfo.setText(logradouro.formatar(tipo));
                } else {
                    Toast.makeText(
                            MainActivity.this,
                            "Erro ao buscar informações. Verifique o ".concat(tipo),
                            Toast.LENGTH_LONG
                    ).show();
                    tvInfo.setText("");
                }
            }

            @Override
            public void onFailure(Call<Logradouro> call, Throwable throwable) {
                Toast.makeText(
                        MainActivity.this,
                        "Erro ao buscar informações. Verifique sua conexão com a Internet",
                        Toast.LENGTH_LONG
                ).show();
                tvInfo.setText("");
            }
        });
    }
}