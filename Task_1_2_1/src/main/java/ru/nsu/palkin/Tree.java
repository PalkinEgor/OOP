package ru.nsu.palkin;

import java.util.*;

public class Tree<T> implements Iterable<Tree<T>> {
    private T root;
    private Tree<T> parent;
    private ArrayList<Tree<T>> childrens;
    public int iterator;

    Tree(T value) {
        this.root = value;
        this.childrens = new ArrayList<>();
        this.parent = null;
        this.iterator = 0;
    }

    Tree<T> addChild(Tree<T> tree) {
        tree.parent = this;
        this.childrens.add(tree);
        return tree;
    }

    Tree<T> addChild(T value) {
        Tree<T> currentTree = new Tree<>(value);
        currentTree.parent = this;
        this.childrens.add(currentTree);
        return currentTree;
    }

    void remove() {
        if (this.parent != null) {
            this.parent.childrens.remove(this);
        } else {
            this.root = null;
            this.childrens = null;
        }
    }

    T getRoot() {
        return this.root;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Tree<T> t = (Tree<T>) obj;
        Queue<Tree<T>> q1 = new LinkedList<>();
        Queue<Tree<T>> q2 = new LinkedList<>();
        q1.add(this);
        q2.add(t);
        while (!q1.isEmpty()) {
            Tree<T> tmp1 = q1.element();
            Tree<T> tmp2 = q2.element();
            q1.remove();
            q2.remove();
            if ((tmp1.root != tmp2.root) || (tmp1.childrens.size() != tmp2.childrens.size())) {
                return false;
            }
            int len = tmp1.childrens.size();
            for (int i = 0; i < len; i++) {
                if (tmp1.childrens.get(i).root != tmp2.childrens.get(i).root) {
                    return false;
                }
                q1.add(tmp1.childrens.get(i));
                q2.add(tmp2.childrens.get(i));
            }
        }
        return true;
    }

    @Override
    public Iterator<Tree<T>> iterator() {
        if (iterator == 0) {
            return new bfsTreeIterator(this);
        }
        return new dfsTreeIterator(this);
    }

    private class bfsTreeIterator implements Iterator<Tree<T>> {
        private Deque<Tree<T>> d;

        bfsTreeIterator(Tree<T> tree) {
            this.d = new LinkedList<>();
            this.d.addLast(tree);
        }

        @Override
        public boolean hasNext() {
            return !this.d.isEmpty();
        }

        @Override
        public Tree<T> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Tree<T> tree = d.pollFirst();
            int len = tree.childrens.size();
            for (int i = 0; i < len; i++) {
                d.addLast(tree.childrens.get(i));
            }
            return tree;
        }
    }

    private class dfsTreeIterator implements Iterator<Tree<T>> {
        private Deque<Tree<T>> d;

        dfsTreeIterator(Tree<T> tree) {
            this.d = new LinkedList<>();
            this.d.addLast(tree);
        }

        @Override
        public boolean hasNext() {
            return !this.d.isEmpty();
        }

        @Override
        public Tree<T> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Tree<T> tree = d.pollFirst();
            int len = tree.childrens.size();
            for (int i = 0; i < len; i++) {
                d.addFirst(tree.childrens.get(i));
            }
            return tree;
        }
    }
}