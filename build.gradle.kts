plugins {
    id 'java'
    id 'org.springframework.boot' version '2.7.6'
    id 'io.spring.dependency-management' version '1.0.15.RELEASE'
}

group = 'ra'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '1.8'
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
    compileOnly {
        extendsFrom annotationProcessor
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-security'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    compileOnly 'org.projectlombok:lombok'
    developmentOnly 'org.springframework.boot:spring-boot-devtools'
    runtimeOnly 'com.mysql:mysql-connector-j'
    annotationProcessor 'org.projectlombok:lombok'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testImplementation 'org.springframework.security:spring-security-test'
    // https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt
    implementation group: 'io.jsonwebtoken', name: 'jjwt', version: '0.9.1'

    implementation 'org.springframework.boot:spring-boot-starter-oauth2-client'
    implementation 'org.springframework.boot:spring-boot-starter-oauth2-resource-server'

    // https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-mail
    implementation group: 'org.springframework.boot', name: 'spring-boot-starter-mail', version: '2.7.1'


}

tasks.named('test') {
    useJUnitPlatform()
}
