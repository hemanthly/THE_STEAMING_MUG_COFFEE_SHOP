/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 2, price_of_each = 5;
    boolean whippedcream_checked = false, chocolate_checked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display(quantity);
        Toast.makeText(getApplicationContext(),"Welcome! to STEAMING MUG , COFFEE HOUSE .",Toast.LENGTH_SHORT).show();
    }
    /**
     * This method is called when the order button is clicked.
     */
    public String getmyname() {
        TextView priceTextView = (TextView) findViewById(R.id.album_description_view);
        String name = priceTextView.getText().toString();
        return name;
    }
    public int calculatePrice()
    {
        int total = 0;
        if(whippedcream_checked)
            total += quantity * 1;
        if(chocolate_checked)
            total += quantity * 2;

        total += quantity * price_of_each;
        return total;
    }
    public void composeEmail( String subject, String message , String addresses) {

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    public void submitOrder(View view) {
   //      String priceMessage = quantity*price_of_each + " dollars for " + quantity + " cups of coffee. Pay up.";
            String message = "", sub = getmyname() + "! your Order from STEEMING MUG COFFEE SHOP";
            String recepient = "steemingmug_coffee_shop@gmail.com";
         if( quantity > 0 && quantity < 11 )
            {
               message += getmyname();
               message += "\nAdd biscuit ? " + whippedcream_checked ;
               message += "\nAdd whipped cream ? " + chocolate_checked ;
               message += "\nQuantity : " + quantity;
               message += "\nTotal: $" + calculatePrice();
               message += "\nThank you!";
//                displayMessage(priceMessage);
            }
        Toast.makeText(getApplicationContext()," Thank you. we will deliver your Order shortly. \n ",Toast.LENGTH_SHORT).show();

        composeEmail(sub, message , recepient);

         // return priceMessage;
        //displayPrice(quantity * price_of_each );
    }

    public void notify_me_checkbox_fn(View view) {

        if(whippedcream_checked) whippedcream_checked = false;
        else
            whippedcream_checked = true;
    }

    public void notify_me_chocolate_fn(View view) {
        if(chocolate_checked) chocolate_checked = false;
        else
            chocolate_checked = true;
    }

    public void submitPlus(View view) {
        if(quantity >= 10)
        {
            // i.e don't go beyond 10.
            Toast.makeText(getApplicationContext()," Sorry, we can't deliver more than 10 coffee. in accordance with COVID GUIDELINES ",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity++;
        display(quantity);
        //displayPrice(quantity * price_of_each );
    }

    public void submitMinus(View view) {
        if(quantity <= 1 )
        {
            // don't go less than 0.
            Toast.makeText(getApplicationContext()," Please, Order atleast 1 coffee ",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity--;
        display(quantity);

        //displayPrice(quantity * price_of_each ); ````````````````````````````````````````````````````````````````````pppppp
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    public void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */

//    public void displayPrice(int number) {
//        TextView priceTextView = (TextView) findViewById(R.id.order_summary_view);
//        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
//    }

    /**
     * This method displays the given text on the screen.
     */
//    public void displayMessage(String message) {
//        TextView priceTextView = (TextView) findViewById(R.id.order_summary_view);
//        priceTextView.setText(message);
//    }

}