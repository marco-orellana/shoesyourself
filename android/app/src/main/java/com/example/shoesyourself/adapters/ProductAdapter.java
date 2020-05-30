package com.example.shoesyourself.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shoesyourself.R;
import com.example.shoesyourself.entities.Product;
import com.example.shoesyourself.fragments.AddEditProduct;
import com.example.shoesyourself.managers.ProductManager;
import com.example.shoesyourself.settings.Preferences;
import com.example.shoesyourself.settings.Switcher;

import java.util.ArrayList;


public class ProductAdapter extends BaseAdapter {

    private final ArrayList<Product> products;
    private Button btnAddCart, btnSetting;
    private AppCompatActivity activity;
    public ProductAdapter(ArrayList<Product> products, AppCompatActivity activity) {
        this.products = products;
        this.activity = activity;
    }
    @Override
    public int getCount() {
        return products.size();
    }
    @Override
    public Product getItem(int position) {
        return products.get(position);
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Product product = products.get(position);
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(activity);
            convertView = inflater.inflate(R.layout.product_list_item_layout, null);
        }
        btnSetting = convertView.findViewById(R.id.btn_setting_product_item);
        btnAddCart = convertView.findViewById(R.id.btn_add_cart_product_item);
        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAddCartClick((Button) v, product.getId());
            }
        });
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSettingClick((Button) v, product.getId(), position);
            }
        });
        this.setAdminBtn();
        this.switchBtnText(btnAddCart, Preferences.existInCart(product.getId()));
        ImageView img = convertView.findViewById(R.id.img_product_item);
        TextView tvTitle = convertView.findViewById(R.id.tv_title_product_item);
        TextView tvPrice = convertView.findViewById(R.id.tv_price_product_item);
        img.setImageDrawable(product.getImg());
        tvPrice.setText(activity.getString(R.string.product_price_text, String.valueOf(product.getPrice())));
        tvTitle.setText(product.getTitle());
        return convertView;
    }
    private void setAdminBtn() {
        if (Preferences.getIsAdmin()) {
            btnAddCart.setVisibility(View.GONE);
            btnSetting.setVisibility(View.VISIBLE);
        } else {
            btnAddCart.setVisibility(View.VISIBLE);
            btnSetting.setVisibility(View.GONE);
        }
    }
    public void btnSettingClick(Button btn, final String id, final int position) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
        View dv = ((LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.admin_alert_product_setting, null);
        Button btnEdit = dv.findViewById(R.id.btn_edit_setting);
        Button btnDelete = dv.findViewById(R.id.btn_delete_setting);
        Button btnCloseAlert = dv.findViewById(R.id.btn_close_alert_dialog);
        dialogBuilder.setView(dv);
        final AlertDialog alertDialog = dialogBuilder.create();
        btnCloseAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddEditProduct addEditProduct = AddEditProduct.create(AddEditProduct.ProductAction.EDIT, id);
                Switcher.showWithParams(activity, addEditProduct, Switcher.ADDEDITPRODUCT_TAG);
                alertDialog.dismiss();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductManager.delete(activity, id);
                products.remove(position);
                notifyDataSetChanged();
                Toast.makeText(activity, "Product deleted successfully", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
    private void btnAddCartClick(Button btn, String id) {
        boolean idExist = Preferences.existInCart(id);
        if (idExist)
            Preferences.removeFromCart(id);
        else
            Preferences.addToCart(id);
        switchBtnText(btn, !idExist);
    }
    private void switchBtnText(Button btn, boolean exist) {
        Drawable img;
        if (exist) {
            btn.setText(activity.getString(R.string.remove_cart_text));
            img = activity.getResources().getDrawable(R.drawable.ic_remove_shopping_cart);
        } else {
            btn.setText(activity.getString(R.string.add_cart_text));
            img = activity.getResources().getDrawable(R.drawable.ic_add_shopping_cart);
        }
        img.setBounds(0, 0, 60, 60);
        btn.setCompoundDrawables(img, null, null, null);
    }
}