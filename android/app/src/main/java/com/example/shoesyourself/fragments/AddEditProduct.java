package com.example.shoesyourself.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoesyourself.R;
import com.example.shoesyourself.adapters.ProductAdapter;
import com.example.shoesyourself.entities.Brand;
import com.example.shoesyourself.entities.Product;
import com.example.shoesyourself.managers.BrandManager;
import com.example.shoesyourself.managers.ProductManager;
import com.example.shoesyourself.settings.Switcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;


public class AddEditProduct extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    public enum ProductAction {
        ADD,
        EDIT;
    }
    private static final String ACTION = "param1";
    private static final String ID = "param2";
    private ProductAction action;
    private String idProduct, textAddUpdate, textCancel, textTitle;
    private AppCompatActivity activity;
    private Product product;
    private ImageView selectedImage;
    private Button btnSelectImg, btnSaveEdit, btnCancel;
    Spinner spCategory;
    TextView tvTitle, tvError;
    EditText edTitle, edDescription, edPrice;
    private ArrayList<Brand> brands;
    private ArrayAdapter<String> brandsAdapter;
    public AddEditProduct() {
    }
    public static AddEditProduct create(ProductAction action, @Nullable String productId) {
        AddEditProduct fragment = new AddEditProduct();
        int act;
        switch (action) {
            case ADD:
                act = 1;
                break;
            case EDIT:
                act = 2;
                break;
            default:
                act = 0;
        }
        Bundle args = new Bundle();
        args.putInt(ACTION, act);
        args.putString(ID, productId);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        activity = (AppCompatActivity) context;
        super.onAttach(context);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            switch (getArguments().getInt(ACTION)) {
                case 1:
                    action = ProductAction.ADD;
                    textTitle = String.format(activity.getString(R.string.product_action_text), activity.getString(R.string.product_btn_add_text));
                    textAddUpdate = activity.getString(R.string.product_btn_add_text);
                    break;
                case 2:
                    action = ProductAction.EDIT;
                    textTitle = String.format(activity.getString(R.string.product_action_text), activity.getString(R.string.product_btn_update_text));
                    textAddUpdate = activity.getString(R.string.product_btn_update_text);
                    idProduct = getArguments().getString(ID);
                    product = ProductManager.getById(activity, idProduct);
                    break;
            }
            idProduct = getArguments().getString(ID);
        }
        textCancel = activity.getString(R.string.product_btn_cancel_text);
        brands = BrandManager.getAll(getContext());
        ArrayList<String> brandsTitle = BrandManager.getAllTitle(getContext());
        brandsAdapter = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, brandsTitle);
        brandsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_or_edit_product, container, false);
        spCategory = view.findViewById(R.id.spinner_category);
        spCategory.setAdapter(brandsAdapter);
        spCategory.setOnItemSelectedListener(this);
        tvError = view.findViewById(R.id.tv_add_edit_error);
        tvTitle = view.findViewById(R.id.tv_product_action_title);
        edTitle = view.findViewById(R.id.ed_product_title);
        edDescription = view.findViewById(R.id.ed_product_description);
        edPrice = view.findViewById(R.id.ed_product_price);
        btnSaveEdit = view.findViewById(R.id.btn_save_edit_product);
        btnCancel = view.findViewById(R.id.btn_cancel_product);
        btnSelectImg = view.findViewById(R.id.btn_product_get_image);
        selectedImage = view.findViewById(R.id.img_product_action);
        if (idProduct == null) selectedImage.setVisibility(View.GONE);
        tvTitle.setText(textTitle);
        btnSaveEdit.setText(textAddUpdate);
        btnSaveEdit.setOnClickListener(this);
        btnCancel.setText(textCancel);
        btnCancel.setOnClickListener(this);
        btnSelectImg.setOnClickListener(this);
        if (product != null) {
            edTitle.setText(product.getTitle());
            edDescription.setText(product.getDescription());
            edPrice.setText(String.valueOf(product.getPrice()));
            btnSelectImg.setText(product.getImgUrl());
            selectedImage.setImageDrawable(product.getImg());
            int spinnerPosition = brandsAdapter.getPosition(product.getBrandTitle());
            spCategory.setSelection(spinnerPosition);
        }
        tvError.setVisibility(View.GONE);
        return view;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel_product:
                Toast.makeText(activity, "Cancelling", Toast.LENGTH_SHORT).show();
                Switcher.show(activity, Switcher.CATALOGUE_TAG);
                break;
            case R.id.btn_product_get_image:
                Toast.makeText(activity, "Btn Select Image", Toast.LENGTH_SHORT).show();
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 5);
                break;
            case R.id.btn_save_edit_product:
                Toast.makeText(activity, "Saving", Toast.LENGTH_SHORT).show();
                validateFields();
                break;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 5)
            try {
                Uri uri = data.getData();
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                selectedImage.setVisibility(View.VISIBLE);
                selectedImage.setImageBitmap(bitmap);
                btnSelectImg.setText(getFileName(uri));
            } catch (IOException e) {
                Log.i("TAG", "Some exception " + e);
            }
    }
    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = activity.getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String idBrand = brands.get(position).getId();
        //Toast.makeText(activity, idBrand, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
    private int getIndex(Spinner spinner, String myString) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)) {
                return i;
            }
        }
        return 0;
    }
    public void validateFields() {
        String title = edTitle.getText().toString();
        String description = edDescription.getText().toString();
        String price = edPrice.getText().toString();
        String imgName = btnSelectImg.getText().toString();
        if (title.isEmpty() || description.isEmpty() || price.isEmpty() || imgName.isEmpty()) {
            tvError.setText("* All fields are required !");
            tvError.setVisibility(View.VISIBLE);
        } else {
            product.setTitle(title);
            product.setDescription(description);
            product.setPrice(Double.valueOf(price));
            product.setImg_url(imgName);
            int i = spCategory.getSelectedItemPosition();
            product.setIdBrand(brands.get(i).getId());
            switch (action) {
                case ADD:
                    product.setId(UUID.randomUUID().toString());
                    ProductManager.insert(activity, product);
                    Toast.makeText(activity, "Product added successfully", Toast.LENGTH_SHORT).show();
                    break;
                case EDIT:
                    ProductManager.update(activity, product);
                    Toast.makeText(activity, "Product updated successfully", Toast.LENGTH_SHORT).show();
                    break;
            }
            Switcher.show(activity, Switcher.CATALOGUE_TAG);
        }
    }
}
