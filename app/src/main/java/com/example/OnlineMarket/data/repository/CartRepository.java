package com.example.OnlineMarket.data.repository;

import android.content.Context;
import android.util.Log;


import androidx.lifecycle.LiveData;


import com.example.OnlineMarket.data.model.order.Order;
import com.example.OnlineMarket.data.model.product.Product;
import com.example.OnlineMarket.data.remote.NetworkParams;
import com.example.OnlineMarket.data.remote.retrofit.RetrofitInstance;
import com.example.OnlineMarket.data.remote.retrofit.WooCommerceAPI;
import com.example.OnlineMarket.data.room.dao.CartDAO;
import com.example.OnlineMarket.data.room.entities.Cart;
import com.example.OnlineMarket.data.model.coupon.Coupon;
import com.example.OnlineMarket.data.room.RoomDataBase;


import java.util.List;

import retrofit2.Call;

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
