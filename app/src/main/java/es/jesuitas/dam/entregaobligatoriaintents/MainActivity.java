package es.jesuitas.dam.entregaobligatoriaintents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int TEXT_REQUEST = 1;

    int cantidad = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void decremeto(View view) {
        cantidad--;
        TextView cambiarTextodecre = findViewById(R.id.text4);
        cambiarTextodecre.setText(String.valueOf(cantidad));
    }

    private void incremento(View view) {
        cantidad++;
        TextView cambiarTextoincre = findViewById(R.id.text4);
        cambiarTextoincre.setText(String.valueOf(cantidad));
    }


    public void showToast(View view) {

        double precioUnidad = 1.50;

        EditText TextoEditado = findViewById(R.id.text1);
        String texto = TextoEditado.getText().toString();
        String texto_cantidad = texto + getString(R.string.you_ordered) + cantidad + getString(R.string.coffee);

        CheckBox nata = findViewById(R.id.checkBox1);
        boolean seleccrionarNata = nata.isChecked();

        CheckBox chocolate = findViewById(R.id.checkBox2);
        boolean seleccionarChocolate = chocolate.isChecked();

        if(seleccrionarNata){
            texto_cantidad += getString(R.string.Whipped_Cream);
            precioUnidad += 0.50;
            if(seleccionarChocolate){
                texto_cantidad += getString(R.string.and);
            }
        }

        if(seleccionarChocolate){
            texto_cantidad += getString(R.string.with_Chocolate);
            precioUnidad += 0.50;
        }

        double precioTotal = precioUnidad * cantidad;
        String texto_final = texto_cantidad + "\n" + precioTotal;

        EditText correo = findViewById(R.id.correo);
        String correo2 = correo.getText().toString();


        Intent intent = new Intent(this, MostrarTexto.class);
        intent.putExtra("correo", correo2);
        intent.putExtra("texto", texto_final);
        startActivityForResult(intent, TEXT_REQUEST);


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String toast = data.getStringExtra("Toast");
                Toast toast2 = Toast.makeText(this, toast, Toast.LENGTH_SHORT);
                toast2.show();
            }
        }
    }
}