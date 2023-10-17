package br.com.ehsolucoes.SisAI.resource;

import br.com.ehsolucoes.SisAI.config.OpenAIProperties;
import br.com.ehsolucoes.SisAI.model.ResponseModel;
import br.com.ehsolucoes.SisAI.service.OpenAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gpt")
public class GPTController {

    private OpenAIService openAIService;

    @Autowired
    public GPTController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @PostMapping("/generate")
    public ResponseEntity<?> generateText(@RequestBody String prompt, @RequestParam String version) {
        try{
            ResponseModel responseModel = openAIService.callGPT(prompt, version);
            if(responseModel != null){
                return ResponseEntity.ok(responseModel);
            }else{
                return ResponseEntity.badRequest().body("Erro ao chamar a API do OpenAI");
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Erro ao chamar a API do OpenAI");
        }
    }

}
