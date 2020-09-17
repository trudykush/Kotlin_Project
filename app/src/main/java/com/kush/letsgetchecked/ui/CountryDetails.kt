package com.kush.letsgetchecked.ui

import android.annotation.SuppressLint
import android.graphics.Paint
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.kush.letsgetchecked.R
import kotlinx.android.synthetic.main.activity_country_details.*

class CountryDetails : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_details)

        val actionbar = supportActionBar
        actionbar!!.title        = "Country details"
        actionbar.setDisplayHomeAsUpEnabled(true)   // To get the back button in toolbar

        detailsHeading.setTypeface(null, Typeface.BOLD_ITALIC)
        detailsHeading.paintFlags = Paint.UNDERLINE_TEXT_FLAG

        tableLayout.setColumnShrinkable(1, true)

        val dataFromCountriesAdapter = intent
        val countryName             = dataFromCountriesAdapter.getStringExtra("CountryName")
        val countryCapital          = dataFromCountriesAdapter.getStringExtra("CountryCapital")
        val countryRegion           = dataFromCountriesAdapter.getStringExtra("CountryRegion")
        val countrySubRegion        = dataFromCountriesAdapter.getStringExtra("CountrySubRegion")
        val countryNativeName       = dataFromCountriesAdapter.getStringExtra("CountryNativeName")
        val countryPopulation       = dataFromCountriesAdapter.getStringExtra("CountryPopulation")
        val countryCallingCode      = dataFromCountriesAdapter.getStringExtra("CountryCallingCode")
        val countryTimeZone         = dataFromCountriesAdapter.getStringExtra("CountryTimeZone")

        country_selected_name_value.text = countryName

        assigningCountryValuesToActivity(
            countryCapital,
            countryName,
            countryRegion,
            countrySubRegion,
            countryNativeName,
            countryTimeZone,
            countryPopulation,
            countryCallingCode
        )

    }

    /*
    * Getting data via Intent and assigning it to Activity
    * Also, checking if data is empty or contains '[]'
    * */
    @SuppressLint("SetTextI18n")
    private fun assigningCountryValuesToActivity(
        countryCapital: String?,
        countryName: String?,
        countryRegion: String?,
        countrySubRegion: String?,
        countryNativeName: String?,
        countryTimeZone: String?,
        countryPopulation: String?,
        countryCallingCode: String?
    ) {
        if (countryCapital == "") {
            country_selected_capital_value.text         = "No capital for $countryName"
        } else {
            country_selected_capital_value.text         = countryCapital
        }
        if (countryRegion == "") {
            country_selected_region_value.text          = "No region for $countryName"
        } else {
            country_selected_region_value.text          = countryRegion
        }
        if (countrySubRegion == "") {
            country_selected_sub_region_value.text      = "No sub-region for $countryName"
        } else {
            country_selected_sub_region_value.text      = countrySubRegion
        }
        if (countryNativeName == "") {
            country_selected_native_name_value.text     = "No native name for $countryName"
        } else {
            country_selected_native_name_value.text     = countryNativeName
        }
        if (countryTimeZone == "" || countryTimeZone    == "[]") {
            country_selected_time_zone_value.text       = "No timeZone for $countryName"
        } else {
            country_selected_time_zone_value.text       =
                countryTimeZone!!.removePrefix("[").removeSuffix("]")
        }
        if (countryPopulation == "") {
            country_selected_population_value.text      = "No population count for $countryName"
        } else {
            country_selected_population_value.text      = countryPopulation
        }
        if (countryCallingCode == "" || countryCallingCode == "[]") {
            country_selected_calling_code_value.text    = "No calling code for $countryName"
        } else {
            country_selected_calling_code_value.text    =
                countryCallingCode!!.removePrefix("[").removeSuffix("]")
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onBackPressed()
        return true
    }

    fun goBackToMainActivity(view: View) {
        super.onBackPressed()
    }
}