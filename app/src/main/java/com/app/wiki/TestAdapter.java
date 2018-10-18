package com.app.wiki;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.DataModelHolder> {

    private final LayoutInflater inflater;
    private final Context context;
    private List<DataModel> dataModels;

    public TestAdapter(Context context, List<DataModel> dataModels) {
        this.inflater = LayoutInflater.from(context);
        this.dataModels = dataModels;
        this.context = context;
    }

    @Override
    public DataModelHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.adapter_flower, parent, false);
        DataModelHolder holder = new DataModelHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DataModelHolder holder, int position) {
        holder.setDataModel(dataModels.get(position));
    }


    @Override
    public int getItemCount() {
        return dataModels == null ? 0 : dataModels.size();
    }

    public void setData(DataModel dataModel) {
        dataModels.add(dataModel);
        notifyDataSetChanged();
    }

    public class DataModelHolder extends RecyclerView.ViewHolder {


        private final TextView tvName;

        public DataModelHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
        }

        public void setDataModel(final DataModel dataModel) {

            tvName.setText(dataModel.getName());
        }
    }
}

