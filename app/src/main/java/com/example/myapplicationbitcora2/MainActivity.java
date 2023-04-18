package com.example.myapplicationbitcora2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.et1);
        String archivos [] = fileList();

        if (ArchivoExiste(archivos, "Bitácora.txt")) {

            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput("Bitácora.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                String archivoCompleto = "";

                while(linea != null) {
                    archivoCompleto = archivoCompleto + linea + "\n";
                    linea = br.readLine();
                }

                br.close();
                archivo.close();
                et1.setText(archivoCompleto);

            } catch (IOException e) {


            }

        }

    }

    // Metodo booleano si el archivo existe
    private boolean ArchivoExiste(String archivos [], String NombreArchivo) {
        for (int i = 0; i < archivos.length; i++) // En el método booleano no poner llaves //
            if (NombreArchivo.equals(archivos[i]))
                return true;
        return false;
    }
    // Metodo para el boton guardar //

    public void Guardar (View view) {
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("Bitácora.txt", Activity.MODE_PRIVATE));
            archivo.write(et1.getText().toString());
            archivo.flush(); // Para guardar el archivo //
            archivo.close(); // Para cerrar el archivo //
        } catch (IOException e) {
            Toast.makeText(this, "El archivo no se ha podido guardar", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this, "El archivo se ha guardado correctamente", Toast.LENGTH_SHORT).show();
        finish();

    }

}