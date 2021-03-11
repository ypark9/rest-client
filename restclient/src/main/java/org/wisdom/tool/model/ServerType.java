package org.wisdom.tool.model;

public enum ServerType {
    WEB_CENTER(0);

    private int mid;

    private ServerType(int mid)
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
