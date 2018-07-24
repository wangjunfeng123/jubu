package com.ninep.jubu.helper;

/**
 * 封装分页和排序参数及分页查询结果
 * 
 * @created 2017-06-01
 * @version 1.0
 */
public class Page implements Cloneable {

    public static final int DEFAULT_PAGE_SIZE = 20;

    public static final int DEFAULT_PAGE = 1;

    private int pageNo = DEFAULT_PAGE;

    private int pageSize = DEFAULT_PAGE_SIZE;

    private int totalCount = -1;

    private int totalPageCount = 1;

    public Page() {
    }

    public Page(Integer pageNo) {
        this.pageNo = pageNo;
        this.pageSize = DEFAULT_PAGE_SIZE;
    }

    public Page(Integer pageNo, Integer pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize == null ? DEFAULT_PAGE_SIZE : pageSize;
    }

    public Page(Integer pageNo, Integer pageSize, Integer totalCount) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
    }

    /**
     * 第一条记录在结果集中的位置,序号从0开始.
     *
     * @created 2017-06-01
     * @return
     */
    public int getStart() {
        if (pageNo < 0 || pageSize < 0) {
            return -1;
        } else {
            return ((pageNo - 1) * pageSize);
        }
    }

    /**
     *下一页的第一条数据
     * @return
     */
    public int getEnd() {
        return pageNo * pageSize;
    }

    /**
     * 总页数.
     *
     * @created 2017-06-01
     * @return
     */
    public int getTotalPageCount() {
        calculateTotalPageCount();
        return totalPageCount;
    }

    public void calculateTotalPageCount() {
        totalPageCount = totalCount / pageSize;
        if (totalCount % pageSize > 0) {
            totalPageCount++;
        }

        // 校正页码
        if (pageNo > totalPageCount) {
            pageNo = totalPageCount;
        }
        if (pageNo < 1) {
            pageNo = 1;
        }
    }

    /**
     * 是否还有下一页.
     *
     * @created 2017-06-01
     * @return
     */
    public boolean isHasNextPage() {
        return (pageNo + 1 <= getTotalPageCount());
    }

    /**
     * 返回下页的页号,序号从1开始.
     *
     * @created 2017-06-01
     * @return
     */
    public int getNextPage() {
        if (isHasNextPage())
            return pageNo + 1;
        else
            return pageNo;
    }

    /**
     * 是否还有上一页.
     *
     * @created 2017-06-01
     * @return
     */
    public boolean isHasPrePage() {
        return (pageNo - 1 >= 1);
    }

    /**
     * 返回上页的页号,序号从1开始.
     *
     * @created 2017-06-01
     * @return
     */
    public int getPrePage() {
        if (isHasPrePage())
            return pageNo - 1;
        else
            return pageNo;
    }

    /**
     * 每页的记录数量.
     *
     * @created 2017-06-01
     * @return
     */
    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 当前页的页号,序号从1开始.
     *
     * @created 2017-06-01
     * @return
     */
    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int page) {
        this.pageNo = page;
    }

    /**
     * 总记录数量.
     *
     * @created 2017-06-01
     * @return
     */
    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        // 计算总页数
        calculateTotalPageCount();
    }

    @Override
    public Object clone() {
        Object page = null;
        try {
            page = super.clone();
        } catch (CloneNotSupportedException e) {
        }
        return page;
    }
}
