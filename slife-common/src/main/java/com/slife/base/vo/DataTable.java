package com.slife.base.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**

 */
public class DataTable<T> implements Serializable {
    private static final long serialVersionUID = 2252240868205663450L;


    private int total;
    /**
     * 搜索条件
     */
    private Map<String, Object> searchParams;

    /**
     * 返回列表
     */
    private List<T> rows = new ArrayList<>();

    /**
     * 排序 条件
     */
    Map<String, String> sorts;

    /**
     * 当前页码
     */
    private int pageNumber;

    /**
     * 页码大小
     */
    private int pageSize;


    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Map<String, Object> getSearchParams() {
        return searchParams;
    }

    public void setSearchParams(Map<String, Object> searchParams) {
        this.searchParams = searchParams;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }


    public Map<String, String> getSorts() {
        return sorts;
    }

    public void setSorts(Map<String, String> sorts) {
        this.sorts = sorts;
    }
}
