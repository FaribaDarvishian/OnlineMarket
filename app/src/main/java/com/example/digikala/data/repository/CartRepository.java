package com.example.digikala.data.repository;

import android.content.Context;
import android.util.Log;


import androidx.lifecycle.LiveData;


import com.example.digikala.data.model.coupon.Coupon;
import com.example.digikala.data.model.order.Order;
import com.example.digikala.data.model.product.Product;
import com.example.digikala.data.remote.NetworkParams;
import com.example.digikala.data.remote.retrofit.RetrofitInstance;
import com.example.digikala.data.remote.retrofit.WooCommerceAPI;
import com.example.digikala.data.room.RoomDataBase;
import com.example.digikala.data.room.dao.CartDAO;
import com.example.digikala.data.room.entities.Cart;



import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartRepository {
    public static final String TAG = "Cart Repository";
    private static CartRepository sInstance;
    private CartDAO mCartDAO;
    private WooCommerceAPI mWooCommerceAPI;

    public static synchronized CartRepository getInstance(Context context) {
        if (sInstance == null)
            sInstance = new CartRepository(context);
        return sInstance;
    }


    public CartRepository(Context context) {
        RoomDataBase roomDataBase = RoomDataBase.getDataBase(context);
        mCartDAO = roomDataBase.getCardDAO();
        mWooCommerceAPI = RetrofitInstance.getInstance().create(WooCommerceAPI.class);
    }

    public Call<Product> setCartProducts(Integer productId) {
        return mWooCommerceAPI.getProductById(productId);
    }


    public LiveData<List<Cart>> getCartLiveData() {
        return mCartDAO.getCarts();
    }

    public Cart getCart(int productId) {
        return mCartDAO.getCart(productId);
    }

    public void updateCart(Cart cart) {
        RoomDataBase.dataBaseWriteExecutor.execute(() -> mCartDAO.updateCarts(cart));    }

    public void deleteCart(Cart cart) {
        RoomDataBase.dataBaseWriteExecutor.execute(() -> mCartDAO.deleteCarts(cart));
    }

    public void insertCart(Cart cart) {
        RoomDataBase.dataBaseWriteExecutor.execute(() -> mCartDAO.insertCarts(cart));
    }

    public void deleteAllCarts() {
        RoomDataBase.dataBaseWriteExecutor.execute(() -> mCartDAO.deleteAllCarts());
    }
    public Call<Order> postOrder(Order order) {
        return mWooCommerceAPI.postOrder(order);
    }

    public Call<List<Coupon>> setCoupons(){
        Log.d(TAG, "setCoupons: ");
        return mWooCommerceAPI.getCoupons(NetworkParams.getCoupons(100));
    }
}
