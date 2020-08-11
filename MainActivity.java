package com.brandon.digitalstorageconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Spinner toSpinner;
    private Spinner fromSpinner;
    private TextView textViewFrom;
    private TextView textViewTo;
    private String fromUnit;
    private String toUnit;
    private EditText edit;
    private double amount;
    private BigDecimal amountOfBytes;
    //private MathContext mc = new MathContext(4);
    private ArrayList<String> conversionList = new ArrayList<>();
    //size value
    private int toValue;
    private int fromValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create a list of all of the units to convert
        ArrayList<String> list = new ArrayList<>(); //remove bits and make this a byte converter and then make a separate app for converting bits
        //list.add("Select Unit");
        //list.add("Bit");
        //list.add("Kilobit");
        //list.add("Kibibit");
        //list.add("Megabit");
        //list.add("Mebibit");
        //list.add("Gigabit");
        //list.add("Gibibit");
        //list.add("Terabit");
        //list.add("Tebibit");
        //list.add("Petabit");
        //list.add("Pebibit");
        list.add("Byte");
        list.add("Kilobyte");
        list.add("Kibibyte");
        list.add("Megabyte");
        list.add("Mebibyte");
        list.add("Gigabyte");
        list.add("Gibibyte");
        list.add("Terabyte");
        list.add("Tebibyte");
        list.add("Petabyte");
        list.add("Pebibyte");

        //add each item to the 2 spinners
        fromSpinner = (Spinner)findViewById(R.id.fromSpinner);
        toSpinner = (Spinner)findViewById(R.id.toSpinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, list);
        fromSpinner.setAdapter(adapter);
        toSpinner.setAdapter(adapter);

    }

    public void convertButtonClicked(View v){
        TextView viewToSpinner = (TextView)toSpinner.getSelectedView();
        TextView viewFromSpinner = (TextView)fromSpinner.getSelectedView();

        try {
            fromUnit = viewFromSpinner.getText().toString(); //gets the name of the start unit (string value)
            toUnit = viewToSpinner.getText().toString(); //gets the name of the end unit (string value)
        } catch (NullPointerException e){
            System.out.println(e);
        }

        edit = (EditText)findViewById(R.id.editText);
        String result = edit.getText().toString();
        try {
            int num = Integer.parseInt(result);
            amount = (double)num;
        }catch(NumberFormatException e){
            System.out.println(e);
        }

        BigDecimal from = new BigDecimal(0);
        BigDecimal to = new BigDecimal(0);
        BigDecimal am = new BigDecimal(0);
        MathContext m = new MathContext(5);

        from = toBytes();

        switch (toUnit){
            case "Bit":
                //amountOfBytes = new BigDecimal(amount / 8);
                am = new BigDecimal(8.0);
                to = from.multiply(am, m);
                toValue = 1;
                break;
            case "Kilobit":
                //amountOfBytes = new BigDecimal(amount * 125);
                am = new BigDecimal(125);
                to = from.divide(am, m);
                toValue = 4;
                break;
            case "Kibibit":
                //amountOfBytes = new BigDecimal(amount * 128);
                am = new BigDecimal(128);
                to = from.divide(am, m);
                toValue = 5;
                break;
            case "Megabit":
                //amountOfBytes = new BigDecimal(amount * 125000);
                am = new BigDecimal(125000);
                to = from.divide(am, m);
                toValue = 7;
                break;
            case "Mebibit":
                //amountOfBytes = new BigDecimal(amount * 131072);
                am = new BigDecimal(131072);
                to = from.divide(am, m);
                toValue = 8;
                break;
            case "Gigabit":
                //amountOfBytes = new BigDecimal(amount * 1.25e+8);
                am = new BigDecimal(1.25e+8);
                to = from.divide(am, m);
                toValue = 10;
                break;
            case "Gibibit":
                //amountOfBytes = new BigDecimal(amount * 1.342e+8);
                //am = new BigDecimal(1.342e+8);
                //to = from.divide(am, m);
                am = new BigDecimal(7.4506e-9); // --------------------------1
                to = from.multiply(am, m);
                toValue = 11;
                break;
            case "Terabit":
                //amountOfBytes = new BigDecimal(amount * 1.25e+11);
                am = new BigDecimal(1.25e+11);
                to = from.divide(am, m);
                toValue = 13;
                break;
            case "Tebibit":
                //amountOfBytes = new BigDecimal(amount * 1.374e+11);
                //am = new BigDecimal(1.374e+11);
                //to = from.divide(am, m);
                am = new BigDecimal(7.276e-12); // ------------------------2
                to = from.multiply(am, m);

                toValue = 14;
                break;
            case "Petabit":
                //amountOfBytes = new BigDecimal(amount * 1.25e+14);
                am = new BigDecimal(1.25e+14);
                to = from.divide(am, m);
                toValue = 16;
                break;
            case "Pebibit":
                //amountOfBytes = new BigDecimal(amount * 1.407e+14);
                //am = new BigDecimal(1.407e+14);
                //to = from.divide(am, m);
                am = new BigDecimal(7.1054e-15); // --------------------------------3
                to = from.multiply(am, m);
                toValue = 17;
                break;
            case "Byte":
                //amountOfBytes = new BigDecimal(amount);
                am = from;
                to = am;
                toValue = 2;
                break;
            case "Kilobyte":
                //amountOfBytes = new BigDecimal(amount * 1000);
                am = new BigDecimal(1000);
                to = from.divide(am, m);
                toValue = 5;
                break;
            case "Kibibyte":
                //amountOfBytes = new BigDecimal(amount * 1024);
                am = new BigDecimal(1024);
                to = from.divide(am, m);
                toValue = 5;
                break;
            case "Megabyte":
                //amountOfBytes = new BigDecimal(amount * 1e+6);
                am = new BigDecimal(1e+6);
                to = from.divide(am, m);
                toValue = 8;
                break;
            case "Mebibyte":
                //amountOfBytes = new BigDecimal(amount * 1.049e+6);
                //am = new BigDecimal(1.049e+6);
                //to = from.divide(am, m);
                am = new BigDecimal(9.5367e-7); // -----------------------------------4
                to = from.multiply(am, m);
                toValue = 8;
                break;
            case "Gigabyte":
                //amountOfBytes = new BigDecimal(amount * 1e+9);
                am = new BigDecimal(1e+9);
                to = from.divide(am, m);
                toValue = 11;
                break;
            case "Gibibyte":
                //amountOfBytes = new BigDecimal(amount * 1.074e+9);
                //am = new BigDecimal(1.074e+9);
                //to = from.divide(am, m);
                am = new BigDecimal(9.3132e-10); // --------------------------------5
                to = from.multiply(am, m);

                toValue = 11;
                break;
            case "Terabyte":
                //amountOfBytes = new BigDecimal(amount * 1e+12);
                am = new BigDecimal(1e+12);
                to = from.divide(am, m);
                toValue = 14;
                break;
            case "Tebibyte": //change to multiply by 9.0949e-13
                //amountOfBytes = new BigDecimal(amount * 1.1e+12);
                //am = new BigDecimal(1.1e+12);
                am = new BigDecimal(9.0949e-13);
                //to = from.divide(am, m);
                to = from.multiply(am, m);
                toValue = 14;
                break;
            case "Petabyte":
                //amountOfBytes = new BigDecimal(amount * 1e+15);
                am = new BigDecimal(1e+15);
                to = from.divide(am, m);
                toValue = 16;
                break;
            case "Pebibyte":
                //amountOfBytes = new BigDecimal(amount * 1.126e+15);
                //am = new BigDecimal(1.126e+15);
                //to = from.divide(am, m);
                am = new BigDecimal(8.8818e-16); //-----------------------------------------6
                to = from.multiply(am, m);
                toValue = 17;
                break;
        }

        int ref = to.intValue();
        //to = to.setScale(5, to.ROUND_HALF_UP); //set at 5 for now but can change to whatever later

        DecimalFormat format = new DecimalFormat("0.#####E0");

        format.format(to);



        String text = "";

        if (fromUnit.equals("Select Unit") && toUnit.equals("Select Unit")) {
            text = "Select a unit to convert to and from.";
        } else if (fromUnit.equals("Select Unit") && !toUnit.equals("Select Unit")) {
            text = "Select a unit to convert from.";
        } else if (toUnit.equals("Select Unit") && !fromUnit.equals("Select Unit")) {
            text = "Select a unit to convert to.";
        } else {
            if (amount != 1){
                fromUnit += "s";
            }
            if (ref != 1){
                toUnit += "s";
            }

            int d1 = toValue - fromValue;
            int d2 = fromValue - toValue;
            int chosen = 0;
            if (d1 < 0){
                chosen = d2;
            } else {
                chosen = d1;
            }

            //make a case situation where if the from and to units are too far apart, the to unit gets formatted
            if (chosen < 7) {
                text = amount + " " + fromUnit + " = " + to + " " + toUnit;
            } else {
                text = amount + " " + fromUnit + " = " + format.format(to) + " " + toUnit; //change from 'to' to format.format(to)
            }
        }

        String output = "";

        for (int i = conversionList.size() - 1; i >= 0; i--) {
            output += conversionList.get(i) + "\n";
        }
        conversionList.add(text);

        TextView tv = (TextView)findViewById(R.id.textView); //blue top
        tv.setText(text);
        TextView tv2 = (TextView)findViewById(R.id.textViewOutput);
        tv2.setText(output);
        tv2.setMovementMethod(new ScrollingMovementMethod());

    }

    public BigDecimal toBytes(){
        TextView textView = (TextView)fromSpinner.getSelectedView();
        TextView textView2 =(TextView)toSpinner.getSelectedView();
        try {
            fromUnit = textView.getText().toString(); //gets the name of the start unit (string value)
            toUnit = textView2.getText().toString(); //gets the name of the end unit (string value)
        } catch (NullPointerException e){
            System.out.println(e);
        }
        edit = (EditText)findViewById(R.id.editText);
        String result = edit.getText().toString();
        try {
            double num = Double.parseDouble(result);
            amount = (double)num;
        }catch(NumberFormatException e){
            System.out.println(e);
        }

        switch (fromUnit){
            case "Bit":
                amountOfBytes = new BigDecimal(amount * .125);
                fromValue = 1;
                break;
            case "Kilobit":
                amountOfBytes = new BigDecimal(amount * 125);
                fromValue = 4;
                break;
            case "Kibibit":
                amountOfBytes = new BigDecimal(amount * 128);
                fromValue = 5;
                break;
            case "Megabit":
                amountOfBytes = new BigDecimal(amount * 125000);
                fromValue = 7;
                break;
            case "Mebibit":
                amountOfBytes = new BigDecimal(amount * 131072);
                fromValue = 8;
                break;
            case "Gigabit":
                amountOfBytes = new BigDecimal(amount * 1.25e+8);
                fromValue = 10;
                break;
            case "Gibibit":
                //amountOfBytes = new BigDecimal(amount * 1.342e+8);
                amountOfBytes = new BigDecimal(amount / 7.4506e-9);
                fromValue = 11;
                break;
            case "Terabit":
                amountOfBytes = new BigDecimal(amount * 1.25e+11);
                fromValue = 13;
                break;
            case "Tebibit":
                //amountOfBytes = new BigDecimal(amount * 1.374e+11);
                amountOfBytes = new BigDecimal(amount / 7.276e-12);
                fromValue = 14;
                break;
            case "Petabit":
                amountOfBytes = new BigDecimal(amount * 1.25e+14);
                fromValue = 16;
                break;
            case "Pebibit":
                //amountOfBytes = new BigDecimal(amount * 1.407e+14);
                amountOfBytes = new BigDecimal(amount / 7.1054e-15);
                fromValue = 17;
                break;
            case "Byte":
                amountOfBytes = new BigDecimal(amount * 1.0);
                fromValue = 2;
                break;
            case "Kilobyte":
                amountOfBytes = new BigDecimal(amount * 1000);
                fromValue = 5;
                break;
            case "Kibibyte":
                amountOfBytes = new BigDecimal(amount * 1024);
                fromValue = 5;
                break;
            case "Megabyte":
                amountOfBytes = new BigDecimal(amount * 1e+6);
                fromValue = 8;
                break;
            case "Mebibyte":
                //amountOfBytes = new BigDecimal(amount * 1.049e+6);
                amountOfBytes = new BigDecimal(amount / 9.5367e-7);
                fromValue = 8;
                break;
            case "Gigabyte":
                amountOfBytes = new BigDecimal(amount * 1e+9);
                fromValue = 11;
                break;
            case "Gibibyte":
                //amountOfBytes = new BigDecimal(amount * 1.074e+9);
                amountOfBytes = new BigDecimal(amount / 9.3132e-10);
                fromValue = 11;
                break;
            case "Terabyte":
                amountOfBytes = new BigDecimal(amount * 1e+12);
                fromValue = 14;
                break;
            case "Tebibyte":
                //amountOfBytes = new BigDecimal(amount * 1.1e+12);
                amountOfBytes = new BigDecimal(amount / 9.0949e-13);
                fromValue = 14;
                break;
            case "Petabyte":
                amountOfBytes = new BigDecimal(amount * 1e+15);
                fromValue = 16;
                break;
            case "Pebibyte":
                //amountOfBytes = new BigDecimal(amount * 1.126e+15);
                amountOfBytes = new BigDecimal(amount / 8.8818e-16);
                fromValue = 17;
                break;
            case "Select Unit":
                amountOfBytes = new BigDecimal(0);
                break;
        }

        return amountOfBytes;




    }





}

