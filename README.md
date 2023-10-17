# Projeto SisAI - Chat com OpenAI

Este projeto é uma aplicação web interativa que permite aos usuários conversar com modelos de linguagem avançados da OpenAI, especificamente as versões 3.5 e 4 do GPT (Generative Pre-trained Transformer). Através de uma interface amigável, os usuários podem enviar prompts de texto para o modelo de IA e receber respostas coerentes e informativas baseadas nos dados que o modelo foi treinado.

## Contexto

O objetivo desta aplicação é proporcionar uma plataforma onde pesquisadores, entusiastas de IA ou curiosos possam interagir diretamente com modelos de linguagem GPT sem a necessidade de entender os detalhes técnicos ou configurar APIs por conta própria. Isso não só torna a tecnologia de IA mais acessível, como também permite uma exploração rápida e fácil das capacidades e limitações dos modelos de linguagem da OpenAI.

## Como funciona

A aplicação é dividida em duas partes principais:

1. **Frontend**: Uma página web onde os usuários podem escolher a versão do modelo GPT que desejam interagir, inserir seus prompts de texto e visualizar as respostas. O frontend também suporta a alteração de temas (claro e escuro) para uma melhor experiência do usuário.

2. **Backend**: Um servidor que hospeda a API Rest utilizada para processar as solicitações do usuário, interagir com a API da OpenAI e retornar as respostas do modelo GPT para o frontend.

## Endpoint da API

### POST `http://localhost:8080/api/gpt/generate`

Este endpoint é responsável por processar as solicitações do chat enviadas pelos usuários e comunicar-se com a API da OpenAI para obter respostas dos modelos GPT.

#### Parâmetros

- `@RequestBody String prompt`: O texto que o usuário deseja enviar para o modelo GPT. Este prompt serve como um gatilho para a "conversa" e deve ser uma string de texto claro.

- `@RequestParam String version`: A versão específica do modelo GPT que o usuário deseja consultar. As opções suportadas são "3.5" e "4", representando diferentes versões e capacidades do modelo.

#### Resposta

Se a solicitação for bem-sucedida, o endpoint retornará um objeto JSON contendo a resposta gerada pelo modelo GPT. Em caso de erro, uma mensagem apropriada de erro será retornada.

#### Exemplo de Uso

```bash
curl -X POST "http://localhost:8080/api/gpt/generate?version=4" \
     -H "Content-Type: application/json" \
     -d '{"prompt":"Olá, como você está?"}' 
```

## Executando a Aplicação

Para iniciar a aplicação, você precisa seguir os seguintes passos (assumindo que você já possui um ambiente de desenvolvimento configurado):

1. **Clone o repositório do projeto.**
2. **Navegue até o diretório do projeto e instale as dependências necessárias.**
3. **Inicie o servidor backend.**
4. **Abra seu navegador e acesse o frontend através do URL fornecido (geralmente `http://localhost:8080`).**
5. **Agora você deve estar pronto para interagir com os modelos GPT através de uma interface de chat simples, mas poderosa.**

## Contribuições

Contribuições são bem-vindas! Se você encontrou um bug ou tem uma sugestão de recurso, sinta-se à vontade para abrir uma issue. Se você deseja contribuir com o código, por favor, faça um fork do repositório e submeta um pull request.


