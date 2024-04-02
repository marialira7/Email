package moreira.lira.email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Obtendo o botao com findViewById
        Button btnEnviar = (Button) findViewById(R.id.btnEnviar);
        //Definição de click do botao
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Obtendo dados digitados pelo usuario
                EditText etEmail = (EditText) findViewById(R.id.etEmail);
                //Obtendo o valor digitado pelo usuario e colocando dentro de uma string
                String email = etEmail.getText() .toString();

                //Obtendo dados de assunto
                EditText etAssunto = (EditText) findViewById(R.id.etAssunto);
                //Colocando dados de assunto dentro de uma string
                String assunto = etAssunto.getText() .toString();

                //Obtendo dados de texto
                EditText etTexto = (EditText) findViewById(R.id.etTexto);
                //Colocando dados de texto dentro de uma string
                String texto = etTexto.getText() .toString();

                //Criando a intencao onde o SENDTO procura dentro do android quais apps podem resolver a acao
                Intent i =  new Intent(Intent.ACTION_SENDTO);
                //malito é utilizado para indicar apps que trabalham com envio e recebimento de e-mail
                i.setData(Uri.parse("mailto:"));

                //Preenchendo os dados
                String[] emails = new String[]{email};
                i.putExtra(Intent.EXTRA_EMAIL, emails);
                i.putExtra(Intent.EXTRA_SUBJECT, assunto);
                i.putExtra(Intent.EXTRA_TEXT, texto);
                //Executando a intencao
                //Intent.CreateChooser, que faz com que apareça para o usuário um menu onde ele pode escolher entre várias apps
                try {
                    startActivity(Intent.createChooser(i, "Escolha o APP"));
                }
                //Mensagem de erro que sera exibida
                catch (ActivityNotFoundException e) {
                    Toast.makeText(MainActivity.this, "Não há nenhum app que posso realizar essa operação", Toast.LENGTH_LONG).show();
                }


            }
        });

    }
}