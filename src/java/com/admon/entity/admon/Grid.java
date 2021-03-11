package com.admon.entity.admon;

import java.util.List;

public class Grid {

    private List grid;
    private Integer paginas;
    private Integer total;

    public Grid() {
    }

    public List getGrid() {
        return grid;
    }

    public void setGrid(List grid) {
        this.grid = grid;
    }

    public Integer getPaginas() {
        return paginas;
    }

    public void setPaginas(Integer paginas) {
        this.paginas = paginas;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
