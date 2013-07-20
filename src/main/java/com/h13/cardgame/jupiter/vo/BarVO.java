package com.h13.cardgame.jupiter.vo;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-7-16
 * Time: 下午4:14
 * To change this template use File | Settings | File Templates.
 */
public class BarVO {

    private List<CaptainCardVO> list = new LinkedList<CaptainCardVO>();

    @Override
    public String toString() {
        return "BarVO{" +
                "list=" + list +
                '}';
    }

    public List<CaptainCardVO> getList() {
        return list;
    }

    public void setList(List<CaptainCardVO> list) {
        this.list = list;
    }
}
