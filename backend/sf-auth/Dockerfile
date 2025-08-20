# Use OpenJDK image
FROM eclipse-temurin:17-jdk-jammy

# Set working dir
WORKDIR /app

# Copy pom.xml and build (if using Maven Wrapper include ./mvnw)
COPY . .

# Build jar
RUN ./mvnw clean package -DskipTests

# Run jar (replace with actual jar name inside target/)
CMD ["java", "-jar", "target/sf-auth-0.0.1-SNAPSHOT.jar"]