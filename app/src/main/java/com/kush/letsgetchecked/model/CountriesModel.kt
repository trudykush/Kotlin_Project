package com.kush.letsgetchecked.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

/**
 * Created by Kush on 17/09/2020.
 */
public class CountriesModel {

    @SerializedName("name")
    @Expose
    private var name: String? = null

    @SerializedName("topLevelDomain")
    @Expose
    private var topLevelDomain: List<String?>? = null

    @SerializedName("alpha2Code")
    @Expose
    private var alpha2Code: String? = null

    @SerializedName("alpha3Code")
    @Expose
    private var alpha3Code: String? = null

    @SerializedName("callingCodes")
    @Expose
    private var callingCodes: List<String?>? = null

    @SerializedName("capital")
    @Expose
    private var capital: String? = null

    @SerializedName("altSpellings")
    @Expose
    private var altSpellings: List<String?>? = null

    @SerializedName("region")
    @Expose
    private var region: String? = null

    @SerializedName("subregion")
    @Expose
    private var subregion: String? = null

    @SerializedName("population")
    @Expose
    private var population: Int? = null

    @SerializedName("latlng")
    @Expose
    private var latlng: List<Double?>? = null

    @SerializedName("demonym")
    @Expose
    private var demonym: String? = null

    @SerializedName("area")
    @Expose
    private var area: Double? = null

    @SerializedName("gini")
    @Expose
    private var gini: Double? = null

    @SerializedName("timezones")
    @Expose
    private var timezones: List<String?>? = null

    @SerializedName("borders")
    @Expose
    private var borders: List<String?>? = null

    @SerializedName("nativeName")
    @Expose
    private var nativeName: String? = null

    @SerializedName("numericCode")
    @Expose
    private var numericCode: String? = null

    @SerializedName("currencies")
    @Expose
    private var currencies: List<String?>? = null

    @SerializedName("languages")
    @Expose
    private var languages: List<String?>? = null

    @SerializedName("translations")
    @Expose
    private var translations: Translations? = null

    @SerializedName("relevance")
    @Expose
    private var relevance: String? = null

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getTopLevelDomain(): List<String?>? {
        return topLevelDomain
    }

    fun setTopLevelDomain(topLevelDomain: List<String?>?) {
        this.topLevelDomain = topLevelDomain
    }

    fun getAlpha2Code(): String? {
        return alpha2Code
    }

    fun setAlpha2Code(alpha2Code: String?) {
        this.alpha2Code = alpha2Code
    }

    fun getAlpha3Code(): String? {
        return alpha3Code
    }

    fun setAlpha3Code(alpha3Code: String?) {
        this.alpha3Code = alpha3Code
    }

    fun getCallingCodes(): List<String?>? {
        return callingCodes
    }

    fun setCallingCodes(callingCodes: List<String?>?) {
        this.callingCodes = callingCodes
    }

    fun getCapital(): String? {
        return capital
    }

    fun setCapital(capital: String?) {
        this.capital = capital
    }

    fun getAltSpellings(): List<String?>? {
        return altSpellings
    }

    fun setAltSpellings(altSpellings: List<String?>?) {
        this.altSpellings = altSpellings
    }

    fun getRegion(): String? {
        return region
    }

    fun setRegion(region: String?) {
        this.region = region
    }

    fun getSubregion(): String? {
        return subregion
    }

    fun setSubregion(subregion: String?) {
        this.subregion = subregion
    }

    fun getPopulation(): Int? {
        return population
    }

    fun setPopulation(population: Int?) {
        this.population = population
    }

    fun getLatlng(): List<Double?>? {
        return latlng
    }

    fun setLatlng(latlng: List<Double?>?) {
        this.latlng = latlng
    }

    fun getDemonym(): String? {
        return demonym
    }

    fun setDemonym(demonym: String?) {
        this.demonym = demonym
    }

    fun getArea(): Double? {
        return area
    }

    fun setArea(area: Double?) {
        this.area = area
    }

    fun getGini(): Double? {
        return gini
    }

    fun setGini(gini: Double?) {
        this.gini = gini
    }

    fun getTimezones(): List<String?>? {
        return timezones
    }

    fun setTimezones(timezones: List<String?>?) {
        this.timezones = timezones
    }

    fun getBorders(): List<String?>? {
        return borders
    }

    fun setBorders(borders: List<String?>?) {
        this.borders = borders
    }

    fun getNativeName(): String? {
        return nativeName
    }

    fun setNativeName(nativeName: String?) {
        this.nativeName = nativeName
    }

    fun getNumericCode(): String? {
        return numericCode
    }

    fun setNumericCode(numericCode: String?) {
        this.numericCode = numericCode
    }

    fun getCurrencies(): List<String?>? {
        return currencies
    }

    fun setCurrencies(currencies: List<String?>?) {
        this.currencies = currencies
    }

    fun getLanguages(): List<String?>? {
        return languages
    }

    fun setLanguages(languages: List<String?>?) {
        this.languages = languages
    }

    fun getTranslations(): Translations? {
        return translations
    }

    fun setTranslations(translations: Translations?) {
        this.translations = translations
    }

    fun getRelevance(): String? {
        return relevance
    }

    fun setRelevance(relevance: String?) {
        this.relevance = relevance
    }
}