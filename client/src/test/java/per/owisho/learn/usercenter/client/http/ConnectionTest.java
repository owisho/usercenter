package per.owisho.learn.usercenter.client.http;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import sun.net.www.protocol.http.HttpURLConnection;

import java.io.IOException;
import java.net.Proxy;
import java.net.URL;

@SpringBootTest
public class ConnectionTest {

    @Autowired
    RestTemplate template;

    @Test
    public void test() throws IOException {
//        HttpURLConnection connection = new HttpURLConnection(new URL("https://per.owisho.learn/user"), Proxy.NO_PROXY);
//        connection.connect();

        ResponseEntity<Object> resp = template.exchange("https://per.owisho.learn/user", HttpMethod.GET,null,Object.class, (Object) null);
        System.out.println(resp.getStatusCode());
        System.out.println(resp.getBody());

    }

}
