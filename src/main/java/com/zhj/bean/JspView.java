package com.zhj.bean;

import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by zhj on 2018/10/15.
 */

public class JspView implements View {

    private String jspTemplateFileLocation;

    @Override
    public String getContentType() {
        return "text/html;charset=UTF-8";
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType(getContentType());
        exposeModelToRequest(model,request);
        request.getRequestDispatcher(jspTemplateFileLocation).forward(request,response);
    }

    protected void exposeModelToRequest(Map model , HttpServletRequest request){
        if (!model.isEmpty()){
            Iterator<Map.Entry> iterator = model.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry entry = iterator.next();
                String attrName = (String) entry.getKey();
                Object attrValue = entry.getValue();
                request.setAttribute(attrName,attrValue);
            }
        }
    }

    public String getJspTemplateFileLocation() {
        return jspTemplateFileLocation;
    }

    public void setJspTemplateFileLocation(String jspTemplateFileLocation) {
        this.jspTemplateFileLocation = jspTemplateFileLocation;
    }
}