package br.com.ehsolucoes.SisAI.service;

import br.com.ehsolucoes.SisAI.config.OpenAIProperties;
import br.com.ehsolucoes.SisAI.model.RequestModel;
import br.com.ehsolucoes.SisAI.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class OpenAIService {

    private final RestTemplate restTemplate;
    private final OpenAIProperties openAIProperties;

    // Injeção de dependências via construtor
    @Autowired
    public OpenAIService(RestTemplate restTemplate, OpenAIProperties openAIProperties) {
        this.restTemplate = restTemplate;
        this.openAIProperties = openAIProperties;
    }

    public ResponseModel callGPT(String prompt, String engineVersion) {
        //montar o cabeçalho da requisição
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(openAIProperties.getKey());

        String engine = "davinci"; // Este é um exemplo, pode variar conforme a versão desejada.
        if ("3.5".equals(engineVersion)) {
            engine = "gpt-3.5-turbo"; // ou o código específico para GPT-3.5
        } else if ("4".equals(engineVersion)) {
            engine = "gpt-4"; // ou o código específico para GPT-4
        }

        RequestModel requestModel = new RequestModel();
        requestModel.setModel(engine);

        List<RequestModel.Message> messages = new ArrayList<>();
        messages.add(new RequestModel.Message("assistant", prompt));
        requestModel.setMessages(messages);

        //Criar entidade da requisição incluindo o corpo e o cabeçalho
        HttpEntity<RequestModel> requestEntity = new HttpEntity<>(requestModel, headers);

        //Fazer a chamada para a API e receber a resposta
        ResponseEntity<ResponseModel> responseEntity = restTemplate.postForEntity(openAIProperties.getUrl(), requestEntity, ResponseModel.class);

        // Se a requisição for bem-sucedida, retornar o corpo da resposta
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        } else {
            // Se houver erro, você pode querer lançar uma exceção ou tomar outra ação adequada
            // Por exemplo, retornar nulo ou lançar uma exceção personalizada
            return null; // ou lançar nova RuntimeException("Erro ao chamar a API do OpenAI");
        }

    }

}
