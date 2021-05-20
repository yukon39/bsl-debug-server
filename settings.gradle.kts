rootProject.name = "bsl-debug-server"

include( ":debugBSL")

for (project in rootProject.children) {
    project.projectDir = file("subprojects/${project.name}")
}
