FROM openjdk:17-jdk-slim

# Instala o Maven
RUN apt-get update && \
    apt-get install -y maven

# Define o diretório de trabalho
WORKDIR /app

# Copia o arquivo pom.xml para o diretório de trabalho
COPY pom.xml .

# Executa o comando para baixar todas as dependências do projeto
RUN mvn dependency:go-offline -B

# Copia todos os arquivos do diretório atual para o diretório de trabalho
COPY . .

# Define a porta que será usada pela aplicação
EXPOSE 8080

# Executa o comando para compilar o projeto
RUN mvn package -DskipTests

# Define as variáveis de ambiente
ENV SPRING_PROFILES_ACTIVE=prod
ENV SERVER_PORT=8080

# Define o comando para executar a aplicação
CMD ["java", "-jar", "target/product-0.0.1-SNAPSHOT.jar"]

