package com.victorsaico.gastosapp.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.victorsaico.gastosapp.Models.Registro;
import com.victorsaico.gastosapp.Models.Saldos;
import com.victorsaico.gastosapp.R;
import com.victorsaico.gastosapp.Repositories.RegistroRepository;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String tipo,cuenta;
    private Double monto;
    private TextView saldoAhorro,saldoTarjeta,saldoEfectivo;
    private Double saldoahorro,saldoacredito,saldoefectivo;
    private ProgressBar progressbarproporcion;
    private Saldos s1 = new Saldos();
   // private float[] ydata;
    PieChart piechart;
    private static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        saldoAhorro = (TextView)findViewById(R.id.saldoAhorro);
        saldoTarjeta = (TextView)findViewById(R.id.saldoCredito);
        saldoEfectivo = (TextView) findViewById(R.id.saldoEfectivo);
        progressbarproporcion = (ProgressBar)findViewById(R.id.progressaproporcion);
        piechart = (PieChart) findViewById(R.id.PieChart);

        //piechart.setDescription("Sales by employee (In Thousands $) ");
        piechart.setRotationEnabled(true);
        //pieChart.setUsePercentValues(true);
        //pieChart.setHoleColor(Color.BLUE);
        //pieChart.setCenterTextColor(Color.BLACK);
        piechart.setHoleRadius(25f);
        piechart.setTransparentCircleAlpha(0);
        piechart.setCenterText("Registro de Ahorros");
        piechart.setCenterTextSize(10);
        obtenerdatos();

    }
    public void addItem(View view){
      Log.d(TAG, "Añadiendo nuevo item");
        startActivityForResult(new Intent(this, RegistroActivity.class),100);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        RegistroRepository registroRepository = RegistroRepository.getInstance();
        List<Registro> registros = registroRepository.getRegistros();
        for (Registro registro : registros){
              tipo = registro.getTipo();
              cuenta = registro.getCuenta();
              monto = registro.getMonto();
        }

        if (tipo.equals("ingreso")){
            switch (cuenta){
                case ("cuenta"):
                    Double saldoatarjetanew = saldoahorro + monto;
                    saldoTarjeta.setText(String.valueOf(saldoatarjetanew));
                    break;
                case ("ahorro"):
                    Double saldoahorronew = saldoacredito + monto;
                    saldoAhorro.setText(String.valueOf(saldoahorronew));
                    break;
                case ("efectivo"):
                    Double saldoefectibonew = saldoefectivo + monto;
                    saldoEfectivo.setText(String.valueOf(saldoefectibonew));
                    break;
            }
        }else if (tipo.equals("egreso")){
            switch (cuenta){
                case ("cuenta"):
                    Double saldoatarjetanew = saldoahorro - monto;
                    saldoTarjeta.setText(String.valueOf(saldoatarjetanew));
                    break;
                case ("ahorro"):
                    Double saldoahorronew = saldoacredito - monto;
                    saldoAhorro.setText(String.valueOf(saldoahorronew));
                    break;
                case ("efectivo"):
                    Double saldoefectibonew = saldoefectivo - monto;
                    saldoEfectivo.setText(String.valueOf(saldoefectibonew));
                    break;
            }
        }
        progressbar();
        obtenerdatos();

    }
    public void obtenerdatos(){
        Saldos s1 = new Saldos();
        saldoacredito  = Double.parseDouble(saldoAhorro.getText().toString());
        saldoahorro= Double.parseDouble(saldoTarjeta.getText().toString());
        saldoefectivo = Double.parseDouble(saldoEfectivo.getText().toString());
        float v1 = saldoacredito.floatValue();
        float v2 = saldoahorro.floatValue();
        float v3 = saldoefectivo.floatValue();
       float[] ydata = {v1, v2, v3};
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        for(int i = 0; i < ydata.length; i++){
            yEntrys.add(new PieEntry(ydata[i] , i));
        }
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Estado de Cuentas");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GRAY);
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        pieDataSet.setColors(colors);

        Legend legend = piechart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        piechart.setData(pieData);
        piechart.invalidate();
    }

    public void progressbar(){
        Double total = saldoahorro + saldoacredito + saldoefectivo;
        Double x = total/100;
        int i = (int) x.doubleValue();
        progressbarproporcion.setProgress(i);
        Log.d(TAG, "progressbar"+i);
    }
}
