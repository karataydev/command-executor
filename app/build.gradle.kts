plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
  	id("org.graalvm.buildtools.native") version "0.10.3"

}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Use JUnit Jupiter for testing.
    testImplementation(libs.junit.jupiter)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // This dependency is used by the application.
    implementation(libs.guava)

	// lombok annotation
	compileOnly(libs.lombok)
	annotationProcessor(libs.lombok)
	
	implementation(libs.jackson)
}

version = "0.0.1"

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    // Define the main class for the application.
    mainClass = "dev.karatay.commandexecutor.CommandExecutorApp"
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}

tasks {
    val fatJar = task("fatJar", type = Jar::class) {
        archiveClassifier.set("all")
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        manifest {
            attributes["Main-Class"] = application.mainClass.get()
        }
        from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
        with(jar.get() as CopySpec)
    }

    build {
        dependsOn(fatJar)
    }
}


graalvmNative {
    binaries {
        named("main") {
			useFatJar.set(true)

            imageName.set("comExecutor")
            mainClass.set("dev.karatay.commandexecutor.CommandExecutorApp")
            buildArgs.add("-O4")
            buildArgs.add("-H:ReflectionConfigurationFiles=${rootProject.projectDir}/app/src/main/resources/reflection-config.json")
        }
        named("test") {
            buildArgs.add("-O0")
        }
    }
    binaries.all {
		resources.autodetect()
        buildArgs.add("--verbose")
    }
}
