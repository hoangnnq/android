package com.example.onthi2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter implements Filterable {

    private Activity activity;
    private ArrayList<Contact> data;
    private LayoutInflater inflater;
    private ArrayList<Contact> databackup;

    public MyAdapter(Activity activity,ArrayList<Contact>data){
        this.activity=activity;
        this.data=data;
        this.inflater=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void RemoveItem(int position){
        data.remove(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        Contact s=data.get(i);
        return s.getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v =view;
        if(v==null){
            v=inflater.inflate(R.layout.list_item,null);
            TextView txtSoxe=v.findViewById(R.id.eMaSV);
            txtSoxe.setText("Mã sinh viên :"+data.get(i).getMaSv());

            TextView txtQuangDuong=v.findViewById(R.id.eTenSV);
            txtQuangDuong.setText("Tên sinh viên :"+data.get(i).getTenSv());


//            CheckBox chkbox= v.findViewById(R.id.checkBox);
//            chkbox.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    data.get(position).setStatus(chkbox.isChecked());
//                }
//            });
//            chkbox.setChecked(data.get(position).isStatus());
        }
        return v;
    }

    @Override
    public Filter getFilter() {
        Filter f= new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults fr=new FilterResults();
                if(databackup==null){
                    databackup=new ArrayList<>(data);
                }
                if(constraint==null || constraint.length()==0){
                    fr.count=databackup.size();
                    fr.values=databackup;
                }
                else{
                    ArrayList<Contact> newdata=new ArrayList<>();
                    for(Contact u:databackup){
                        if(u.getTenSv().toLowerCase().contains(
                                constraint.toString().toLowerCase()))
                            newdata.add(u);
                        fr.count= newdata.size();
                        fr.values=newdata;
                    }
                }
                return fr;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                data=(ArrayList<Contact>)results.values;
                notifyDataSetChanged();

            }
        };
        return f;
    }
}
