package ru.nsu.palkin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TreeIterator<Tree> implements Iterator<Tree> {
    private ArrayList<Tree> trees;
    private int currentIndex;

    public TreeIterator(ArrayList<Tree> trees) {
        this.trees = trees;
        this.currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return this.currentIndex < this.trees.size();
    }

    @Override
    public Tree next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Tree tree = this.trees.get(this.currentIndex);
        this.currentIndex++;
        return tree;
    }
}