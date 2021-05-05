package pojo;

import java.util.List;

/**
 * @description:
 * @author:18312
 * @date:2020/11/14 22:26
 */
public class Page<T> {
    public static final int PAGE_SIZE =4;
    //当前页码
    private int pageNo;
    //总页码
    private int pageTotal;
    //每页个数
    private int pageSize=PAGE_SIZE;
    //总个数
    private int pageTotalCount;
    //当前页数据
    private List<T> items;

    private String url;

    public Page() {}

    public Page(int pageNo, int pageTotal, int pageSize, int pageTotalCount, List<T> items) {
        this.pageNo = pageNo;
        this.pageTotal = pageTotal;
        this.pageSize = pageSize;
        this.pageTotalCount = pageTotalCount;
        this.items = items;
    }

    public String getUrl() {return url;}

    public void setUrl(String url) {this.url = url;}

    public void setPageSize(int pageSize) {this.pageSize = pageSize;}

    public int getPageTotalCount() {return pageTotalCount;}

    public void setPageTotalCount(int pageTotalCount) {this.pageTotalCount = pageTotalCount;}

    public List<T> getItems() {return items;}

    public void setItems(List<T> items) {this.items = items;}

    public int getPageNo() {return pageNo;}

    public void setPageNo(int pageNo) {this.pageNo = pageNo;}

    public int getPageTotal() {return pageTotal;}

    public void setPageTotal(int pageTotal) {this.pageTotal = pageTotal;}

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageSize=" + pageSize +
                ", pageTotalCount=" + pageTotalCount +
                ", items=" + items +
                '}';
    }
}
