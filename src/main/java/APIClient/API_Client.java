package APIClient;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class API_Client {
    private HttpClient httpClient;
    private String base_url;

    public API_Client(String url, String user, String password) {
        List<Header> headerList = new ArrayList<Header>();
        headerList.add(new BasicHeader("Content-Type", "application/json"));
        headerList.add(new BasicHeader("Authorization", "Basic " + getAuthorization(user, password)));

        httpClient = HttpClientBuilder.create().setDefaultHeaders(headerList).build();
        this.base_url = url + "/index.php?/api/v2/";
    }

    private String getAuthorization(String user, String password) {
        return new String(Base64.getEncoder().encode((user + ":" + password).getBytes()));
    }

    public String sendGet(String endpoint) throws IOException {
        HttpGet httpGet = new HttpGet(base_url + endpoint);
        return getResponse(httpClient.execute(httpGet));
    }

    public String sendPost(String endpoint, String data) throws IOException {
        HttpPost httpPost = new HttpPost(base_url + endpoint);
        return getResponse(httpClient.execute(httpPost));
    }

    public String getResponse (HttpResponse response) throws IOException{
        HttpEntity entity = response.getEntity();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        entity.writeTo(stream);
        String content = stream.toString();
        return content;
    }
}