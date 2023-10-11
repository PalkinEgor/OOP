package ru.nsu.palkin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    public void equalsTrueTest() {
        Tree<String> tree = new Tree<>("R1");
        var a = tree.addChild("A");
        var b = a.addChild("B");
        Tree<String> subtree = new Tree<>("R2");
        subtree.addChild("C");
        subtree.addChild("D");
        tree.addChild(subtree);
        b.remove();

        Tree<String> tree1 = new Tree<>("R1");
        tree1.addChild("A");
        var r2 = tree1.addChild("R2");
        r2.addChild("C");
        r2.addChild("D");

        assertEquals(tree, tree1);
    }

    @Test
    public void equalsFalseValueTest() {
        Tree<String> tree = new Tree<>("R1");
        var a = tree.addChild("A");
        var b = a.addChild("B");
        Tree<String> subtree = new Tree<>("R2");
        subtree.addChild("C");
        subtree.addChild("D");
        tree.addChild(subtree);
        b.remove();

        Tree<String> tree1 = new Tree<>("R1");
        tree1.addChild("A");
        var r2 = tree1.addChild("R2");
        r2.addChild("C");
        r2.addChild("F");

        assertNotEquals(tree, tree1);
    }

    @Test
    public void equalsFalseStructureTest() {
        Tree<String> tree = new Tree<>("R1");
        var a = tree.addChild("A");
        var b = a.addChild("B");
        Tree<String> subtree = new Tree<>("R2");
        subtree.addChild("C");
        subtree.addChild("D");
        tree.addChild(subtree);
        b.remove();

        Tree<String> tree1 = new Tree<>("R1");
        var a1 = tree1.addChild("A");
        var r2 = tree1.addChild("R2");
        a1.addChild("C");
        r2.addChild("D");

        assertNotEquals(tree, tree1);
    }

    @Test
    public void bfsTest() {
        Tree<String> tree = new Tree<>("R1");
        var a = tree.addChild("A");
        var b = a.addChild("B");
        Tree<String> subtree = new Tree<>("R2");
        subtree.addChild("C");
        subtree.addChild("D");
        tree.addChild(subtree);
        b.remove();

        String answer = "R1AR2CD";
        String result = "";
        for (Tree<String> x : tree) {
            result = result + x.getRoot();
        }

        assertEquals(answer, result);
    }

    @Test
    public void dfsTest() {
        Tree<String> tree = new Tree<>("R1");
        var a = tree.addChild("A");
        var b = a.addChild("B");
        Tree<String> subtree = new Tree<>("R2");
        subtree.addChild("C");
        subtree.addChild("D");
        tree.addChild(subtree);
        b.remove();
        tree.iterator = 1;

        String answer = "R1R2DCA";
        String result = "";
        for (Tree<String> x : tree) {
            result = result + x.getRoot();
        }

        assertEquals(answer, result);
    }
}