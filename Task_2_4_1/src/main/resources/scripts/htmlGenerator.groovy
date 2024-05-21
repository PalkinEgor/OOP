package scripts

import org.jsoup.Jsoup
import java.nio.file.Files
import java.nio.file.Paths

def engine = new GroovyScriptEngine("src/main/resources/configs")
def taskScript = engine.loadScriptByName("TaskConfig.groovy")
def taskClass = taskScript.getDeclaredConstructor().newInstance()
def studentScript = engine.loadScriptByName("StudentConfig.groovy")
def studentClass = studentScript.getDeclaredConstructor().newInstance()

GroovyShell shell = new GroovyShell()
def buildPath = "src/main/resources/scripts/buildTask.groovy"
def buildText = new String(Files.readAllBytes(Paths.get(buildPath)))
def buildResult = shell.evaluate(buildText)

def javaDocPath = "src/main/resources/scripts/javaDocChecker.groovy"
def javaDocText = new String(Files.readAllBytes(Paths.get(javaDocPath)))
def javaDocResult = shell.evaluate(javaDocText)

def testPath = "src/main/resources/scripts/testChecker.groovy"
def testText = new String(Files.readAllBytes(Paths.get(testPath)))
def testResult = shell.evaluate(testText)

def getHtmlTemplate(def taskKey) {
    def htmlHead = new File(this.class.getResource("/html/head.html").getFile())
    def htmlString = Jsoup.parse(htmlHead).toString()
    htmlString += """
    <body>
        <table>
            <tr>
                <td colspan="7">${taskKey}</td>
            </tr>
            <tr>
                <th>Student</th>
                <th>Build</th>
                <th>JavaDoc</th>
                <th>Tests</th>
            </tr>"""
    return htmlString
}

for (i in taskClass.tasks.keySet()) {
    htmlString = getHtmlTemplate(i)
    for (j in studentClass.students.keySet()) {
        def studentUserName = studentClass.students.get(j).username
        htmlString = htmlString + "<tr><td>" + studentUserName + "</td>"
        def buildMark = buildResult.get(i).get(studentUserName)
        htmlString = htmlString + "<td>" + buildMark + "</td>"
        def javaDocMark = javaDocResult.get(i).get(studentUserName)
        htmlString = htmlString + "<td>" + javaDocMark + "</td>"
        def testMark = testResult.get(i).get(studentUserName)
        htmlString = htmlString + "<td>" + testMark + "</td>"
        htmlString = htmlString + "</tr>"
    }
    htmlString = htmlString + "</table></body>"
    String htmlDocPath = "src/main/resources/html/${i}.html"
    FileWriter writer = new FileWriter(htmlDocPath)
    writer.write(htmlString)
    writer.close()
}