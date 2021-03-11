package org.wisdom.tool.model;

public enum CadTask {
    HELLO(0), GEN_SVG(1), GET_DESIGN_TYPE(2);

    private int mid;

    private CadTask(int mid)
    {
        this.mid = mid;
    }

    /**
     * @return mid
     */
    public int getMid()
    {
        return mid;
    }

    /**
     * @param mid the mid to set
     */
    public void setMid(int mid)
    {
        this.mid = mid;
    }

}
