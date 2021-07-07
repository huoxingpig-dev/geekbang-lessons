package org.wandering.learning.myservlet.tag;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 *  自定义空标签页,示例
 */
public class EmptyTag extends SimpleTagSupport {
    private String cacheControl;
    private String pragma;
    private Long expires;

    public void setCacheControl(String cacheControl) {
        this.cacheControl = cacheControl;
    }

    public void setPragma(String pragma) {
        this.pragma = pragma;
    }

    public void setExpires(Long expires) {
        this.expires = expires;
    }

    @Override
    public void doTag() throws JspException, IOException {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = attributes.getResponse();
        response.setHeader("Cache-Control", cacheControl);
        response.setHeader("Pragma", pragma);
        response.setDateHeader("Expires", expires);

        JspWriter out = getJspContext().getOut();
        out.print(cacheControl);
        out.print("Hello Empty Tag");
    }
}
