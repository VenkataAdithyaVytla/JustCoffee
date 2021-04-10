package com.example.justjava1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.justjava1.R;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    int quantity=2;
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText name=(EditText)findViewById(R.id.name_view);
        CheckBox whippedCreamCheckBox = (CheckBox)findViewById(R.id.whipped_cream_checkbox);
        CheckBox choclateCheckBox = (CheckBox)findViewById(R.id.choclate_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        boolean hasChoclate = choclateCheckBox.isChecked();
        String Name=name.getText().toString();
        String Subject="just java order for "+Name;
        String orderSummary=createOrderSummary(hasWhippedCream,hasChoclate,Name);
        displayMessage(orderSummary);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT,Subject);
        intent.putExtra(Intent.EXTRA_TEXT,orderSummary);
        /*if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }*/
        startActivity(intent);
    }
    public void increment(View view) {
        quantity=quantity+1;
        if(quantity>100) {
            quantity=100;
        }
        displayQuantity(quantity);
    }
    public void decrement(View view) {
        quantity=quantity-1;
        if(quantity<1){
            quantity=1;
        }
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int digit) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + digit);
    }
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
    private int calculatePrice(boolean addWhippedCream,boolean addChoclate) {
        int pricePerCup=5;
        if(addWhippedCream) {
            pricePerCup+=1;
        }
        if(addChoclate){
            pricePerCup+=2;
        }
        return (quantity*pricePerCup);
    }
    private String createOrderSummary(boolean addWhippedCream,boolean addChoclate,String name) {
        return "Name:"+name+"\nAdd whipped cream:"+addWhippedCream+"\nAdd choclate:"+addChoclate+"\nQuantity: "+quantity+"\ntotal: $"+calculatePrice(addWhippedCream,addChoclate)+"\nThank you!";
    }
}