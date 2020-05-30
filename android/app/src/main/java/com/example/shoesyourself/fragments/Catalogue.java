package com.example.shoesyourself.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.shoesyourself.R;
import com.example.shoesyourself.adapters.ProductAdapter;
import com.example.shoesyourself.entities.Brand;
import com.example.shoesyourself.entities.Product;
import com.example.shoesyourself.managers.BrandManager;
import com.example.shoesyourself.managers.ProductManager;
import com.example.shoesyourself.settings.Preferences;
import com.example.shoesyourself.settings.Switcher;

import java.util.ArrayList;


public class Catalogue extends Fragment {

    private ArrayList<Product> products;
    private ArrayList<Brand> brands;
    private ProductAdapter productAdapter;
    private GridView gvProduct;
    private ArrayAdapter<String> brandsAdapter;
    private AppCompatActivity activity;
    @Override
    public void onAttach(@NonNull Context context) {
        activity = (AppCompatActivity) context;
        super.onAttach(context);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        products = ProductManager.getAll(getContext());
        brands = BrandManager.getAll(getContext());
        ArrayList<String> brandsTitle = BrandManager.getAllTitle(getContext());
        brandsTitle.add(0, getString(R.string.brand_text));
        productAdapter = new ProductAdapter(products, activity);
        brandsAdapter = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, brandsTitle);
        brandsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catalogue, container, false);
        Spinner spinnerCategory = view.findViewById(R.id.spinner_category);
        Button btnAddProduct = view.findViewById(R.id.btn_add_product);
        Button btnSettingProduct = view.findViewById(R.id.btn_setting_product_item);
        gvProduct = view.findViewById(R.id.gv_list_product);
        // Spinner Setting
        spinnerCategory.setAdapter(brandsAdapter);
        spinnerCategory.setOnItemSelectedListener(brandClick);
        // BtnAddProduct Setting
        btnAddProduct.setOnClickListener(btnAddProductClick);
        if (Preferences.getIsAdmin()) btnAddProduct.setVisibility(View.VISIBLE);
        else btnAddProduct.setVisibility(View.GONE);
        // GridView Setting
        gvProduct.setAdapter(productAdapter);
        productAdapter.notifyDataSetChanged();
        gvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showAlertDetail(products.get(position));
            }
        });
        return view;
    }
    private View.OnClickListener btnAddProductClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getContext(), "BTN ADD PRODUCT", Toast.LENGTH_SHORT).show();
            AddEditProduct addEditProduct = AddEditProduct.create(AddEditProduct.ProductAction.ADD, null);
            Switcher.showWithParams(activity, addEditProduct, Switcher.ADDEDITPRODUCT_TAG);
        }
    };
    private void showAlertDetail(Product product) {
        String textPrice = getString(R.string.product_price_text, String.valueOf(product.getPrice()));
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        View dv = ((LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.activity_detail_product, null);
        ((ImageView) dv.findViewById(R.id.img_product_detail)).setImageDrawable(product.getImg());
        ((TextView) dv.findViewById(R.id.tv_title_product_detail)).setText(product.getTitle().toUpperCase());
        ((TextView) dv.findViewById(R.id.tv_price_product_detail)).setText(textPrice);
        ((TextView) dv.findViewById(R.id.tv_description_product_detail)).setText(product.getDescription());
        Button btnCloseAlert = dv.findViewById(R.id.btn_close_alert_dialog);
        dialogBuilder.setView(dv);
        final AlertDialog alertDialog = dialogBuilder.create();
        btnCloseAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
    private AdapterView.OnItemSelectedListener brandClick = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (position == 0)
                products = ProductManager.getAll(getContext());
            else
                products = ProductManager.getByBrand(getContext(), brands.get(position - 1).getId());
            productAdapter = new ProductAdapter(products, activity);
            gvProduct.setAdapter(productAdapter);
            productAdapter.notifyDataSetChanged();
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    };
}
