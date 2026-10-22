# Atividade 3 — Desenvolvimento para Dispositivos Móveis I

Atividade 3 da disciplina **Desenvolvimento para Dispositivos Móveis I** do curso de Pós-Graduação em Desenvolvimento de Sistemas WEB e Aplicativos Móveis — IFSP Capivari.

## Enunciado

A atividade do módulo 03 consiste em realizar alguns ajustes no app desenvolvido e acrescentar outro serviço usando a API Invertexto. Para cumprir essa atividade sigam as instruções abaixo:

- Pesquisem alguma estratégia para melhorar a forma como o token está configurado no app.
- As chamadas para API podem demorar a acontecer, então coloquem algum "loading" para que tenha uma informação visual para o usuário.
- Escolha entre um dos serviços disponíveis na API Invertexto e criem os recursos necessários para usar e apresentar esse novo serviço no app.

## O que o app faz

Consulta **CEP** (logradouro) e **IP** pela [API Invertexto](https://api.invertexto.com/), usando Retrofit, com indicador de carregamento durante as chamadas.

## Stack

Android · Java · Retrofit 2 + Gson · minSdk 24

## Como executar

1. Crie um token na [Invertexto](https://invertexto.com/api).
2. Adicione o token ao arquivo `local.properties` na raiz do projeto (ele não é versionado):

   ```properties
   API_TOKEN=seu_token_aqui
   ```

3. Abra no Android Studio e execute em um emulador ou dispositivo.
