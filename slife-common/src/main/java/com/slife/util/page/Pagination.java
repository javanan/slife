package com.slife.util.page;

import java.util.List;

public class Pagination extends SimplePage implements java.io.Serializable {

    private static final long serialVersionUID = -1585539529849523194L;

    public Pagination() {
    }

    /**
     * 构造器
     *
     * @param pageNo     页码
     * @param pageSize   每页几条数据
     * @param totalCount 总共几条数据
     */
    public Pagination(int pageNo, int pageSize, int totalCount) {
        super(pageNo, pageSize, totalCount);
    }

    /**
     * 构造器
     *
     * @param pageNo     页码
     * @param pageSize   每页几条数据
     * @param totalCount 总共几条数据
     * @param list       分页内容
     */
    public Pagination(int pageNo, int pageSize, int totalCount, List<?> list) {
        super(pageNo, pageSize, totalCount);
        this.list = list;
    }

    /**
     * 第一条数据位置
     *
     * @return
     */
    public int getFirstResult() {
        return (pageNo - 1) * pageSize;
    }

    /**
     * 当前页的数据
     */
    private List<?> list;

    /**
     * 获得分页内容
     *
     * @return
     */
    public List<?> getList() {
        return list;
    }

    /**
     * 设置分页内容
     *
     * @param list
     */
    public void setList(List<?> list) {
        this.list = list;
    }


    public int getStart() {
        int start = getTotalCount() - getPageNo() * getPageSize();
        if (start <= 0) {
            return 1;
        }
        return start + 1;

    }

    public int getEnd() {
        int end = 1;
        if (isLastPage() && !isFirstPage()) {//如果是最后一页
            end = getTotalCount() - getPrePage() * pageSize + 1;
        } else {
            end = getStart() + getPageSize();
        }

        if (end > getTotalCount()) {
            return getTotalCount();
        }
        if (end <= 0) {
            end = 1;
        }
        return end - 1;
    }


}
