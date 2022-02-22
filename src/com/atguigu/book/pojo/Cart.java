package com.atguigu.book.pojo;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author Tom 2022/2/11 1:01
 * ���ﳵ����
 */
public class Cart {


    private Map<Integer,CartItem> cartItems = new LinkedHashMap<>();

    /**
     * ���ﳵ�������Ʒ
     * @param cartItem ��Ʒ
     */
    public void addItem(CartItem cartItem){
        //�ж���û�и���Ʒ�����������Ʒ������1��û�������map����
        CartItem cartItem1 = cartItems.get(cartItem.getId());
        //����ǿգ���ǰ���ﳵ�����ڴ���Ʒ
        if(cartItem1 == null){
            cartItems.put(cartItem.getId(), cartItem);
        }else {
            cartItem1.setCount(cartItem1.getCount()+1);
            cartItem1.setTotalPrice(cartItem1.getPrice().multiply(new BigDecimal(cartItem1.getCount())));
        }
    }

    /**
     * ɾ��ָ����Ʒ
     * @param id ��Ʒid
     */
    public void deleteItem(int id){
        cartItems.remove(id);
    }


    /**
     * ��չ��ﳵ
     */
    public void clear(){
        cartItems.clear();
    }

    /**
     * �޸�ָ����Ʒ����
     * @param id ��Ʒid
     * @param count �޸�����
     */
    public void updateItem(int id,int count){
        CartItem cartItem = cartItems.get(id);
        if (cartItem != null){
            cartItem.setCount(count);
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }else {
            System.out.println("��ǰ���ﳵ��û�и���Ʒ");
        }
    }


    public Cart() {
    }





    /**
     * ���ع��ﳵ��Ʒ������
     * @return ������
     */
    public int getTotalCount(){
        int totalCount = 0;
        for (Map.Entry<Integer,CartItem> entry : cartItems.entrySet()){
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }

    /**
     * ���ع��ﳵ��Ʒ�ܽ��
     * @return �ܽ��
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
