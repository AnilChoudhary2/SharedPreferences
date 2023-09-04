package com.bob.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;



import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class SettingsActivity extends AppCompatActivity {
    private SwitchMaterial mySwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
        boolean isDarkmode = sharedPref.getBoolean("APP_DARKMODE",false);

        mySwitch =  findViewById(R.id.main_switch);
        mySwitch.setActivated(isDarkmode);
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                Toast.makeText(SettingsActivity.this, "Switch value is "+isChecked, Toast.LENGTH_SHORT).show();
                if(isChecked){
                    AppCompatDelegate
                            .setDefaultNightMode(
                                    AppCompatDelegate
                                            .MODE_NIGHT_YES);
                }else{
                    AppCompatDelegate
                            .setDefaultNightMode(
                                    AppCompatDelegate
                                            .MODE_NIGHT_NO);
                }
                save(isChecked);
            }
        });



    }

    private void save(boolean isChecked){
        Log.d("isChecked","is checked message");
        //save to sharedprefs
        SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("APP_DARKMODE", isChecked);
        editor.apply();
    }
}