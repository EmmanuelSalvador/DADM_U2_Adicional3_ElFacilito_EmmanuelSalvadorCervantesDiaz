package mx.edu.ittepic.dadm_u2_adicional3_elfacilito_emmanuelsalvadorcervantesdiaz;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    LinearLayout layo;
    EditText edNombre, edEdad;
    Spinner spGenero;
    RadioButton rbSoltero, rbCasado;
    RadioGroup estCivil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layo=findViewById(R.id.layin);
        TextView titulo = new TextView(this);
        titulo.setText("DATOS GENERALES");
        titulo.setGravity(1);
        layo.addView(titulo);

        LinearLayout llNombre = new LinearLayout(this);
        TextView tvNombre = new TextView(this);
        tvNombre.setText("Nombre");
        tvNombre.setEms(4);
        edNombre = new EditText(this);
        edNombre.setEms(15);
        edNombre.setHint("Iniciando con apellidos");
        llNombre.addView(tvNombre);
        llNombre.addView(edNombre);
        llNombre.setGravity(1);
        layo.addView(llNombre);

        LinearLayout llEdad = new LinearLayout(this);
        TextView tvEdad = new TextView(this);
        tvEdad.setText("Edad");
        tvEdad.setEms(4);
        edEdad = new EditText(this);
        edEdad.setEms(15);
        edEdad.setInputType(InputType.TYPE_CLASS_NUMBER);
        edEdad.setHint("Debe ser un número");
        llEdad.addView(tvEdad);
        llEdad.addView(edEdad);
        llEdad.setGravity(1);
        layo.addView(llEdad);

        TextView txtGenero= new TextView(this);
        txtGenero.setText("Genero");
        spGenero = new Spinner(this);
        String[] listaSpinner = new String[]{"Femenino", "Masculino"};
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, listaSpinner);
        spGenero.setAdapter(adaptador);
        final LinearLayout genero= new LinearLayout(this);
        genero.setGravity(1);
        genero.addView(txtGenero);
        genero.addView(spGenero);
        layo.addView(genero);

        TextView txtEstCivil = new TextView(this);
        txtEstCivil.setText("EstCivil");
        txtEstCivil.setWidth(180);
        rbSoltero= new RadioButton(this);
        rbSoltero.setText("Soltero / soltera");
        rbCasado = new RadioButton(this);
        rbCasado.setText("Casado / casada");
        estCivil = new RadioGroup(this);
        estCivil.addView(rbSoltero);
        estCivil.addView(rbCasado);
        LinearLayout liEstCivil = new LinearLayout(this);
        liEstCivil.addView(estCivil);
        final LinearLayout grupo = new LinearLayout(this);
        grupo.addView(txtEstCivil);
        grupo.addView(liEstCivil);
        grupo.setGravity(1);
        layo.addView(grupo);

        Button btEnviar = new Button(this);
        btEnviar.setText("Enviar");
        btEnviar.setEms(6);
        Button btLimpiar = new Button(this);
        btLimpiar.setText("Limpiar");
        btEnviar.setEms(6);
        LinearLayout botones = new LinearLayout(this);
        botones.addView(btEnviar);
        botones.addView(btLimpiar);
        botones.setGravity(1);
        layo.addView(botones);

        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edNombre.getText().toString().compareTo("")==0 || edEdad.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this,"Por favor, llena todos los campos",Toast.LENGTH_LONG).show();
                }
                else {
                    if (rbCasado.isChecked()||rbSoltero.isChecked()) {
                        mandarAlerta();
                    }
                    else {
                        Toast.makeText(MainActivity.this,"Por favor, llena todos los campos",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        btLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edNombre.setText("");
                edEdad.setText("");
                spGenero.setSelection(0);
                estCivil.clearCheck();
            }
        });
    }

    private void mandarAlerta() {
        String nombre = edNombre.getText().toString();
        String edad = edEdad.getText().toString();
        String genero = spGenero.getSelectedItem().toString();
        String estadoCivil;
        if (genero.equals("Femenino")){
            if (rbSoltero.isChecked()){
                estadoCivil="Soltera";
            }
            else {

                estadoCivil="Casada";
            }
        }
        else {
            if (rbCasado.isChecked()){
                estadoCivil="Casado";
            }
            else {
                estadoCivil="Soltero";
            }
        }
        AlertDialog.Builder mensaje = new AlertDialog.Builder(this);
        mensaje.setTitle("Enviando...")
                .setMessage("Nombre: "+nombre+"\nEdad: "+edad+"\nGenero: "+genero+"\nEstado civil: "+estadoCivil)
                .show();
    }
}
