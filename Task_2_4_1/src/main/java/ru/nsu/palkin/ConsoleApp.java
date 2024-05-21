package ru.nsu.palkin;

import groovy.lang.Binding;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleApp {
    public static void start() throws IOException, ScriptException, ResourceException {
        Scanner scanner = new Scanner(System.in);
        GroovyScriptEngine engine = new GroovyScriptEngine("src/main/resources/scripts");
        Binding binding = new Binding();
        String result = "";
        boolean cloneRepoStatus = false;
        while (true) {
            System.out.println("Enter the command: ");
            String inputCommand = scanner.nextLine();
            switch (inputCommand) {
                case "exit":
                    System.exit(0);
                    break;
                case "build":
                    if (cloneRepoStatus) {
                        result = engine.run("buildTask.groovy", binding).toString();
                        System.out.println(result);
                    } else {
                        System.out.println("Please clone repos");
                    }
                    break;
                case "javaDoc":
                    if (cloneRepoStatus) {
                        result = engine.run("javaDocChecker.groovy", binding).toString();
                        System.out.println(result);
                    } else {
                        System.out.println("Please clone repos");
                    }
                    break;
                case "test":
                    if (cloneRepoStatus) {
                        result = engine.run("testChecker.groovy", binding).toString();
                        System.out.println(result);
                    } else {
                        System.out.println("Please clone repos");
                    }
                    break;
                case "htmlReport":
                    if (cloneRepoStatus) {
                        engine.run("htmlGenerator.groovy", binding);
                    } else {
                        System.out.println("Please clone repos");
                    }
                    break;
                case "cloneRepo":
                    if (!cloneRepoStatus) {
                        engine.run("cloneRepo.groovy", binding);
                        cloneRepoStatus = true;
                    }
                    break;
                default:
                    System.out.println("I do not know this command");
                    break;
            }
        }
    }
}
