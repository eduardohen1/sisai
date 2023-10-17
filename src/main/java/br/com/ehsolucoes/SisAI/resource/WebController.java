package br.com.ehsolucoes.SisAI.resource;

import br.com.ehsolucoes.SisAI.model.ResponseModel;
import br.com.ehsolucoes.SisAI.service.OpenAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WebController {

    private OpenAIService openAIService;

    @Autowired
    public WebController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @GetMapping("/")
    public String showForm(Model model){
        model.addAttribute("modelVersion", List.of("3.5", "4"));
        return "form";
    }

    @PostMapping("/")
    public String handleFormSubmission(@RequestParam String modelVersion,
                                       @RequestParam String prompt,
                                       Model model){

        StringBuilder sb = new StringBuilder();

        ResponseModel responseModel = openAIService.callGPT(prompt, modelVersion);
        if(responseModel != null){
            List<ResponseModel.Choice> choices = responseModel.getChoices();
            choices.stream().forEach(choice -> {
                sb.append(choice.getMessage().getContent());
            });
        }else{
            sb.append("Erro ao chamar a API do OpenAI");
        }/*
        double i = 0.0;
        while(i < 1000000000)
            i++;
        sb.append("Deu certo!!!!");*/

        //adicionar atributos ao modelo;
        model.addAttribute("modelVersion", List.of("3.5", "4"));
        model.addAttribute("response", convertNewlinesToHtml(sb.toString()));
        model.addAttribute("selectedModel", modelVersion);
        return "form";
    }

    public String convertNewlinesToHtml(String text) {
        // Substitui quebras de linha por <br> tags. Este método deve ser chamado antes de adicionar a resposta ao modelo.
        return text.replace("\n", "<br>");
    }


}
