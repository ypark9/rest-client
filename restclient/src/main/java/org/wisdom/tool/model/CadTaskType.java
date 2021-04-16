package org.wisdom.tool.model;

public enum CadTaskType {
    HELLO(0), GEN_SVG(1), GET_DESIGN_TYPE(2);

    private int mid;

    private CadTaskType(int mid)
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

    /**
     * @return get the proper HTTP method
     */
    public String getHttpMethod(){
        String retVal = "POST";
        if(this.mid == 0)
            retVal = "GET";
        return retVal;
    }

    /**
     * @return get the proper HTTP method
     */
    public String getURLTaskName(){
        String retVal = "ECadTask";
        if(this.mid == 0)
            retVal = "Hello";
//        else if(this.mid == 3) {
//            retVal = "ECadDocTask";
//        }
        return retVal;
    }

}
