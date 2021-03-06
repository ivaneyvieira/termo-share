import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val karibudslVersion = "1.1.1"
val vaadinVersion = "22.0.2"

plugins {
  kotlin("jvm") version "1.6.10"
  id("org.gretty") version "3.0.6"
  war
  id("com.vaadin") version "22.0.2"
}

defaultTasks("clean", "build")

repositories {
  mavenLocal()
  mavenCentral()
  maven { setUrl("https://maven.vaadin.com/vaadin-addons") }
  maven { setUrl("https://maven.vaadin.com/vaadin-prereleases") }
}

gretty {
  contextPath = "/"
  servletContainer = "jetty9.4"
}

val staging: Configuration by configurations.creating

tasks.withType<KotlinCompile> {
  kotlinOptions.jvmTarget = "1.8"
}

group = "cliente"
version = "1.0"

java {
  sourceCompatibility = JavaVersion.VERSION_1_8
  targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
  // Karibu-DSL dependency
  implementation("com.github.mvysny.karibudsl:karibu-dsl:$karibudslVersion")
  implementation("com.github.mvysny.karibu-tools:karibu-tools:0.7")

  // Vaadin
  implementation("com.vaadin:vaadin-core:${vaadinVersion}")
  providedCompile("javax.servlet:javax.servlet-api:3.1.0")

  // logging
  implementation("org.slf4j:slf4j-simple:1.7.32")

  implementation(kotlin("stdlib-jdk8"))

  // logging
  implementation("ch.qos.logback:logback-classic:1.2.3")
  implementation("org.slf4j:slf4j-api:1.7.30")
  implementation("org.sql2o:sql2o:1.6.0")
  implementation("org.simpleflatmapper:sfm-sql2o:8.2.3")
  implementation("mysql:mysql-connector-java:5.1.48")
  implementation("net.sourceforge.jtds:jtds:1.3.1")
  implementation("org.imgscalr:imgscalr-lib:4.2")
  implementation("com.jcraft:jsch:0.1.55")
  implementation("org.cups4j:cups4j:0.7.8")
  // https://mvnrepository.com/artifact/org.jsoup/jsoup
  implementation("org.jsoup:jsoup:1.13.1")

  implementation("org.vaadin.tatu:twincolselect:1.2.0")
  implementation("org.vaadin.gatanaso:multiselect-combo-box-flow:1.1.0")
  implementation("org.vaadin.tabs:paged-tabs:2.0.1")
  implementation("org.claspina:confirm-dialog:2.0.0")

  implementation("org.vaadin.crudui:crudui:4.1.0")
  implementation("org.vaadin.stefan:lazy-download-button:1.0.0")
  //implementation("com.github.nwillc:poink:0.4.6")
  implementation("com.flowingcode.addons:font-awesome-iron-iconset:2.1.2")
  implementation("org.vaadin.haijian:exporter:3.0.1")
  implementation("com.github.doyaaaaaken:kotlin-csv-jvm:0.15.2")
  implementation("com.beust:klaxon:5.5")
  implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.12.3")
  implementation("com.github.wmixvideo:nfe:3.0.58")

  //implementation ("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.3")
  implementation("com.wontlost:zxing-vaadin:1.0.2-8")

  implementation(kotlin("reflect"))
  // https://mvnrepository.com/artifact/net.sourceforge.dynamicreports/dynamicreports-core
  implementation("net.sourceforge.dynamicreports:dynamicreports-core:6.12.1") {
    exclude(group = "com.lowagie", module = "itext")
  }
  // https://mvnrepository.com/artifact/net.sf.jasperreports/jasperreports-fonts
  implementation("net.sf.jasperreports:jasperreports:6.17.0")
  implementation("net.sf.jasperreports:jasperreports-fonts:6.17.0")
  implementation("de.f0rce.signaturepad:signature-widget:2.0.0")

  implementation("com.lowagie:itext:2.1.7")
  implementation("javax.xml.bind:jaxb-api:2.3.1")
  implementation("com.sun.mail:javax.mail:1.6.2")
  implementation("com.sun.mail:gimap:1.6.2")
}

vaadin {
  pnpmEnable = false
  productionMode = true
}

