package com.kush.letsgetchecked.ui

import android.app.SearchManager
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kush.letsgetchecked.R
import com.kush.letsgetchecked.adapters.CountriesRecyclerAdapter
import com.kush.letsgetchecked.model.CountriesModel
import com.kush.letsgetchecked.retrofit.serviceBuilders.CountriesServiceBuilders
import com.kush.letsgetchecked.retrofit.urls.CountriesURL
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fav_country_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val TAG                                 = MainActivity::class.qualifiedName
    var countriesAdapter: CountriesRecyclerAdapter? = null
    var allCountriesRecyclerView: RecyclerView?     = null
    private var searchView: SearchView?             = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Will check, if user has marked any country as favourite
        lookForAnyFavouriteCountry()

        // Will fire up API to get all details of countries
        getCountryDetailsFromAPI()

        // Once, all checked, Refresh the recyclerView containing
        refreshingRecyclerViewBySwipe()
    }

    /*
    * While Refreshing recyclerView, keyboard is hidden if visible,
    * searchBar is cancelled if visible,
    * and API to get all country detail is fired up again, to get the latest data
    * */
    fun refreshingRecyclerViewBySwipe() {
        swipeRefreshLayout.setOnRefreshListener {

            hideKeyboardIfVisible()

            cancelSearchBarIfActive()

            getCountryDetailsFromAPI()
        }
    }

    /*
    * Cancels searchBar is visible
    * */
    private fun cancelSearchBarIfActive() {
        if (!searchView!!.isIconified) {
            searchView!!.isIconified = true
        }
    }

    /*
    * To hide the keyboard when not needed to clear the screen for users
    * */
    private fun hideKeyboardIfVisible() {
        val view: View? = this.currentFocus
        val imm: InputMethodManager =
            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        if (view != null) {
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    /*
    * Look if user has marked any country as favourite
    * */
    fun lookForAnyFavouriteCountry() {
        val context: Context = this@MainActivity.applicationContext
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(
            "FavouriteCountry",
            0
        )
        val countryName: String?    = sharedPreferences.getString("CountryName", "")
        val countryCapital: String? = sharedPreferences.getString("CountryCapital", "")
        val countryRegion: String?  = sharedPreferences.getString("CountryRegion", "")


        if (countryName == "") {
            fav_country_item.visibility                         = View.GONE
        } else {
            fav_country_item.visibility                         = View.VISIBLE
            fav_country_detail_cardView.visibility              = View.VISIBLE

            if (countryCapital == "") {
                fav_country_detail_capital_heading.visibility   = View.GONE
                fav_country_detail_Capital_TV.visibility        = View.GONE

                fav_country_detail_Name_TV.text     = countryName
                fav_country_detail_Region_TV.text   = countryRegion
            }
            else {
                fav_country_detail_capital_heading.visibility   = View.VISIBLE
                fav_country_detail_Capital_TV.visibility        = View.VISIBLE

                fav_country_detail_Name_TV.text     = countryName
                fav_country_detail_Capital_TV.text  = countryCapital
                fav_country_detail_Region_TV.text   = countryRegion
            }

            if (countryRegion == "") {
                fav_country_region_heading.visibility           = View.GONE
                fav_country_detail_Region_TV.visibility         = View.GONE

                fav_country_detail_Name_TV.text     = countryName
                fav_country_detail_Capital_TV.text  = countryCapital
            } else {
                fav_country_region_heading.visibility           = View.VISIBLE
                fav_country_detail_Region_TV.visibility         = View.VISIBLE

                fav_country_detail_Name_TV.text     = countryName
                fav_country_detail_Capital_TV.text  = countryCapital
                fav_country_detail_Region_TV.text   = countryRegion
            }
        }

        Log.d(TAG, "lookForAnyFavouriteCountry: $countryName")

    }

    /*
    * Using Retrofit to fire up API and get all details,
    * parse JSON with the help of POJO and
    * transfer data to recycler adapter.
    * */
    fun getCountryDetailsFromAPI() {

        swipeRefreshLayout.isRefreshing = true

        val countriesServiceBuilder =
            CountriesServiceBuilders(this@MainActivity)

        val countriesURL: CountriesURL =
            countriesServiceBuilder.buildService(CountriesURL::class.java)

        val allCountriesDetails: Call<List<CountriesModel>> = countriesURL.getAllCountryDetails()

        allCountriesDetails.enqueue(object : Callback<List<CountriesModel>?> {
            override fun onResponse(
                call: Call<List<CountriesModel>?>,
                response: Response<List<CountriesModel>?>
            ) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        val allDetails: List<CountriesModel>? = response.body()!!

                        allCountriesRecyclerView = country_RV
                        allCountriesRecyclerView!!.layoutManager =
                            LinearLayoutManager(this@MainActivity)
                        countriesAdapter = CountriesRecyclerAdapter(
                            this@MainActivity,
                            allDetails!!
                        )
                        allCountriesRecyclerView!!.adapter = countriesAdapter

                        countriesAdapter!!.notifyDataSetChanged()
                    }

                    swipeRefreshLayout.isRefreshing = false
                }
            }

            override fun onFailure(call: Call<List<CountriesModel>?>, t: Throwable) {
                Toast.makeText(
                    this@MainActivity,
                    "Failed to retrieve all countries", Toast.LENGTH_LONG
                ).show()
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        // Inflate the menu, this adds items to the active bar if it is present.
        menuInflater.inflate(R.menu.main_activity_header, menu)
        val searchCountry: MenuItem = menu!!.findItem(R.id.action_country_search)
        onCountrySearch(searchCountry)

        return true
    }

    /*
    * To search the country by name in recyclerview and filter it down for users
    * getFilter() from CountriesRecyclerAdapter is used to filter the content
    * */
    private fun onCountrySearch(searchCountry: MenuItem) {
        if (searchCountry != null) {
            searchView = searchCountry.actionView as SearchView
            val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager
            if (searchManager != null) {
                searchView!!.queryHint = "Search by country"
                searchView!!.setSearchableInfo(searchManager.getSearchableInfo(componentName))
                searchView!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                    override fun onQueryTextChange(query: String?): Boolean {
                        if(query == "") {
                            getCountryDetailsFromAPI()
                        } else {
                            countriesAdapter!!.getFilter()!!.filter(query)
                        }
                        return false
                    }

                    override fun onQueryTextSubmit(query: String?): Boolean {
                        if(query == "") {
                            getCountryDetailsFromAPI()
                        } else {
                            countriesAdapter!!.getFilter()!!.filter(query)
                        }
                        return false
                    }
                })
            }
        }
    }

    override fun onBackPressed() {
        // close search view on back button pressed
        if (!searchView!!.isIconified) {
            searchView!!.isIconified = true
            Toast.makeText(this, "Press back-button again to confirm exit",
                        Toast.LENGTH_SHORT).show()
            return
        }
        super.onBackPressed()
    }


}