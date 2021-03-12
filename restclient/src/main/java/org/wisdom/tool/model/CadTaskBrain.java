package org.wisdom.tool.model;

import com.google.gson.JsonObject;

public class CadTaskBrain {
    String url = "http://localhost:8080/ia-cad-service/rest/CadServer/";
    String method = "";
    String body = "";

    public CadTaskBrain(CadTask task) {
        this.method = task.getHttpMethod();
        System.out.print("CadTaskBrain : " + method + "\n");
        url += task.getEnd();
        System.out.print("CadTaskBrain : " + url + "\n");
    }

}
