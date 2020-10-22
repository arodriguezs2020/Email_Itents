package es.jesuitas.dam.entregaobligatoriaintents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MostrarTexto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_texto);

        Intent intent = getIntent();
        String textoExtra = intent.getStringExtra("texto");
        String correo = intent.getStringExtra("correo");

        TextView texto = findViewById(R.id.textView);
        texto.setText(textoExtra + "\n" + getString(R.string.your_email_is) + correo);
    }

    public void volver(View view) {
        String toast = getString(R.string.toast);
        Intent intent = new Intent();
        intent.putExtra("Toast", toast);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void enviarCorreo(View view) {
        Intent intent = getIntent();
        String subject = intent.getStringExtra("texto");
        String[] correo = {intent.getStringExtra("correo")};
        String message = getString(R.string.message);
        Intent sendIntent = new Intent();

        sendIntent.setAction(Intent.ACTION_SENDTO);
        sendIntent.setData(Uri.parse("mailto:"));
        sendIntent.putExtra(Intent.EXTRA_TEXT, message);
        sendIntent.putExtra(Intent.EXTRA_EMAIL, correo);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        startActivity(sendIntent);
    }
}