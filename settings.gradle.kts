rootProject.name = "bsl-debug-server"

include( ":debugBSL")
include( ":debugClientBSL")

for (project in rootProject.children) {
    project.projectDir = file("subprojects/${project.name}")
}
