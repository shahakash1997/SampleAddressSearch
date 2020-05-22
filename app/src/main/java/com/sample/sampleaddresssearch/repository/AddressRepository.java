package com.sample.sampleaddresssearch.repository;

import androidx.lifecycle.MutableLiveData;

import com.sample.sampleaddresssearch.data.schema.Address;
import com.sample.sampleaddresssearch.data.schema.AddressResponse;
import com.sample.sampleaddresssearch.network.APIClient;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class AddressRepository {
    private static volatile AddressRepository _instance;

    public static AddressRepository getInstance()
    {
        if (_instance == null) {
            synchronized (AddressRepository.class) {
                if (_instance == null)
                {
                    _instance  = new AddressRepository();
                }
            }
        }
        return _instance;
    }


    private AddressRepository(){
        
    }
}
