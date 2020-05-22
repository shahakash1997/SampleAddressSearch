package com.sample.sampleaddresssearch.network;

import com.sample.sampleaddresssearch.data.schema.AddressResponse;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("/compassLocation/rest/address/autocomplete")
    Single<Response<AddressResponse>> getAddressesBasedOnQuery(@Query("queryString") String queryString ,@Query("city") String city);
}
