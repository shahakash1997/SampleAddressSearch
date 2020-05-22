package com.sample.sampleaddresssearch.data.schema;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("autoCompleteRequestString")
    @Expose
    private String autoCompleteRequestString;
    @SerializedName("focusWord")
    @Expose
    private String focusWord;
    @SerializedName("addressList")
    @Expose
    private List<Address> address = null;

    public String getAutoCompleteRequestString() {
        return autoCompleteRequestString;
    }

    public void setAutoCompleteRequestString(String autoCompleteRequestString) {
        this.autoCompleteRequestString = autoCompleteRequestString;
    }

    public String getFocusWord() {
        return focusWord;
    }

    public void setFocusWord(String focusWord) {
        this.focusWord = focusWord;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

}