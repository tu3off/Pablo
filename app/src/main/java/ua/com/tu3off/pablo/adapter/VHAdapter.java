package ua.com.tu3off.pablo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <T>  a class for a list of items
 * @param <VH> a ViewHolder subclass
 */
public abstract class VHAdapter<T, VH extends VHAdapter.ViewHolder> extends BaseAdapter {

    protected final List<T> items;
    private final int layoutResId;
    private final LayoutInflater layoutInflater;

    protected abstract VH createViewHolder(View inflatedView);

    protected abstract void bindView(View convertView, int position, VH vh, T item);

    public VHAdapter(Context context, int layoutResId) {
        this(context, new ArrayList<T>(), layoutResId);
    }

    public VHAdapter(Context context, List<T> items, int layoutResId) {
        layoutInflater = LayoutInflater.from(context);
        this.items = items;
        this.layoutResId = layoutResId;
    }

    public void add(T item) {
        items.add(item);
        notifyDataSetChanged();
    }

    public void add(List<T> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void addAndClear(List<T> items) {
        this.items.clear();
        add(items);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public T getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressWarnings("unchecked")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VH vh;
        if (convertView == null) {
            convertView = layoutInflater.inflate(layoutResId, parent, false);
            vh = createViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (VH) convertView.getTag();
        }
        bindView(convertView, position, vh, getItem(position));
        return convertView;
    }

    public static abstract class ViewHolder {

        public ViewHolder(View inflatedView) {
        }

    }
}
