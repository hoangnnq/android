package com.example.onthi2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView mTextView;
    android.widget.ListView ListView;
    MyAdapter lstTaxiAdapter;
    ArrayList<Contact> lstTaxi;
    Button add_1;
    int selectedid=-1;
    MyDB abc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView =findViewById(R.id.editTextTextPersonName);
        ListView=findViewById(R.id.listitem);
        add_1=findViewById(R.id.add_1);

        registerForContextMenu(ListView);


        abc=new MyDB(this,"ContactDB",null,1);
//        abc.addTaxi(new Contact(null,"191201082","Nguyễn Anh Tuấn"));
//        abc.addTaxi(new Contact(null,"191201082","Abc"));
//        abc.addTaxi(new Contact(null,"191201082","Bcd"));
        lstTaxi=abc.getAllContact();
        ListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                selectedid=position;
                Toast.makeText(MainActivity.this,lstTaxi.get(position).getId().toString(), Toast.LENGTH_SHORT).show();
                return false;
            }

        });
        lstTaxiAdapter=new MyAdapter(this,lstTaxi);
        ListView.setAdapter(lstTaxiAdapter);
        add_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, Add_Contact.class);
                startActivityForResult(myIntent, 100);
            }
        });

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater=new MenuInflater(this);
        inflater.inflate(R.menu.menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

//            case R.id.Add:
//                Intent intent1=new Intent(MainActivity.this,Contact.class);
//                startActivityForResult(intent1,100);
//                break;
            case R.id.Delete:
                AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
                //Thiết lập tiêu đề
                b.setTitle("Xác nhận");
                //
                b.setMessage("Bạn có đồng ý xóa không?");
                // Nút Đồng ý
                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        abc.deleteContact(lstTaxi.get(selectedid).getId());
                        lstTaxiAdapter.RemoveItem(selectedid);
                        lstTaxiAdapter.notifyDataSetChanged();
                        ListView.setAdapter(lstTaxiAdapter);
                    }
                });
                //Nút hủy
                b.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                ////Tạo dialog
                AlertDialog al = b.create();
                //Hiển thị
                al.show();
        }

        return super.onContextItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle bundle= data.getExtras();
        String MaSv=bundle.getString("MaSv");
        String TenSv=bundle.getString("TenSv");
        if(requestCode==100 && resultCode==200){
            abc.addTaxi(new Contact(null,MaSv,TenSv));
        }
        UpdateArr();
        UpdateContact();
    }
    public void UpdateArr(){
        abc=new MyDB(this,"ContactDB",null,1);
        lstTaxi= new ArrayList<>();
        lstTaxi=abc.getAllContact();
    }
    public void UpdateContact(){
        lstTaxiAdapter=new MyAdapter(MainActivity.this,lstTaxi);
        ListView.setAdapter(lstTaxiAdapter);
    }
}