package org.wisdom.tool.model;

public enum ConfigType {
    DEFAULT(0), INPUT(1), OUTPUT(2), SERVER(3);

    private int mid;

    private ConfigType(int mid)
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