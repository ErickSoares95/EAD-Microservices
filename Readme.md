PLATAFORMA EAD

Domain: 
-AuthUser Service: Podem ser Administradores, estudates, estrututores, etc. Poderam ser cadastrados, atualizados,. Teram que ser autenticados
-Course Service: Cursos seram cadastrados, teram modulos, nos modulos teram aulas, matricula dos estudantes,
-Notification: Service Usuário entra na plataforma recebe boas vindas, cursos cadastrados hávera notificações

Base de dados: base de dados por serviço.

Processo Sybchronous Communication via HTTP
-API RESTful
-API Composition Pattern

Asynchronous Communication via Messagign or Events
-Broker Pattern
-Mediator Pattern
-Event Notification Pattern
-Event Carried State Transfer Pattern
-Saga Pattern

Distributed Identifiers - UUIDs: único universal, trabalha de forma temporal, unico, pode ser geral em qualquer lugar,

Cross Cutting Concerns:
-API Gateway Pattern
-Service Registry Descovery Pattern
-Config Server Pattern
-Circuit Breaker Pattern
-Log Aggregation Pattern

Authentication and Authorization:
-Realizado no AuthUser Service.
-Access Token Pattern

Base de dados compartilhada:
-Prós: 
--forte consintência
--ACID, Atomicidade(Só ocorre transações completas), forte consistencia, Isolamento de transações e durabilidade.
-Contras:
-Forte acoplamento
-Baixa Perfomace
-Modelo de dados não isolados


Base de dados por serviços:
-Pros:
--Baixo acoplamento
--isolamento de modelo de dados
--flexibilidade na escolha do banco de dados por serviço
--Independencia entre times(Tecnologias)
-Contras:
--Sicronização dos dados
--Consistencia vs Disponibilidade(Teorema CAP)
--Replicação de dados

Pattern por causa dos bancos isolados
API Composition: Quando for realizar uma transação que depende de dados de varios services é utilizado esté padrão para reunir os dados.
Saga Pattern

o que é uma message: Pedido generico uma carga util.
exchange: protocolo
Routing Key endereço direcionado
redelivered: descobrir
Composta de header e body.
header: meta dados
body: Payload que é oq será enviado.






