package scripts

import org.gradle.tooling.GradleConnector

def engine = new GroovyScriptEngine("src/main/resources/configs")
def studentScript = engine.loadScriptByName("StudentConfig.groovy")
def taskScript = engine.loadScriptByName("TaskConfig.groovy")
def studentClass = studentScript.getDeclaredConstructor().newInstance()
def taskClass = taskScript.getDeclaredConstructor().newInstance()

def javaDocChecker(def students, def taskKey) {
    def result = new LinkedHashMap()
    for (i in students.keySet()) {
        def studentUserName = students.get(i).username
        result[studentUserName] = "-"
        def connector = GradleConnector.newConnector()
        def path = "repos/" + studentUserName + "/" + taskKey
        connector.forProjectDirectory(new File(path))
        def connection = connector.connect()
        def build = connection.newBuild()
        try {
            build.forTasks("javadoc").run()
            result[studentUserName] = "+"
        } catch (Exception e) {
            println "Some problems with javadoc: " + e.getMessage()
        }
    }
    return result
}

def result = new LinkedHashMap()
for (i in taskClass.tasks.keySet()) {
    result[i] = javaDocChecker(studentClass.students, i)
}

return result