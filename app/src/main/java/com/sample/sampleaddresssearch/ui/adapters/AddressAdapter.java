package com.sample.sampleaddresssearch.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.sample.sampleaddresssearch.config.Config;
import com.sample.sampleaddresssearch.data.schema.Address;
import com.sample.sampleaddresssearch.databinding.AutocompleteItemBinding;
import com.sample.sampleaddresssearch.utils.Tracer;

import java.util.ArrayList;
import java.util.List;

public class AddressAdapter extends ArrayAdapter<Address> implements Filterable {
    private static final String TAG = Config.logger + "AddressAdapter";
    private Context context;
    private int resourceId;
    private List<Address> items, tempItems, suggestions;
    private Filter addressFilter = new Filter() {

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            Tracer.debug(TAG," convertResultToString"+" ");
            Address address = (Address) resultValue;
            return address.getAddressString();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                suggestions.clear();
                for (Address address : tempItems) {
                    if (address.getAddressString().toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                        suggestions.add(address);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            }
            else return new FilterResults();

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ArrayList<Address> tempAddress = (ArrayList<Address>) results.values;
            if (results.count >0)
            {
                clear();
                for (Address address : tempAddress)
                {
                    add(address);
                }
                notifyDataSetChanged();
            }
            else
            {
                clear();
                notifyDataSetChanged();

            }

        }
    };

    public AddressAdapter(@NonNull Context context, int resource, @NonNull List<Address> address) {
        super(context, resource, address);
        this.items = address;
        this.context = context;
        this.resourceId = resource;
        tempItems = new ArrayList<>(address);
        suggestions = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Nullable
    @Override
    public Address getItem(int position) {
        return items.get(position);

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        AutocompleteItemBinding binding = DataBindingUtil.inflate(inflater, resourceId, parent, false);
        Address address = getItem(position);
        binding.setAddress(address);
        return binding.getRoot();

    }

    @NonNull
    @Override
    public Filter getFilter() {
        return addressFilter;
    }
}
