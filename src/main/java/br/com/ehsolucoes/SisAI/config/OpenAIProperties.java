package br.com.ehsolucoes.SisAI.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "openai.api")
public class OpenAIProperties {
    private String key;
    private String url;
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
