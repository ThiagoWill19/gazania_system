# Gazânia System
### Gerenciador de nogócios para o ramo de jardinagem limpeza de piscinas

### Descrição do Projeto:
O Gazânia System é um programa de gerenciamento de serviços de jardinagem e piscina voltado para pequenos e médios empreendedores que desejam simplificar a gestão do seu negócio.

> *Com este sistema, é possível gerenciar clientes, serviços, um controle de estoque básico e despesas, de forma fácil e intuitiva.*

Na **tela inicial**, o sistema apresenta um dashboard com informações importantes, como gastos, receita atual, quantidade de clientes e serviços em aberto, permitindo que o empreendedor acompanhe o andamento do seu negócio.

O Gazânia System também oferece uma página de **Demonstração do Resultado do Exercício (DRE)**, uma ferramenta indispensável para análise dos resultados mensais.

Na seção de **serviços**, é possível gerenciar visitas de jardinagem e piscina, controlar vendas de produtos utilizados na execução dos serviços, como por exemplo, cloro e adubo, além de cadastrar serviços adicionais que podem ser prestados fora do escopo do serviço contratado, como por exemplo, uma limpeza de calhas.

O sistema também conta com um **controle de estoque** e um **controle de gastos simplificado** e intuitivo.

>*O Gazânia System permite ainda fazer o fechamento do serviço e enviar os dados e valores dos serviços prestados diretamente para o e-mail do cliente, ou gerar um PDF e salvar no computador.*

**Com o Gazânia System, os pequenos e médios empreendedores do ramo de jardinagem e piscina têm acesso a uma ferramenta completa para simplificar a gestão de seus negócios.**

### Tecnologias

**Backend**
- Java
- Spring Boot
- Spring web
- Spring JPA
- MySQL

**Frontend:**
- HTML
- CSS
- JavaScript
- Bootstrap

**Arquitetura** - *MVC*

### Configuração do arquivo *application.properties*

- Crie e configure o arquivo **application.properties** na pasta resources:

    > **Em** *username*, *password*, *smtphost*, *email* **e** *senhaEmail* **substitua por seus dados de acesso ao banco e seus dados do provedor de email.**
    
    ``` 
    spring.datasource.url=jdbc:mysql://localhost:3306/gazania_system
    spring.datasource.username=username
    spring.datasource.password=password
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql= true

    #Hibernate Properties
    
    spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5InnoDBDialect

    #spring-boot-starter-mail properties
    spring.mail.host=smtphost
    spring.mail.port=587
    spring.mail.username=email
    spring.mail.password=senhaEmail
    spring.mail.properties.mail.smtp.auth=true
    spring.mail.properties.mail.smtp.starttls.enable=true
    spring.mail.properties.mail.smtp.starttls.required=true
    spring.mail.properties.mail.smtp.ssl.enable=false
    spring.mail.test-connection=true 
    ```
