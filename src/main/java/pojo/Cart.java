package pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description:
 * @author:18312
 * @date:2020/11/17 9:40
 */
public class Cart {
    private Map<Integer,CartItem> items=new LinkedHashMap<>();

    public Cart() {}

    public Cart(Map<Integer, CartItem> items) {
        this.items = items;
    }

    /**
     * 添加到购物车
     * @param c
     */
    public void addBook(CartItem c){
        CartItem item = items.get(c.getId());
        if(item==null){
            items.put(c.getId(),c);
        }else{
            item.setCount(item.getCount()+c.getCount());
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
            items.put(item.getId(),item);
        }
    }

    /**
     * 删除商品
     * @param id
     */
    public void delete(int id){
        items.remove(id);
    }

    /**
     * 清空购物车
     */
    public void clear(){
        items.clear();
    }

    /**
     * 修改购物车中商品
     * @param id
     */
    public void update(int id,int count){
        CartItem item = items.get(id);
        if(item!=null){
            item.setCount(count);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
            items.put(item.getId(),item);
        }
    }
    public Integer getTotalCount() {
        int totalCount=0;
        for(Map.Entry<Integer,CartItem> entry:items.entrySet()) {
            totalCount+=entry.getValue().getCount();
        }
        return totalCount;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice=new BigDecimal(0);
        for(Map.Entry<Integer,CartItem> entry:items.entrySet()){
            totalPrice=totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
