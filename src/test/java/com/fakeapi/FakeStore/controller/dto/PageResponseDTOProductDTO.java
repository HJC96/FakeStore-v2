package com.fakeapi.FakeStore.controller.dto;

import java.util.List;

public class PageResponseDTOProductDTO {

    private Integer page;
    private Integer size;
    private Integer total;
    private Integer start;
    private Integer end;
    private Boolean prev;
    private Boolean next;
    private List<ProductDTO> dtoList;

    public PageResponseDTOProductDTO() {
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public Boolean getPrev() {
        return prev;
    }

    public void setPrev(Boolean prev) {
        this.prev = prev;
    }

    public Boolean getNext() {
        return next;
    }

    public void setNext(Boolean next) {
        this.next = next;
    }

    public List<ProductDTO> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<ProductDTO> dtoList) {
        this.dtoList = dtoList;
    }

}
