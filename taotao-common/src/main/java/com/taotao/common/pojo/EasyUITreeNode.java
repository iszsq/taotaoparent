package com.taotao.common.pojo;

import java.io.Serializable;

/**
 * @author zsq
 * @date 2018/12/15 - 21:21
 */
public class EasyUITreeNode implements Serializable {
    private Long id;
    private String text;
    private String state;

    public EasyUITreeNode() {
    }

    public EasyUITreeNode(Long id, String text, String state) {
        this.id = id;
        this.text = text;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "EasyUITreeNode{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
