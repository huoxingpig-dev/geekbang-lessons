package org.wandering.learning.myservlet.tag;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

/**
 *  自定义标签属性,动态获取标签属性，并进行打印
 */
public class AttributeTag extends SimpleTagSupport {
    private String message;
    private String cacheControl;
    private String pragma;
    private Long expires;

    public void setMessage(String msg) {
        this.message = msg;
    }

    public void setCacheControl(String cacheControl) {
        this.cacheControl = cacheControl;
    }

    public void setPragma(String pragma) {
        this.pragma = pragma;
    }

    public void setExpires(Long expires) {
        this.expires = expires;
    }

    private StringWriter sw = new StringWriter();

    @Override
    public void doTag() throws JspException, IOException {

        if (message != null) {
            // 从属性中使用消息
            JspWriter out = getJspContext().getOut();
            out.println(message);

            if (cacheControl != null)
                out.print(cacheControl);
            if (pragma != null)
                out.print(pragma);
            if (expires !=null)
                out.print(expires);

        } else {
            // 从内容体中使用消息
            getJspBody().invoke(sw);
            getJspContext().getOut().print(sw.toString());
        }
    }
}
