package com.yong.mark.vo;

/**
 * @acthor yong.a.liang
 * @date 2018/02/10
 */
public class PatronValue {

    private String patron;
    private long value;
    private String tableId;

    public PatronValue(){}
    public PatronValue(String patron, long value, String tableId) {
        this.patron = patron;
        this.value = value;
        this.tableId = tableId;
    }

    public String getPatron() {
        return patron;
    }

    public void setPatron(String patron) {
        this.patron = patron;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }
}
