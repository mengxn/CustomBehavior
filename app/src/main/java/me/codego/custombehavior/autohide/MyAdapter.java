package me.codego.custombehavior.autohide;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mengxn on 17-2-14.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context mContext;
    private List<String> mDataList;

    public MyAdapter(Context context) {
        mContext = context;
    }

    public void setDataList(List<String> dataList) {
        mDataList = dataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(android.R.layout.simple_expandable_list_item_1, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mTextView.setText(mDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }
}
