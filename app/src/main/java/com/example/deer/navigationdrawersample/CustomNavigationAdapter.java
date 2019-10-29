package com.example.deer.navigationdrawersample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomNavigationAdapter extends BaseAdapter {

    private OnClickListener onClickListener;
    private final Integer[] viewResId= {
            R.id.nav_all,
            R.id.nav_one,
            R.id.nav_two,
            R.id.nav_three,
            R.id.nav_four,
            R.id.nav_five,
            R.id.nav_six,
            R.id.nav_seven
    };
    private boolean isItemSelect = false;

    public CustomNavigationAdapter(OnClickListener onClickListener) {
        super();
        this.onClickListener = onClickListener;
    }

    @Override
    public int getCount() {
        return viewResId.length;
    }

    @Override
    public Object getItem(int position) {
        return viewResId[position];
    }

    @Override
    public long getItemId(int position) {
        return viewResId[position].longValue();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SelectViewHolder viewHolder;

        if(convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_custom_menu, parent, false);
            viewHolder = new SelectViewHolder(convertView, position);
            viewHolder.putData(position);
            convertView.setTag(viewHolder);
        }
        else {
            ((SelectViewHolder)convertView.getTag()).putData(position);
        }

        return convertView;
    }

    class SelectViewHolder {

        private ImageView icon;
        private TextView title;

        SelectViewHolder(final View view, final int position) {
            icon = view.findViewById(R.id.icon);
            title = view.findViewById(R.id.title);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!isItemSelect && v != null) {
                        isItemSelect = true;
                        ViewGroup viewGroup = (ViewGroup) v;
                        viewGroup.setSelected(true);
                        for (int index = 0; index < viewGroup.getChildCount(); index++) {
                            if(viewGroup.getChildAt(index) != null)
                                viewGroup.getChildAt(index).setSelected(true);
                        }
                        onClickListener.onClick(SelectionData.getSelectionItem(viewResId[position]));
                        resetItemSelected();
                    }
                }
            });
        }

        void putData(int position) {
            SelectionData selectionData = SelectionData.getSelectionItem(viewResId[position]);
            icon.setImageResource(selectionData.getIconResId());
            title.setText(selectionData.getTitleResId());
        }
    }

    private void resetItemSelected() {
        isItemSelect = false;
    }

    interface OnClickListener {
        void onClick(SelectionData selectionData);
    }
}
