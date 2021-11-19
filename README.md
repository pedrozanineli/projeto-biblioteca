<h1 align="center">Software de Agendamento de Salas</h1>

<p align="center">Desenvolvimento de uma aplicação de agendamento de salas como parte do Projeto de Integração de Engenharia do quarto semestre de Engenharia de Computação.</p>

<div align='center'>

<a href='https://github.com/pedrozanineli/projeto-biblioteca/releases'>

<img src='https://img.shields.io/github/v/release/pedrozanineli/projeto-biblioteca?color=blue&label=versao&style=for-the-badge'>

</a>

</div>

<details><summary><b>Problemática</b></summary>
  
  Na biblioteca da faculdade Facamp, existem algumas salas preparadas para que alunos possam estudar, abrigando entre 4 e 6 estudantes, além de poder prover uma lousa para anotações durante o estudo.
  
  É importante notar, entretanto, que, em muitos momentos (em especial durante o período de provas), o uso não é democrático, com alunos que utilizam a sala por muito tempo e a equipe da biblioteca não detém controle, criando um sério problema de organização.
  
  Justifica-se então o desenvolvimento de um software destinado para a reserva das salas, provendo maior controle por meio da divisão em uma tela destinada aos alunos e outra destinada aos funcionários, registrando em um banco de dados as informações dos alunos e suas respectivas reservas.
  
</details>

<details><summary><b>Consequências do projeto</b></summary>
  
  Além de ter como objetivo principal uma melhor organização do espaço, o software também será capaz de trazer benefícios em esferas econômicas, sociais e ambientais. Isso se dá pelo fato de que hoje, mesmo que não estejam sendo utilizadas, todas as salas permanecem com o ar condicionado e luz ligado, por cerca de 9 horas ao dia.
  
  Em estimativa feita pelo time do uso das salas - levando em consideração o maior fluxo em épocas de provas -, é possível fazer estimativas quanto à disparidade que é possível alcançar com o gasto apenas quando a sala estiver de fato sendo utilizada.
  
  Primeiramente, quanto a um impacto social, devemos levar em consideração que uma sala que não foi utilizada e está, portanto, limpa, não tem a necessidade do retrabalho de ser limpa novamente, o que permite com que a equipe da faxina possa realizar tudo com uma maior calma, diminuindo sua carga de tarefas, que não é nada pequena.
  
  Quanto aos efeitos ambientais, é possível pensar, com a estimativa proposta, em uma economia ao final de um ano de cerca de 13.305,6 kW, em torno dos 40%. Essa energia poderia ser então destinada para outros fins, como para o abastecimento de casas, e que seria suficiente para cerca de 68 casas anualmente.
  
  De forma similar, uma economia financeira também aconteceria por volta dos 40%, resultando em cerca de R$ 11.309,76 de economia anual. 
  
</details>

<details><summary><b>Funcionalidades e recursos</b></summary>
  
  O software foi desenvolvido com a proposta de trazer uma resolução à problemática apresentada de forma eficiente e agradável ao usuário, oferecendo diversos recursos que assistem e suavizam a utilização de suas funcionalidades. Desta forma, vários requisitos foram implementados para cumprir com a proposta e trazer usabilidade ao programa.
  
  Primeiramente, há uma validação de login que impede usuários comuns de acessarem recursos destinados ao caso de uso do administrador do sistema, e também funcionalidades para que as funções do software sejam executadas de forma ágil e com a minimização de erros, dentre elas:
  
  - Condições para que os campos aceitem informações corretas e reais (como o campo de RA só permite números no seu preenchimento);
  - Formatadores automáticos para moldes específicos de informações (p.e o campo de nome automaticamente coloca as primeiras letras dos nomes em maiúsculo);
  - Exceções para que reservas não sejam feitas com a lacuna ou ambiguidade de informações;
  - Busca no banco de dados para a verificação de horários e salas disponíveis, impossibilitando a reserva dupla nas mesmas condições;
  - Gatilhos para que os campos se preencham com base no RA do aluno cadastrado ao apertar a tecla Enter na caixa de texto;
  - Barreiras para o monopolio de reservas de um grupo de pessoas com a verificação do RA dos alunos e impossibilidade de reservar por um dia após ter usado esse recurso.
 
  Para o Administrador do sistema, várias das funcionalidades que preveem agilidade e minimização de erros também se aplicam. É possível buscar informações no banco de dados de acordo com uma informação específica escolhida (por nome, data, horário, etc) e também obter detalhes de todos os alunos que estão inclusos nas reservas. O perfil de Administrador é o único capaz de cancelar reservas previamente realizadas pelo software, essa função vem acompanhada de uma certificação de certeza ao tentar utilizá-la a fim de impedir usos acidentais e indevidos da função.
  
  
  
</details>

<details><summary><b>Bibliografia</b></summary>
  
  Dufrio Refrigeração. Quanto gasta um ar-condicionado de 9000 BTUS? Blog Dufrio. 15 de out. de 2020. Acesso em 22 de set. de 2021. Disponível em: <https://www.dufrio.com.br/blog/ar-condicionado/quanto-gasta-um-ar-condicionado-de-9000-btus/>.
  
  Preço da energia elétrica CPFL 2021. NG Solar. 11 de mai. de 2021. Acesso em 23 de set. de 2021. Disponível em: <https://www.ngsolar.com.br/amp/preco-kwh-cpfl>.

</details>
