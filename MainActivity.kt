package com.example.weekthree

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appSettingsPreferences: SharedPreferences = getSharedPreferences("AppSettingsPreferences", 0)
        val sharedPreferencesEdit: SharedPreferences.Editor = appSettingsPreferences.edit()

        val isNightModeOn: Boolean = appSettingsPreferences.getBoolean("Night Mode", false)
        val nightModeSwitch = findViewById<Switch>(R.id.nightModeSwitch)
        val tvAppbarTitle = findViewById<TextView>(R.id.tvAppbarTitle)


        if (isNightModeOn) {
            nightModeSwitch.isChecked = true
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            tvAppbarTitle.text = getString(R.string.good_night)
        } else {
            nightModeSwitch.isChecked = false
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            tvAppbarTitle.text = getString(R.string.good_morning)
        }

        nightModeSwitch.setOnCheckedChangeListener { _, isChecked ->

            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                sharedPreferencesEdit.putBoolean("Night Mode", true)
                sharedPreferencesEdit.apply()

                tvAppbarTitle.text = getString(R.string.good_night)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                sharedPreferencesEdit.putBoolean("Night Mode", false)
                sharedPreferencesEdit.apply()

                tvAppbarTitle.text = getString(R.string.good_morning)
            }
        }
    }
}
