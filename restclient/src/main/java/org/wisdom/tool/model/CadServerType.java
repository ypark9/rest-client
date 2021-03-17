package org.wisdom.tool.model;

public enum CadServerType {
    WebCenter(0), Cloud(1);

    private int mid;

    private CadServerType(int mid)
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
