package com.view.customview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DogAdapter extends ArrayAdapter<String>
{

    public DogAdapter(@NonNull Context context, int resource, List<String> dogs)
    {
        super(context, resource, dogs);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View itemView, @NonNull ViewGroup parent)
    {
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dog_item, parent, false);
        TextView dogName = itemView.findViewById(R.id.tv_dog_name);
        dogName.setText(getItem(position));
        return itemView;
    }

}
