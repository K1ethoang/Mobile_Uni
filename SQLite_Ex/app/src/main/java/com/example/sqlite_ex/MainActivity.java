package com.example.sqlite_ex;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.model.Product;
import com.example.sqlite_ex.databinding.ActivityMainBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import kotlin.internal.ProgressionUtilKt;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    public static final String DB_NAME = "product_db.db";
    public static final String DB_FOLDER = "/databases/";
    public static final String TBL_NAME = "Product";
    public static final String[] HEADER_ROW = {"ProductId", "ProductName", "ProductPrice"};
    public static SQLiteDatabase db;
    ArrayAdapter<Product> adapter;
    ArrayList<Product> products;
    Product selectedProduct = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        copyDb();
        openDb();
//        loadData();
        addEvents();
        registerForContextMenu(binding.lvProduct);
    }

    private void addEvents()
    {
        binding.lvProduct.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long id) {
                selectedProduct = adapter.getItem(i);
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void openDb()
    {
        db = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
    }

    private void loadData()
    {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getDataFromDb());

        binding.lvProduct.setAdapter(adapter);

    }

    private ArrayList<Product> getDataFromDb()
    {
        products = new ArrayList<>();
        // Select data
        // Method 1: using rawQuery()
//        Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_NAME + " WHERE " + HEADER_ROW[1] + " LIKE ?", new String[]{"%H%"});

        // Method 2: using query()
        Cursor cursor = db.query(TBL_NAME, null,null,null,null,null,null);
//        Cursor cursor = db.query(TBL_NAME,null, HEADER_ROW[1] + " LIKE ?", new String[]{"%H%"}, null,null, null);
//        Cursor cursor = db.query(TBL_NAME, new String[]{HEADER_ROW[1]}, null, null ,null, null, null);

//        cursor.moveToNext();
//        Log.i("cusor", cursor.getString(0));

        while(cursor.moveToNext())
        {
            products.add(new Product(cursor.getInt(0),cursor.getString(1), cursor.getDouble(2)));
        }

        cursor.close();
        return products;
    }

    private boolean processCopy()
    {
        String dbPath = getApplicationInfo().dataDir + DB_FOLDER + DB_NAME;

        try {
            InputStream inputStream = getAssets().open(DB_NAME);
            File f = new File(getApplicationInfo().dataDir + DB_FOLDER);

            if(!f.exists())
            {
                f.mkdir();
            }

            OutputStream outputStream = new FileOutputStream(dbPath);
            byte[] buffer = new byte[1024];
            int length;

            while((length = inputStream.read(buffer))>0)
            {
                outputStream.write(buffer, 0, length);
            }

            outputStream.flush();
            outputStream.close();
            inputStream.close();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void copyDb()
    {
        File dbFile = getDatabasePath(DB_NAME);
        if(!dbFile.exists())
        {
            if(processCopy())
                Toast.makeText(this,"Copy successfully",Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this,"Copy failure",Toast.LENGTH_SHORT).show();
        }
    }

    // ====================================MENU======================================
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mnAdd)
        {
            Intent intent = new Intent(this, AddActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mnEdit){
            Intent intent = new Intent(this, EditActivity.class);

            if(selectedProduct != null)
            {
                intent.putExtra("product", selectedProduct);
            }
            startActivity(intent);
        }
        else if(item.getItemId() == R.id.mnDelete)
        {

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Confirm to delete");
            builder.setIcon(android.R.drawable.ic_delete);
            builder.setMessage("Are you sure to delete product '" + selectedProduct.getName() + "' ?");

            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    int deleteOfRows = db.delete(TBL_NAME,HEADER_ROW[0] + "=?",new String[]{String.valueOf(selectedProduct.getId())});

                    if(deleteOfRows > 0)
                    {
                        loadData();
                        Toast.makeText(MainActivity.this, "Success delete", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                }
            });

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    dialog.dismiss();
                }
            });

            Dialog dialog = builder.create();
            dialog.show();
        }
        return super.onContextItemSelected(item);
    }
}