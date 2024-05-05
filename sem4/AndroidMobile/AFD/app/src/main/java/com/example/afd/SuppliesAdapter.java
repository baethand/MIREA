package com.example.afd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SuppliesAdapter extends BaseAdapter {

    private Context context;
    private List<Supplies> suppliesList;
    private List<Supplies> checkedSupplies;
    private LayoutInflater inflater;

    public SuppliesAdapter(Context context, List<Supplies> suppliesList) {
        this.context = context;
        this.suppliesList = suppliesList;
        this.checkedSupplies = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return suppliesList.size();
    }

    @Override
    public Supplies getItem(int position) {
        return suppliesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Supplies supplies = getItem(position);

        viewHolder.textViewId.setText("ID: " + supplies.getId());
        viewHolder.textViewName.setText(supplies.getName());
        viewHolder.textViewPrice.setText("$" + supplies.getPrice());
        viewHolder.imageView.setImageResource(supplies.getImage());

        viewHolder.checkBox.setChecked(checkedSupplies.contains(supplies));
        viewHolder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkedSupplies.add(supplies);
            } else {
                checkedSupplies.remove(supplies);
            }
            updateFooterText(checkedSupplies.size());
        });

        return convertView;
    }

    public List<Supplies> getCheckedSupplies() {
        return new ArrayList<>(checkedSupplies);
    }

    private void updateFooterText(int count) {
        TextView textViewCheckedItemsCount = ((MainActivity) context).findViewById(R.id.textViewCheckedItemsCount);
        textViewCheckedItemsCount.setText("Checked items: " + count);
    }

    private static class ViewHolder {
        TextView textViewId;
        TextView textViewName;
        TextView textViewPrice;
        ImageView imageView;
        CheckBox checkBox;

        ViewHolder(View view) {
            textViewId = view.findViewById(R.id.textViewId);
            textViewName = view.findViewById(R.id.textViewName);
            textViewPrice = view.findViewById(R.id.textViewPrice);
            imageView = view.findViewById(R.id.imageView);
            checkBox = view.findViewById(R.id.checkBox);
        }
    }
    public double calculateTotalPrice(List<Supplies> checkedSupplies) {
        double totalPrice = 0.0;
        for (Supplies supplies : checkedSupplies) {
            totalPrice += supplies.getPrice();
        }
        return totalPrice;
    }


}