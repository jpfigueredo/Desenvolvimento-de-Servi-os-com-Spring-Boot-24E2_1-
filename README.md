# Getting Started with Create Java Spring App

This project was created using [Spring Initializr](https://start.spring.io/).

## Available Scripts

In the project directory, you can run:

### ./mvnw spring-boot:run

Runs the app in the development mode.\
Open [http://localhost:8080](http://localhost:8080) to view it in your browser.

The page will reload when you make changes to the code.\
You may also see any errors in the console.

### ./mvnw test

Launches the test runner in the interactive watch mode.\
See the section about running tests for more information.

### ./mvnw package

Builds the app for production to the target directory.\
It correctly bundles Java code in production mode and optimizes the build for the best performance.

The build is optimized and the filenames include the version information.
Your app is ready to be deployed!

See the section about deployment for more information.

# Dependencies

## Lombok

This project demonstrates how to set up a Spring Boot application using Maven and integrate Lombok to reduce boilerplate code.

### Setting Up Lombok

Lombok is a Java library that helps to reduce boilerplate code by generating common methods like getters, setters, equals, hashCode, and toString at compile time.

### Step 1: Add Lombok Dependency

Add the following Lombok dependency to your `pom.xml` file:

```xml
<dependencies>
    <!-- Other dependencies -->

    <!-- Lombok dependency -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.20</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

### Step 2: Enable Annotation Processing in Your IDE

#### IntelliJ IDEA

1. Go to File > Settings > Build, Execution, Deployment > Compiler > Annotation Processors.
2. Check Enable annotation processing.

#### Eclipse

1. Go to Project > Properties > Java Compiler > Annotation Processing.
2. Check Enable annotation processing.

#### VS Code

Install the Lombok plugin from the VS Code marketplace. The plugin will automatically configure the annotation processing.

### Step 3: Install Lombok Plugin (Optional)

While this step is optional, it is highly recommended to improve the development experience in your IDE.

#### IntelliJ IDEA

1. Go to File > Settings > Plugins.
2. Search for Lombok and install it.
3. Restart IntelliJ IDEA.

#### Eclipse

1. Go to Help > Eclipse Marketplace.
2. Search for Lombok and install it.
3. Restart Eclipse.

## Learn More

You can learn more about Spring Boot in the Spring Boot documentation.

To learn Java Spring, check out the Spring Framework documentation.

### Database Setup

This section has moved here: [Boot Feature SQL](https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-sql)

### Deploying to Production

This section has moved here: [Deployment](https://docs.spring.io/spring-boot/docs/current/reference/html/deployment.html)

### Advanced Configuration

This section has moved here: [Configuration](https://docs.spring.io/spring-boot/docs/current/reference/html/configuration.html)

### Troubleshooting

This section has moved here: [Troubleshooting](https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-troubleshooting.html)

### Additional Tools

This section has moved here: [CLI](https://docs.spring.io/spring-boot/docs/current/reference/html/using-spring-boot.html#using-boot-cli)
