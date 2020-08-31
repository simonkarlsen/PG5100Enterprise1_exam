// Altered code from course (lecturer) has been used in this file
// https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/spring/jsf/src/main/java/org/tsdes/intro/spring/jsf/ex03/CommentController.java

package com.example.exam.frontend.controller;


import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class FilterController implements Serializable {


    String selection;
    String query = "query";


    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }



    public String getLink() {
        return "moviedetails?filterBy=" + selection + "&query=" + query + "&faces-redirect=true";
    }


}
