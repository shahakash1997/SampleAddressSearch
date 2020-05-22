package com.sample.sampleaddresssearch.ui.activities;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.sample.sampleaddresssearch.R;
import com.sample.sampleaddresssearch.config.Config;
import com.sample.sampleaddresssearch.databinding.ActivityAddressSearchBinding;
import com.sample.sampleaddresssearch.ui.adapters.AddressAdapter;
import com.sample.sampleaddresssearch.ui.viewmodels.AddressActivityViewModel;
import com.sample.sampleaddresssearch.utils.Tracer;
import com.sample.sampleaddresssearch.utils.Utility;

public class AddressSearchActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = Config.logger + "AddressSearchActivity";
    private ActivityAddressSearchBinding activityBinding;
    private AddressActivityViewModel viewModel;
    private AddressAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_address_search);
        initialize();
        setEventListener();
    }

    private void initialize() {
        activityBinding.setClickHandler(this);
        viewModel = new ViewModelProvider(this).get(AddressActivityViewModel.class);
        viewModel.init();
        adapter = new AddressAdapter(this, R.layout.autocomplete_item, viewModel.getAddressList().getValue());
        activityBinding.etAcQueryString.setAdapter(adapter);
        activityBinding.etAcQueryString.setThreshold(3);
        viewModel.getAddressList().observe(this, addresses ->
                {
                    Tracer.debug(TAG," initialize"+" event posted");
                    adapter.notifyDataSetChanged();
                });
    }

    private void setEventListener() {
        activityBinding.etAcQueryString.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                Editable city = activityBinding.etCity.getText();
                if (city != null && !Utility.isEmpty(city.toString())) {
                    viewModel.getAddressesBasedOnQuery(city.toString(), s.toString());

                } else
                    Toast.makeText(AddressSearchActivity.this, "Please Enter City First!", Toast.LENGTH_SHORT).show();
            }
        });


    }
    @Override
    public void onClick(View v) {

    }
}
