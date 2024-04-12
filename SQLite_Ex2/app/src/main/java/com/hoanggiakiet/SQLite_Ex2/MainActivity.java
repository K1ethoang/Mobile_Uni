package com.hoanggiakiet.SQLite_Ex2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.people.PeopleManager;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.hoanggiakiet.SQLite_Ex2.databinding.ActivityMainBinding;
import com.hoanggiakiet.adapters.ProductAdapter;
import com.hoanggiakiet.models.Product;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    Databases db;
    ProductAdapter adapter;
    ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        prepareDb();
        loadData();
        addEvents();
    }

    private void addEvents()
    {
        binding.fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.dialog_add);

                EditText edtName = dialog.findViewById(R.id.edtName);
                EditText edtPrice = dialog.findViewById(R.id.edtPrice);


                Button btnSave = dialog.findViewById(R.id.btnSave);
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Insert data
                        String name = edtName.getText().toString();
                        Double price = Double.valueOf(edtPrice.getText().toString());
                        db.execSql("INSERT INTO " + Databases.TBL_NAME + " VALUES (null, '" + name +"', " + price + ")");
                        loadData();
                        dialog.dismiss();
                    }
                });

                Button btnCancel = dialog.findViewById(R.id.btnCancel);
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                dialog.show();
            }
        });
    }

    private void prepareDb()
    {
        db = new Databases(this);
        db.createSampleData();
    }

    private void loadData()
    {
        adapter = new ProductAdapter(MainActivity.this, R.layout.item_list, getDataFromDb());
        binding.lvProduct.setAdapter(adapter);
    }

    private List<Product> getDataFromDb() {
        products = new ArrayList<>();
        Cursor cursor = db.queryData("SELECT * FROM " + db.TBL_NAME);

        while(cursor.moveToNext())
        {
            products.add(new Product(cursor.getInt(0), cursor.getString(1),cursor.getDouble(2)));
        }

        cursor.close();
        return products;
    }

    public void openEditDialog(Product p)
    {
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.dialog_edit);

        EditText edtName = dialog.findViewById(R.id.edtName);
        edtName.setText(String.valueOf(p.getName()));

        EditText edtPrice = dialog.findViewById(R.id.edtPrice);
        edtPrice.setText(String.valueOf(p.getPrice()));

        Button btnSave = dialog.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                Double price = Double.valueOf(edtPrice.getText().toString());

                db.execSql("UPDATE " + Databases.TBL_NAME + " SET " + Databases.COL_NAME + "='" + name + "', " + Databases.COL_PRICE + "=" + price + " WHERE " + Databases.COL_CODE + "=" + p.getCode());

                loadData();
                dialog.dismiss();
            }
        });

        Button btnCancel = dialog.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
    }

    public void openDeleteDialog(Product p)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Xác nhận xoá");
        builder.setIcon(android.R.drawable.ic_delete);
        builder.setMessage("Bạn có chắc muốn xoá " + p.getName());

        builder.setNegativeButton("Xoá", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                db.execSql("DELETE FROM " + Databases.TBL_NAME + " WHERE " + Databases.COL_CODE + "=" + p.getCode());
                loadData();
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("Huỷ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        Dialog dialog = builder.create();
        dialog.show();
    }

    // ==================================MENU=================================
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnAdd)
        {
            Dialog dialog = new Dialog(MainActivity.this);
            dialog.setContentView(R.layout.dialog_add);

            EditText edtName = dialog.findViewById(R.id.edtName);
            EditText edtPrice = dialog.findViewById(R.id.edtPrice);


            Button btnSave = dialog.findViewById(R.id.btnSave);
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Insert data
                    String name = edtName.getText().toString();
                    Double price = Double.valueOf(edtPrice.getText().toString());
                    db.execSql("INSERT INTO " + Databases.TBL_NAME + " VALUES (null, '" + name +"', " + price + ")");
                    loadData();
                    dialog.dismiss();
                }
            });

            Button btnCancel = dialog.findViewById(R.id.btnCancel);
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
}