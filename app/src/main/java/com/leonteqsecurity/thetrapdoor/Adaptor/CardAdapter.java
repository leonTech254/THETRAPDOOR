package com.leonteqsecurity.thetrapdoor.Adaptor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.leonteqsecurity.thetrapdoor.Model.CardItem;
import com.leonteqsecurity.thetrapdoor.R;

public class CardAdapter extends ArrayAdapter<CardItem> {

    private final Context context;
    private final CardItem[] cardItems;
    private int lastClickedPosition = -1;

    public CardAdapter(Context context, CardItem[] cardItems) {
        super(context, R.layout.card_item, cardItems);
        this.context = context;
        this.cardItems = cardItems;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("ViewHolder") final View rowView = inflater.inflate(R.layout.card_item, parent, false);

        ImageView iconImageView = rowView.findViewById(R.id.cardIcon);
        TextView titleTextView = rowView.findViewById(R.id.cardTitle);
        TextView descriptionTextView = rowView.findViewById(R.id.cardDescription);

        iconImageView.setImageResource(cardItems[position].getIconResource());
        titleTextView.setText(cardItems[position].getTitle());
        descriptionTextView.setText(cardItems[position].getDescription());

        // Set an OnClickListener for the card item
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle card click event here
                String clickedCardTitle = cardItems[position].getTitle();
                Toast.makeText(context, "Clicked on " + clickedCardTitle, Toast.LENGTH_SHORT).show();
            }
        });

        return rowView;
    }


}
