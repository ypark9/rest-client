package org.wisdom.tool.model;

public enum CadInputType {
    WCDocument(0);

    private int mid;

    private CadInputType(int mid)
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

    public String GetName(int mid){
        String retString = "";
        switch (mid){
            case 0:
                return "org.wisdom.tool.model.CadJsonWrapper.WCDocument";
        }
        return retString;
    }

}