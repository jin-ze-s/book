package com.atguigu.book.pojo;


import java.util.List;

/**
 * @author Tom 2022/2/8 0:54
 * ��ҳģ�Ͷ���
 */

public class Page<T> {

    public static final int PAGE_SIZE = 4;
    /**
     *  ��ǰҳ��
     */
    private int pageNo;
    /**
     * ��ҳ��
     */
    private int pageTotal;
    /**
     * һ��ҳ�����������
     */
    private int pageSize = PAGE_SIZE;
    /**
     * ��ǰҳ����ʾ����
     */
    private List<T> items;
    /**
     * �ܼ�¼��
     */
    private int pageTotalCount;
    /**
     * ����ĵ�ַ
     */
    private String url;


    public Page(int pageNo, int pageTotal, int pageSize, List<T> items, int pageTotalCount) {
        this.pageNo = pageNo;
        this.pageTotal = pageTotal;
        this.pageSize = pageSize;
        this.items = items;
        this.pageTotalCount = pageTotalCount;
    }

    public Page() {
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo)
    {
        if(pageNo < 1){
            this.pageNo = 1;
            return;
        }
        if(pageNo > pageTotal){
            this.pageNo = pageTotal;
            return;
        }
        this.pageNo = pageNo;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public int getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(int pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageSize=" + pageSize +
                ", items=" + items +
                ", pageTotalCount=" + pageTotalCount +
                '}';
    }
}
