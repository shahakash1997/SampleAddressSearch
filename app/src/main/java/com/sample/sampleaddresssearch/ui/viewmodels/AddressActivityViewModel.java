package com.sample.sampleaddresssearch.ui.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sample.sampleaddresssearch.config.Config;
import com.sample.sampleaddresssearch.data.schema.Address;
import com.sample.sampleaddresssearch.data.schema.AddressResponse;
import com.sample.sampleaddresssearch.network.APIClient;
import com.sample.sampleaddresssearch.utils.Tracer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class AddressActivityViewModel extends ViewModel {
    private static final String TAG = Config.logger + "AddressActivityViewMode";
    private MutableLiveData<List<Address>> mutableAddressList;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void init() {
        mutableAddressList = new MutableLiveData<>();
        mutableAddressList.setValue(new ArrayList<>());
    }

    public LiveData<List<Address>> getAddressList() {
        return mutableAddressList;
    }

    public void getAddressesBasedOnQuery(@NonNull String city, String queryString) {
        Tracer.debug(TAG, " getAddressesBasedOnQuery" + " " + city + " " + queryString);
        APIClient.getInstance().getRetrofitAdapter().getAddressesBasedOnQuery(queryString, city)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new SingleObserver<Response<AddressResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(Response<AddressResponse> response) {
                        Tracer.debug(TAG, " onSuccess" + " " + response.code());
                        if (response.isSuccessful()) {
                            if (response.body() != null && response.body().getData() != null && response.body().getData().getAddress() != null) {
                                List<Address> addresses = mutableAddressList.getValue();
                                if (addresses != null) {
                                    addresses.addAll(response.body().getData().getAddress());
                                    mutableAddressList.postValue(addresses);
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof IOException)
                            Tracer.debug(TAG," onError"+" No Connection");

                    }
                });
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();

    }
}
