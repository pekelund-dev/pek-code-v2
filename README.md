# pek-code-v2

Coding Agent CLI built with Spring Boot, Spring AI (Google GenAI), and Spring Shell.

## Prerequisites

- Java 25
- Maven 3.9+
- Google GenAI API key

## Configuration

Set your API key before running:

```bash
export GOOGLE_GENAI_API_KEY="your_api_key_here"
```

The app reads this via:

```properties
spring.ai.google.genai.api-key=${GOOGLE_GENAI_API_KEY:YOUR_API_KEY_HERE}
```

## Run the app

Start with Spring Boot plugin:

```bash
mvn spring-boot:run
```

Or run with the exec plugin:

```bash
mvn compile exec:java
```

## Use the CLI

When the shell starts, run:

```text
help
```

You can use either command alias:

```text
agent "<your prompt>"
```

or

```text
code "<your prompt>"
```

Example:

```text
agent "List files in the current directory and summarize what this project is."
```
