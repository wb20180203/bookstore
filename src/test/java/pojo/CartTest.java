package pojo;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @description:
 * @author:18312
 * @date:2020/11/17 17:17
 */
public class CartTest {
    private Cart cart=new Cart();
    @Test
    public void addBook() {
        cart.addBook(new CartItem(1,"hhhh",2,new BigDecimal(200)));
        cart.addBook(new CartItem(2,"hfgdg",2,new BigDecimal(400)));
        System.out.println(cart);
    }

    @Test
    public void delete() {
        cart.addBook(new CartItem(1,"hhhh",2,new BigDecimal(200)));
        cart.addBook(new CartItem(2,"hfgdg",2,new BigDecimal(400)));

        cart.delete(1);
        System.out.println(cart);
    }

    @Test
    public void clear() {
        cart.addBook(new CartItem(1,"hhhh",2,new BigDecimal(200)));
        cart.addBook(new CartItem(2,"hfgdg",2,new BigDecimal(400)));

        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void update() {
        cart.addBook(new CartItem(1,"hhhh",2,new BigDecimal(200)));

        cart.update(1,1);
        System.out.println(cart);
    }
}