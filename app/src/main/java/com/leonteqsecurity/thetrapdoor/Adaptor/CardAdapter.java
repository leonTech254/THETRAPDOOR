package com.leonteqsecurity.thetrapdoor.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.leonteqsecurity.thetrapdoor.R;

public class CardAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final String[] cardTitles;

    public CardAdapter(Context context, String[] cardTitles) {
        super(context, R.layout.card_item, cardTitles);
        this.context = context;
        this.cardTitles = cardTitles;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.card_item, parent, false);

        TextView titleTextView = rowView.findViewById(R.id.cardTitle);
        titleTextView.setText(cardTitles[position]);

        // Set an OnClickListener for the card item
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle card click event here
                String clickedCardTitle = cardTitles[position];
                Toast.makeText(context, "Clicked on " + clickedCardTitle, Toast.LENGTH_SHORT).show();
            }
        });

        return rowView;
    }
}
