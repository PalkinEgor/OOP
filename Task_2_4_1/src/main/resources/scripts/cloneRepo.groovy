package scripts

def engine = new GroovyScriptEngine("src/main/resources/configs")
def studentScript = engine.loadScriptByName("StudentConfig.groovy")
def studentClass = studentScript.getDeclaredConstructor().newInstance()

def cloneRepo(LinkedHashMap students) {
    for (i in students.keySet()) {
        def repoUrl = students.get(i).repository
        def username = students.get(i).username
        def cloneDir = "repos/" + username
        def command = ["git", "clone", repoUrl, cloneDir]
        def process = command.execute()
        def error = process.err.text
        def exitCode = process.waitFor()
        if (exitCode == 0) {
            println "Repository cloned successfully"
        } else {
            println "Repository cloned with some problems"
            println error
        }
    }
}

cloneRepo(studentClass.students)