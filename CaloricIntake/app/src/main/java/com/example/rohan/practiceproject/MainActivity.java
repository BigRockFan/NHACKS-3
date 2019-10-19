package com.example.rohan.practiceproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity
{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText weightval = (EditText)findViewById(R.id.enterWeight);
        final EditText heightval = (EditText)findViewById(R.id.enterHeight);
        final EditText ageval = (EditText)findViewById(R.id.enterAge);
        final TextView answer = (TextView)findViewById(R.id.calorieText);

        //Spinner 1
        final Spinner spinner1 = (Spinner) findViewById(R.id.activityDrop);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(myAdapter);
        //

        //Spinner 2
        final Spinner spinner2 = (Spinner) findViewById(R.id.genderDrop);
        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.genders));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(myAdapter1);
        //

        Button fab = (Button) findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //.setAction("Action", null).show();
                String errorMess = "ERROR";
                if ((heightval.getText().toString()).equals("")||(weightval.getText().toString()).equals("")||(ageval.getText().toString()).equals("")
                        || Integer.parseInt(weightval.getText().toString()) > 1000|| (Integer.parseInt(heightval.getText().toString())) > 1000 ||
                        (Integer.parseInt(ageval.getText().toString()) > 1000))
                    answer.setText(errorMess);
                else
                {
                    int weight = Integer.parseInt(weightval.getText().toString());
                    int height = Integer.parseInt(heightval.getText().toString());
                    int age = Integer.parseInt(ageval.getText().toString());
                    String gender = spinner2.getSelectedItem().toString();
                    String activity = spinner1.getSelectedItem().toString();
                    double act;
                    double mult = 1;
                    if (activity.equals("Basal Metabolic Rate (nothing)"))
                        mult = 1;
                    else if (activity.equals("Sedentary: little or no exercise"))
                        mult = 1.2;
                    else if (activity.equals("Lightly active: exercise/sports 1 to 3 times/week"))
                        mult = 1.375;
                    else if (activity.equals("Moderately Active: exercise/sports 3 to 5 times/week"))
                        mult = 1.55;
                    else if (activity.equals("Very Active: hard exercise/sports 6 to 7 times/week"))
                        mult = 1.725;
                    else if (activity.equals("Extra Active: very hard exercise/sports or physical job"))
                        mult = 1.9;
                    else
                        mult = -1;
                    double cal;

                    if (gender.equals("Male"))
                        cal = 10 * weight + 6.25 * height - 5 * age + 5;
                    else if (gender.equals("Female"))
                        cal = 10 * weight + 6.25 * height - 5 * age - 161;
                    else
                        cal = -1;

                    if (cal > 0 && mult > 0)
                    {
                        cal = cal * mult;
                        int calInt = (int)cal;
                        String calString = Integer.toString(calInt);
                        calString = calString + " cal";
                        answer.setText(calString);
                    }
                    else
                    {
                        String error1 = "ERROR";
                        answer.setText(error1);
                    }
                }

            }
        });

        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
        {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
        }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
        {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings)
                {
                return true;
                }

            return super.onOptionsItemSelected(item);
        }
}
