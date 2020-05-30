package com.example.shoesyourself.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoesyourself.LoginActivity;
import com.example.shoesyourself.R;
import com.example.shoesyourself.adapters.CartAdapter;
import com.example.shoesyourself.entities.CartList;
import com.example.shoesyourself.managers.ProductManager;
import com.example.shoesyourself.settings.Preferences;
import com.example.shoesyourself.settings.Switcher;

import java.util.ArrayList;


public class Cart extends Fragment {

    private ArrayList<CartList> carts;
    private ArrayList<String> cartIds;
    private CartAdapter cartAdapter;
    private ListView lvCart;
    private double totalPriceQuantity;
    private String deleteConfirmation;
    AppCompatActivity activity;
    @Override
    public void onAttach(@NonNull Context context) {
        activity = (AppCompatActivity) context;
        super.onAttach(context);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cartIds = Preferences.getCartList();
        carts = new ArrayList<>();
        for (String idProduct : cartIds) {
            CartList list = new CartList(1, ProductManager.getByIdForShowCartItem(getContext(), idProduct));
            carts.add(list);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        final TextView tvTotal = view.findViewById(R.id.tv_total_cart);
        lvCart = view.findViewById(R.id.lv_cart);
        Button btnSaveCart = view.findViewById(R.id.btn_save_cart);
        cartAdapter = new CartAdapter(carts, lvCart, tvTotal, activity);
        lvCart.setAdapter(cartAdapter);
        btnSaveCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Preferences.getIsConnected()) {
                    Preferences.removeAllCartItem();
                    carts.clear();
                    cartAdapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Your shopping cart has been saved", Toast.LENGTH_SHORT).show();
                    Switcher.show(activity, Switcher.CATALOGUE_TAG);
                } else {
                    Toast.makeText(getContext(), "Please login to save your shopping cart", Toast.LENGTH_SHORT).show();
                    Intent signin = new Intent(activity, LoginActivity.class);
                    startActivity(signin);
                }
            }
        });
        return view;
    }
}
