package com.example.shoesyourself.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shoesyourself.R;
import com.example.shoesyourself.entities.CartList;
import com.example.shoesyourself.settings.Preferences;
import com.example.shoesyourself.settings.Switcher;

import java.util.ArrayList;


public class CartAdapter extends ArrayAdapter<CartList> {

    private ArrayList<CartList> cartItems;
    private ListView cartList;
    private TextView tvTotal;
    private double total;
    AppCompatActivity activity;
    public CartAdapter(ArrayList<CartList> carts, ListView lvCartList, TextView tvTotal, AppCompatActivity activity) {
        super(activity, 0, carts);
        this.cartItems = carts;
        this.tvTotal = tvTotal;
        this.cartList = lvCartList;
        this.total = 0;
        this.activity = activity;
        getTotal();
    }
    @Nullable
    @Override
    public CartList getItem(int position) {
        return cartItems.get(position);
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final CartList cartItem = cartItems.get(position);
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.cart_list_item, null);
        }
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 250);
        convertView.setLayoutParams(lp);
        convertView.setTag(cartItem.getProduct_id());
        ((ImageView) convertView.findViewById(R.id.img_cart_item)).setImageDrawable(cartItem.getProductItem().getImg());
        ((TextView) convertView.findViewById(R.id.tv_cart_item_title)).setText(cartItem.getProductItem().getTitle());
        String priceQtyText = String.format(getContext().getString(R.string.cart_price_quantity_text), String.valueOf(cartItem.getProductItem().getPrice()), String.valueOf(cartItem.getQuantity()));
        final TextView tvPriceQuantity = convertView.findViewById(R.id.tv_cart_item_price_quantite);
        tvPriceQuantity.setText(priceQtyText);
        ((TextView) convertView.findViewById(R.id.tv_cart_item_price_quantite)).setText(priceQtyText);
        Button btnAddQuantity = convertView.findViewById(R.id.btn_add_cart_item_quantite);
        Button btnSubQuantity = convertView.findViewById(R.id.btn_sub_cart_item_quantite);
        final String price = String.valueOf(cartItem.getProductItem().getPrice());
        btnAddQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                String newPriceQtyText = String.format(getContext().getString(R.string.cart_price_quantity_text), price, String.valueOf(cartItem.getQuantity()));
                tvPriceQuantity.setText(newPriceQtyText);
                getTotal();
            }
        });
        btnSubQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cartItem.getQuantity() > 0) {
                    cartItem.setQuantity(cartItem.getQuantity() - 1);
                    String newPriceQtyText = String.format(getContext().getString(R.string.cart_price_quantity_text), price, String.valueOf(cartItem.getQuantity()));
                    tvPriceQuantity.setText(newPriceQtyText);
                    getTotal();
                }
                if (cartItem.getQuantity() == 0) {
                    Preferences.removeFromCart(cartItem.getProductItem().getId());
                    cartItems.remove(position);
                    notifyDataSetChanged();
                }
                if (Preferences.getCartListLength() == 0) {
                    Toast.makeText(getContext(), getContext().getString(R.string.cart_empty_text), Toast.LENGTH_SHORT).show();
                    Switcher.show(activity, Switcher.CATALOGUE_TAG);
                }
            }
        });
        return convertView;
    }
    public void getTotal() {
        double newTotal = 0;
        for (CartList list : cartItems) {
            newTotal += (list.getQuantity() * list.getProductItem().getPrice());
        }
        total = newTotal;
        tvTotal.setText(String.format(getContext().getString(R.string.cart_total_text), String.valueOf(newTotal)));
    }
    public double getTotalPrice() {
        return total;
    }
}
