package com.kush.letsgetchecked.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


/**
 * Created by Kush on 17/09/2020.
 */
public class Translations {

    @SerializedName("de")
    @Expose
    private var de: String? = null

    @SerializedName("es")
    @Expose
    private var es: String? = null

    @SerializedName("fr")
    @Expose
    private var fr: String? = null

    @SerializedName("ja")
    @Expose
    private var ja: String? = null

    @SerializedName("it")
    @Expose
    private var it: String? = null

    fun getDe(): String? {
        return de
    }

    fun setDe(de: String?) {
        this.de = de
    }

    fun getEs(): String? {
        return es
    }

    fun setEs(es: String?) {
        this.es = es
    }

    fun getFr(): String? {
        return fr
    }

    fun setFr(fr: String?) {
        this.fr = fr
    }

    fun getJa(): String? {
        return ja
    }

    fun setJa(ja: String?) {
        this.ja = ja
    }

    fun getIt(): String? {
        return it
    }

    fun setIt(it: String?) {
        this.it = it
    }
}