package org.geektimes.configuration.servlet;

import org.geektimes.configuration.microprofile.config.source.servlet.ServletRequestConfigSource;

public class ServletRequestConfigSourceTest {
    public static void main(String[] args) {
        ServletRequestConfigSource servletRequestConfigSource = new ServletRequestConfigSource();
        HttpUtil.createServer(8080).addAction("/json",new RequestAction(servletRequestConfigSource)).start();
    }

    class RequestAction implements Action {
        private ServletRequestConfigSource servletRequestConfigSource;
        public RequestAction(ServletRequestConfigSource servletRequestConfigSource){
            this.servletRequestConfigSource = servletRequestConfigSource;
        }

        @Override
        public void doAction(HttpServerRequest httpServerRequest, HttpServerResponse httpServerResponse) throws IOException {
            //把请求结果转成map
            ListValueMap<String, String> params = httpServerRequest.getParams();
            Map<String, String> map = new HashMap<>();
            for (Map.Entry<String, List<String>> param : params) {
                //value 数组
                List<String> value = param.getValue();
                //key
                System.out.println(param);
                map.put(param.getKey(),value.get(0));
            }


            System.out.println("当前的值"+servletRequestConfigSource.getProperties());
            servletRequestConfigSource.onUpdate(map);
            System.out.println("更新过后的值"+servletRequestConfigSource.getProperties());
            httpServerResponse.write("当前的值"+servletRequestConfigSource.getProperties() + "更新过后的值"+servletRequestConfigSource.getProperties());

            //httpServerResponse.write(httpServerRequest.getParams().toString(), ContentType.TEXT_PLAIN.toString());

        }
}
