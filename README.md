# Congresso em chamas
![Build - Maven](https://github.com/gpr-indevelopment/congresso_em_chamas/workflows/Java%20CI%20with%20Maven/badge.svg?event=push)

[Congresso em chamas](https://congresso-em-chamas.herokuapp.com/) é uma aplicação web, com código aberto, que entrega ferramentas para o acompanhamento do trabalho dos deputados federais atualmente em mandato. 

Foi concebido como um projeto pessoal, visando o aprimoramento dos meus conhecimentos práticos de frontend.

## Features principais

 1. Despesas: Acompanhe todas as despesas lançadas na cota parlamentar dos deputados federais.
 2. Notícias: Os últimos artigos e notícias dos deputados federais com a curadoria do Google News API.
 3. Proposições: Projetos de lei, resoluções, medidas provisórias e mais. Acompanhe a atividade dos deputados federais na câmara.

# Reflexão
## Introdução
Esse projeto foi concebido principalmente com o intuito de aprimorar os meus conhecimentos de frontend. Também se mostrou uma oportunidade de participar da construção de uma ferramenta que permita a população acompanhar o trabalho dos deputados federais em atividade.

Assim, construí uma aplicação web, nomeada "Congresso em chamas", com Spring Boot no backend e HTML, CSS e JS no frontend com componentes Bootstrap 4 na sua primeira versão. Hoje, ela conta com um frontend desenvolvido com ReactJS e um servidor na AWS. Todos os dados apresentados, e combinados, na aplicação são consultados em APIs públicas como:

 1. [Google News API](https://newsapi.org/s/google-news-br-api)
 2. [Camara API](https://dadosabertos.camara.leg.br/swagger/api.html)
 3. [Twitter API](https://developer.twitter.com/en/docs/api-reference-index)

## Requisitos

O requisito inicial da aplicação era apresentar, por parlamentar:

 1. Apresentação das despesas.
 2. Apresentação das propostas e proposições.
 3. Compilado das redes sociais.
 4. Painel das notícias mais relevantes.
 5. Dashboard com indicadores de desempenho.

Dentre os requisitos iniciais que foram implementados parcialmente foram:

 3. Apenas o timeline do twitter foi apresentado. Ainda, a aplicação determina o usuário dos deputados pelo primeiro resultado de uma busca simples na API do Twitter. Essa abordagem funciona para a maioria dos deputados, mas gera problemas para outros que não tem conta no Twitter. **Portanto, essa feature necessita da criação de uma base de dados de redes sociais dos deputados.** Por conta do esforço de mapeamento dos usuários de twitter por político, a feature da timeline do Twitter foi retirada.
 5. A feature do dashboard exige o acesso em uma base de dados com todas as informações dos deputados para que a aplicação consiga calcular os indicadores de desempenho, por exemplo. **Essa feature foi interrompida pelas limitações de uso das bases de dados relacionais disponíveis gratuitamente para serem acessadas na nuvem.** (Atualização 04/08/2020) A instância EC2 que hospeda o site conta agora com uma base de dados PostgreSQL que permite o desenvolvimento dessa feature. Atualmente é um WIP.

## Aprendizado

O objetivo principal de aprimoraramento de conhecimentos de frontend foi atingido. Sem dúvidas, esse projeto me possibilitou ganhar prática em diversas frentes do desenvolvimento web.

A escolha do Java com Spring Boot como tecnologia de backend foi simples. É onde eu tenho mais experiência, e o projeto pedia uma base sólida para que eu pudesse focar pesquisas e perguntas no frontend.

Me desafiei a desenvolver o frontend da aplicação usando HTML, CSS e JS puros, utilizando componentes Bootstrap para aliviar minhas limitações de design. Tomei essa decisão, mesmo sabendo das perdas de produtividade da não utilização de um framework como React, ou Angular, por exemplo. **No meu entendimento, essa seria um bom jeito de sentir as dores e limitações que levaram o ecossistema ao desenvolvimento de frameworks como esses.** Ao final, senti dores como:

 1. **Necessidade de componentização**: Por exemplo, precisei renderizar listas de tamanhos variáveis. Portanto, acabei criando funções JS para injetar tags HTML diretamente na DOM, utilizando template strings para mapear atributos dos objetos retornados pelo backend. Esse problema foi bem resolvido no React, por exemplo:
```javascript
newsListElement.parentElement.innerHTML =  `<div class="d-flex flex-column justify-content-center align-items-center">
												<img src="${"../assets/perfil-nobackground.png"}" height="200">
												<h2 class="text-center">Nenhuma notícia recente encontrada.</h2>
											</div>`
```
 2. **Escassez de componentes de terceiros**: Procurei por bibliotecas de componentes, e encontrei poucas alternativas aderentes ao JS, HTML e CSS puros. Algumas bibliotecas que encontrei (ex. Semantic UI) mexeram no css das tags padrões do HTML, e criaram um overhead para manter o layout pretendido da página. Optei então pelo Bootstrap 4, que tem componentes maduros e amplamente conhecidos no mercado. Ainda, as classes de CSS do Bootstrap se mostraram muito úteis para a construção de containers flexbox, principalmente.
 3. **Dificuldade em manter a consistência dos nomes das classes CSS**: Não encontrei maneiras elegantes de aplicar classes CSS com escopo restrito. Por vezes, tive que tomar cuidado ao nomear uma classe CSS pois ela poderia sobreescrever uma outra classe escrita em outro arquivo css. Esse problema foi bem resolvido por bibliotecas CSS-in-JS como a [styled components no React](https://styled-components.com/).
 4. **Falta de ferramentas para modularização do JS**: Nesse desenvolvimento eu não utilizei webpack, e me mantive importando o JS e CSS pelas tags "link" e "script", padrões do HTML. Tive que gerenciar essas tags nos arquivos, e quais variáveis globais cada arquivo JS possuía. Ainda, o rename de um arquivo JS, ou variável, envolve saber onde essa variável, ou função, foi utilizada para trocar o seu valor. 

Ainda, relacionado ao frontend, adquiri conhecimentos de design, paletas de cores, layouts e semântica com o projeto. Me limitei a 4 cores (branco, cinza, azul e laranja), mantendo-as consistentes em todas as páginas. Ainda, optei pelo layout de duas linhas e containers flexbox visando facilitar a **responsividade**.

Tambem aprendi muito sobre configuração e consumo de APIs públicas da web, principalmente quando essas não tem biblioteca client disponível em Java. 

Não passei o fonte do projeto por uma análise estática de código, nem escrevi testes automatizados em um primeiro momento, pois o principal objetivo aqui foi o estudo de frontend. As refatorações de code smells e o desenvolvimento de testes automatizados são necessidades que crescem juntas com o code base. Esse é um WIP.

# Migração para ReactJS (07/2020)

A code base com HTML, CSS e JS puros já estava difícil de ser mantida. Decidi migrar a UI para ReactJS para avançar meus conhecimentos em um framework moderno de frontend. Diante disso, consegui migrar tudo usando meu tempo livre em cerca de 2 semanas. Tive muita agilidade nessa migração já que o design já estava bem definido. **Isso comprova a diferença que um protótipo faz na assertividade e velocidade de um desenvolvimento de frontend.** Ainda, optei por utilizar componentes da biblioteca [Antd](https://ant.design/) por terem um design moderno e uma API robusta e flexível. Adaptei o tema de cores desses componentes para a paleta do site e a UI ganhou um aspecto mais moderno. Os usuários também relataram que o site parece mais rápido e responsivo.

# Trabalhos futuros

1. **Requisitos incompletos**: Como citado anteriormente, o dashboard de indicadores de desempenho e o compilado de redes sociais dos deputados não foram totalmente implementados.
2. **Testes automatizados e refatoração de code smells**: Como citado anteriormente, as refatorações de code smells e o desenvolvimento de testes automatizados serão necessárias uma vez que a aplicação tiver algum aumento no volume de acessos. (Atualização 04/08/2020) Os testes de backend foram concluídos com uma cobertura de 70% dos métodos. Os testes de frontend ainda são um WIP.

# Contribua
Pull requests são bem-vindos. Esse é um projeto pessoal para aprendizado de tecnologia, e serei grato por qualquer dica ou sugestão de escrita de código, arquitetura, padrões de projeto, etc.
