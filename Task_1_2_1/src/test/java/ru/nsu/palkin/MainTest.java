package ru.nsu.palkin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ConcurrentModificationException;
import java.util.Objects;
import org.junit.jupiter.api.Test;

/**
 * Класс с тестами.
 */
public class MainTest {
    @Test
    public void equalsTrueTest() {
        Tree<String> tree = new Tree<>("R1");
        var a = tree.addChild("A");
        var b = a.addChild("B");
        Tree<String> subtree = new Tree<>("R2");
        b.remove();
        subtree.addChild("C");
        subtree.addChild("D");
        tree.addChild(subtree);

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
        b.remove();
        subtree.addChild("C");
        subtree.addChild("D");
        tree.addChild(subtree);

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
        b.remove();
        subtree.addChild("C");
        subtree.addChild("D");
        tree.addChild(subtree);

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
        b.remove();
        subtree.addChild("C");
        subtree.addChild("D");
        tree.addChild(subtree);

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
        b.remove();
        subtree.addChild("C");
        subtree.addChild("D");
        tree.addChild(subtree);
        tree.iterator = true;

        String answer = "R1R2DCA";
        String result = "";
        for (Tree<String> x : tree) {
            result = result + x.getRoot();
        }

        assertEquals(answer, result);
    }

    @Test
    public void concurrentModificationExceptionTest() {
        Tree<String> tree = new Tree<>("R1");
        var a = tree.addChild("A");
        var b = a.addChild("B");
        Tree<String> subtree = new Tree<>("R2");
        b.remove();
        subtree.addChild("C");
        subtree.addChild("D");
        tree.addChild(subtree);

        assertThrows(ConcurrentModificationException.class, () -> {
            for (Tree<String> x : tree) {
                if (Objects.equals(x.getRoot(), "R2")) {
                    x.remove();
                }
            }
        });
    }

    @Test
    public void removeTest() {
        Tree<String> tree1 = new Tree<>("A");
        tree1.addChild("B");
        var c = tree1.addChild("C");
        c.addChild("D");
        c.addChild("E");
        c.remove();

        Tree<String> tree2 = new Tree<>("A");
        tree2.addChild("B");

        assertEquals(tree1, tree2);
    }
}