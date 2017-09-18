package com.victorsaico.gastosapp.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.victorsaico.gastosapp.Models.Registro;
import com.victorsaico.gastosapp.R;
import com.victorsaico.gastosapp.Repositories.RegistroRepository;

import butterknife.BindView;

public class RegistroActivity extends AppCompatActivity {
    @BindView(R.id.edtmonto)EditText edtmonto;
    //private RegistroActivity e1 = new RegistroActivity();
    private String tipo,cuenta;
    private Spinner spinnertipocuenta;
    private EditText monto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        spinnertipocuenta = (Spinner) findViewById(R.id.spinnertipocuenta);
        edtmonto = (EditText) findViewById(R.id.edtmonto);
        spinnertipocuenta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                String opcion = adapterView.getItemAtPosition(pos).toString();
                switch (opcion){
                    case ("Tarjeta de Credito"):
                        cuenta = "cuenta";
                        break;
                    case ("Ahorro"):
                        cuenta = "ahorro";
                        break;
                    case ("Efectivo"):
                        cuenta = "efectivo";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void radiotipoclick (View view){
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.radioButtonIngreso:
                if (checked)
                    tipo = "ingreso";
                    break;
            case R.id.radioButtonEgreso:
                if (checked)
                    tipo = "egreso";
                    break;
        }
    }
    public void montototal(View view){
         String monto = edtmonto.getText().toString();
        if(monto.isEmpty() || tipo.isEmpty()){
            Toast.makeText(this, "Ingrese un monto de dinero", Toast.LENGTH_SHORT).show();
            return;
        }
        Registro registro = new Registro(tipo,cuenta,Double.parseDouble(monto));
        RegistroRepository registrorepository = RegistroRepository.getInstance();
        registrorepository.agregarRegistro(registro);
        finish();
    }

}
