package com.kush.letsgetchecked.adapters

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Filter
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.kush.letsgetchecked.R
import com.kush.letsgetchecked.model.CountriesModel
import com.kush.letsgetchecked.ui.CountryDetails
import com.kush.letsgetchecked.ui.MainActivity
import kotlinx.android.synthetic.main.country_list.view.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Kush on 17/09/2020.
 */
class CountriesRecyclerAdapter(
    private val context: Context,
    private var allCountryDetails: List<CountriesModel>
) :
    RecyclerView.Adapter<CountriesRecyclerAdapter.MyViewHolder>() {

    private var selectedPosition =  -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflateView = parent.inflate(R.layout.country_list, false)
        return MyViewHolder(inflateView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.countryName.text = allCountryDetails[position].getName().toString()

        if (allCountryDetails[position].getCapital().toString() == "") {
            holder.countryCapital.visibility        = View.GONE
            holder.countryCapitalHeading.visibility = View.GONE
        } else {
            holder.countryCapital.visibility        = View.VISIBLE
            holder.countryCapitalHeading.visibility = View.VISIBLE
            holder.countryCapital.text              = allCountryDetails[position].getCapital().toString()
        }

        if (allCountryDetails[position].getRegion().toString() == "") {
            holder.countryRegionHeading.visibility  = View.GONE
            holder.countryRegion.visibility         = View.GONE
        } else {
            holder.countryRegionHeading.visibility  = View.VISIBLE
            holder.countryRegion.visibility         = View.VISIBLE
            holder.countryRegion.text               = allCountryDetails[position].getRegion().toString()
        }


        val sharedPreferences: SharedPreferences = context.getSharedPreferences(
            "FavouriteCountry",
            0
        )

        holder.favCountryCheckbox.isChecked = false
        val countryName: String? = sharedPreferences.getString("CountryName", "")
        if (allCountryDetails[position].getName().equals(countryName)) {
            holder.favCountryCheckbox.isChecked = true
        }

        holder.countryListCardView.setOnClickListener{
            Toast.makeText(context, holder.countryName.text, Toast.LENGTH_SHORT).show()

            goToCountryDetails(position)
        }

        holder.favCountryCheckbox.setOnClickListener{

            selectedPosition = position

            val savingFavCountry: SharedPreferences = context.getSharedPreferences(
                "FavouriteCountry",
                0
            )
            val sharedEditor: SharedPreferences.Editor = savingFavCountry.edit()
            sharedEditor.putString("CountryName", allCountryDetails[position].getName().toString())
            if (allCountryDetails[position].getCapital().toString() != "") {
                sharedEditor.putString(
                    "CountryCapital",
                    allCountryDetails[position].getCapital().toString()
                )
            } else {
                sharedEditor.putString("CountryCapital", "")
            }
            sharedEditor.putString(
                "CountryRegion", allCountryDetails[position].getRegion().toString()
            )
            sharedEditor.apply()

            notifyDataSetChanged()
            (context as MainActivity).lookForAnyFavouriteCountry()
            context.refreshingRecyclerViewBySwipe()

        }

    }

    /*
    * Parse data required for country details and transfer it to the CountryDetails Activity
    * */
    private fun goToCountryDetails(position: Int) {
        val intentToCountryDetails = Intent(context, CountryDetails::class.java)
        intentToCountryDetails.putExtra(
            "CountryName",
            allCountryDetails[position].getName().toString()
        )
        intentToCountryDetails.putExtra(
            "CountryCapital",
            allCountryDetails[position].getCapital().toString()
        )
        intentToCountryDetails.putExtra(
            "CountryRegion",
            allCountryDetails[position].getRegion().toString()
        )
        intentToCountryDetails.putExtra(
            "CountrySubRegion",
            allCountryDetails[position].getSubregion().toString()
        )
        intentToCountryDetails.putExtra(
            "CountryNativeName",
            allCountryDetails[position].getNativeName().toString()
        )
        intentToCountryDetails.putExtra(
            "CountryPopulation",
            allCountryDetails[position].getPopulation().toString()
        )
        intentToCountryDetails.putExtra(
            "CountryCallingCode",
            allCountryDetails[position].getCallingCodes().toString()
        )
        intentToCountryDetails.putExtra(
            "CountryTimeZone",
            allCountryDetails[position].getTimezones().toString()
        )
        context.startActivity(intentToCountryDetails)
    }

    override fun getItemCount(): Int {
        return allCountryDetails.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var countryName: TextView           = itemView.country_Name_TV
        var countryCapital: TextView        = itemView.country_Capital_TV
        var countryRegion: TextView         = itemView.country_Region_TV

        var countryRegionHeading: TextView  = itemView.country_region_heading
        var countryCapitalHeading: TextView = itemView.country_capital_heading

        var favCountryCheckbox: CheckBox    = itemView.favourite_Country_CheckBox

        var countryListCardView: CardView   = itemView.country_list_cardView

    }

    /*
    * This helps to filter the recyclerView to search country in MainActivity
    * */
    fun getFilter(): Filter? {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults? {
                val charString = charSequence.toString()
                allCountryDetails = if (charString.isEmpty()) {
                    allCountryDetails
                } else {
                    val filteredList: MutableList<CountriesModel> = ArrayList()
                    for (row in allCountryDetails) {
                        if (row.getName()!!.toLowerCase(Locale.ROOT)
                                .contains(charString.toLowerCase(Locale.ROOT)) || row.getName()!!
                                .contains(charSequence)
                        ) {
                            filteredList.add(row)
                        }
                    }
                    filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = allCountryDetails
                return filterResults
            }

            override fun publishResults(
                charSequence: CharSequence?,
                filterResults: FilterResults
            ) {
                allCountryDetails = filterResults.values as List<CountriesModel>
                notifyDataSetChanged()
            }
        }
    }

}