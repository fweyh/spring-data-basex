package com._4dconcept.springframework.data.basex.core.convert;

/**
 * A simple content holder used by BasexConverter
 * @author Fabien Weyh
 *
 * Modified from spring-data-marklogic
 * @author Stéphane Toussaint
 */
public class BasexContentHolder {

    private Object content;

    /**
     * @return the content
     */
    public Object getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(Object content) {
        this.content = content;
    }
}
