package com.ashchuk.popularmoviesone.data.pojo;

/**
 * Created by Artyom Koshko (@ashchuk) on 25.03.2018.
 * Generated by http://www.jsonschema2pojo.org/
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Videos {

    @SerializedName("results")
    @Expose
    private List<Result> results = null;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

}