package com.taotao.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * easyui表格控件返回数据格式
 * @author zsq
 * @date 2018/12/12 - 11:53
 */
public class EasyUIDataGridResult implements Serializable {

    private Integer total;
    private List rows;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
