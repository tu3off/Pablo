package com.tu3off;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import ua.com.tu3off.pablo.adapter.VHAdapter;
import ua.com.tu3off.pablo.utils.TypefaceUtils;

public class SimpleAdapter extends VHAdapter<String, SimpleAdapter.VH> {

    public SimpleAdapter(Context context, List<String> items) {
        super(context, items, R.layout.item_simple);
    }

    @Override
    protected VH createViewHolder(View inflatedView) {
        return new VH(inflatedView);
    }

    @Override
    protected void bindView(View convertView, int position, VH vh, String item) {
        vh.tvSimple.setText(item);
    }

    protected final static class VH extends VHAdapter.ViewHolder {

        private TextView tvSimple;

        public VH(View inflatedView) {
            super(inflatedView);
            tvSimple = (TextView) inflatedView.findViewById(R.id.tvSimple);
            TypefaceUtils.setTypeface(tvSimple, "assets/fonts/neuropol.TTF");
        }
    }
}
