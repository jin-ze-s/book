package com.atguigu.book.test;

import com.atguigu.book.pojo.Cart;
import com.atguigu.book.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author Tom 2022/2/11 2:25
 * ���ﳵģ�����
 */
@SuppressWarnings("ALL")
public class CartTest {

    private Cart cart = new Cart();

    @Test
    public void addCartItem(){
        CartItem cartItem = new CartItem();
        cartItem.setId(1);
        cartItem.setName("�ҾͲ�������");
        cartItem.setPrice(new BigDecimal(100));
        cartItem.setCount(1);
        cartItem.setTotalPrice(new BigDecimal(300));
        cart.addItem(cartItem);
        cart.addItem(new CartItem(1, "�ҾͲ�����!", 1, new BigDecimal(100), new BigDecimal(100)));
        cart.addItem(new CartItem(2, "���ͣ�", 1, new BigDecimal(100), new BigDecimal(100)));

        System.out.println(cart);
    }

    @Test
    public void deleteCartItem() {
        CartItem cartItem = new CartItem();
        cartItem.setId(1);
        cartItem.setName("�ҾͲ�������");
        cartItem.setPrice(new BigDecimal(100));
        cartItem.setCount(1);
        cartItem.setTotalPrice(new BigDecimal(300));
        cart.addItem(cartItem);
        cart.addItem(new CartItem(1, "�ҾͲ�����!", 1, new BigDecimal(100), new BigDecimal(100)));
        cart.addItem(new CartItem(2, "���ͣ�", 1, new BigDecimal(100), new BigDecimal(100)));
        cart.deleteItem(2);
        System.out.println(cart);
    }

    @Test
    public void clear(){
        CartItem cartItem = new CartItem();
        cartItem.setId(1);
        cartItem.setName("�ҾͲ�������");
        cartItem.setPrice(new BigDecimal(100));
        cartItem.setCount(1);
        cartItem.setTotalPrice(new BigDecimal(300));
        cart.addItem(cartItem);
        cart.addItem(new CartItem(1, "�ҾͲ�����!", 1, new BigDecimal(100), new BigDecimal(100)));
        cart.addItem(new CartItem(2, "���ͣ�", 1, new BigDecimal(100), new BigDecimal(100)));
        cart.deleteItem(2);
        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void updateItems(){
        CartItem cartItem = new CartItem();
        cartItem.setId(1);
        cartItem.setName("�ҾͲ�������");
        cartItem.setPrice(new BigDecimal(100));
        cartItem.setCount(1);
        cartItem.setTotalPrice(new BigDecimal(300));
        cart.addItem(cartItem);
        cart.addItem(new CartItem(1, "�ҾͲ�����!", 1, new BigDecimal(100), new BigDecimal(100)));
        cart.addItem(new CartItem(2, "���ͣ�", 1, new BigDecimal(100), new BigDecimal(100)));
        cart.deleteItem(2);
        cart.updateItem(1, 5);
        System.out.println(cart);
    }

}
