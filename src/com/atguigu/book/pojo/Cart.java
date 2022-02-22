package com.atguigu.book.pojo;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author Tom 2022/2/11 1:01
 * 购物车对象
 */
public class Cart {


    private Map<Integer,CartItem> cartItems = new LinkedHashMap<>();

    /**
     * 向购物车中添加商品
     * @param cartItem 商品
     */
    public void addItem(CartItem cartItem){
        //判断有没有该商品，如果有则将商品数量加1，没有则加入map集合
        CartItem cartItem1 = cartItems.get(cartItem.getId());
        //如果是空，则当前购物车不存在此商品
        if(cartItem1 == null){
            cartItems.put(cartItem.getId(), cartItem);
        }else {
            cartItem1.setCount(cartItem1.getCount()+1);
            cartItem1.setTotalPrice(cartItem1.getPrice().multiply(new BigDecimal(cartItem1.getCount())));
        }
    }

    /**
     * 删除指定商品
     * @param id 商品id
     */
    public void deleteItem(int id){
        cartItems.remove(id);
    }


    /**
     * 清空购物车
     */
    public void clear(){
        cartItems.clear();
    }

    /**
     * 修改指定商品数量
     * @param id 商品id
     * @param count 修改数量
     */
    public void updateItem(int id,int count){
        CartItem cartItem = cartItems.get(id);
        if (cartItem != null){
            cartItem.setCount(count);
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }else {
            System.out.println("当前购物车中没有该商品");
        }
    }


    public Cart() {
    }





    /**
     * 返回购物车商品总数量
     * @return 总数量
     */
    public int getTotalCount(){
        int totalCount = 0;
        for (Map.Entry<Integer,CartItem> entry : cartItems.entrySet()){
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }

    /**
     * 返回购物车商品总金额
     * @return 总金额
     */
    public BigDecimal getTotalPrice(){
        BigDecimal totalPrice = new BigDecimal(0);
        for (Map.Entry<Integer,CartItem> entry : cartItems.entrySet()){
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }

    public Map<Integer,CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Map<Integer,CartItem> cartItems) {
        this.cartItems = cartItems;
    }


    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", cartItems=" + cartItems +
                '}';
    }
}
