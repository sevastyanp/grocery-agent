plugins {
    id("com.nahob.sevastyan.grocery.assistant.kotlin.jvm.application")
}

application {
    mainClass = "com.nahob.sevastyan.grocery.assistant.server.app.MainKt"
    applicationName = "ozon"
}

dependencies {
    implementation(projects.core)
    implementation(projects.server.mcp)
}
