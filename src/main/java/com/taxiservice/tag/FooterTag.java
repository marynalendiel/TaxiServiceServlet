package com.taxiservice.tag;

import org.apache.log4j.Logger;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class FooterTag extends TagSupport {
    private static final Logger LOGGER = Logger.getLogger(FooterTag.class);

    public int doStartTag() {
        JspWriter out = pageContext.getOut();
        try {
            out.println("© Taxi Service, 2021");
        }
        catch (Exception e) {
            LOGGER.error("Cannot start FooterTag " + e);
        }
        return SKIP_BODY;
    }

}
