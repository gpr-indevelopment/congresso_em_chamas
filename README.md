# Congresso em chamas

Congresso em Chamas é uma plataforma para acompanhamento dos principais indicadores dos deputados federais atualmente com mandato. 

## Features principais

 1. Despesas: Acompanhe todas as despesas lançadas na cota parlamentar dos deputados federais.
 2. Notícias: Os últimos artigos e notícias dos deputados federais com a curadoria do Google News API.
 3. Proposições: Projetos de lei, resoluções, medidas provisórias e mais. Acompanhe a atividade dos deputados federais na câmara.

# Reflexão
## Introdução
Esse projeto foi concebido principalmente com o intuito de aprimorar os meus conhecimentos de frontend. Também se mostrou uma oportunidade de participar da construção de uma ferramenta que permita a população acompanhar o trabalho dos deputados federais em atividade.

Assim, construí uma aplicação web, nomeada "Congresso em chamas", com Spring Boot no backend e HTML, CSS e JS no frontend com componentes Bootstrap 4. Hoje, a aplicação é servida no Heroku, que é um PaaS com tier gratuito. Todos os dados apresentados, e combinados, na aplicação são consultados em APIs públicas como:

 1. Google News API: https://newsapi.org/s/google-news-br-api
 2. Camara API: https://dadosabertos.camara.leg.br/swagger/api.html
 3. Twitter API: https://developer.twitter.com/en/docs/api-reference-index

## Requisitos

O requisito inicial da aplicação era apresentar, por parlamentar:

 1. Apresentação das despesas.
 2. Apresentação das propostas e proposições.
 3. Compilado das redes sociais.
 4. Painel das notícias mais relevantes.
 5. Dashboard com indicadores de desempenho.

Dentre os requisitos iniciais que foram implementados parcialmente foram:

 3. Apenas o timeline do twitter foi apresentado. Ainda, a aplicação determina o usuário dos deputados pelo primeiro resultado de uma busca simples na API do Twitter. Essa abordagem funciona para a maioria dos deputados, mas gera problemas para outros que não tem conta no Twitter. **Portanto, essa feature necessita da criação de uma base de dados de redes sociais dos deputados.**
 5. A feature do dashboard exige o acesso em uma base de dados com todas as informações dos deputados para que a aplicação consiga calcular os indicadores de desempenho, por exemplo. **Essa feature foi interrompida pelas limitações de uso das bases de dados relacionais disponíveis gratuitamente para serem acessadas na nuvem.**

## Conclusões
