package ru.nsu.palkin;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Tree<T> {
    private int colored;
    private T root;
    private Tree<T> parent;
    private ArrayList<Tree<T>> childrens;
    private ArrayList<Tree<T>> bfsList;
    private ArrayList<Tree<T>> dfsList;

    Tree(T value) {
        this.root = value;
        this.childrens = new ArrayList<>();
        this.parent = null;
        this.bfsList = new ArrayList<>();
        this.dfsList = new ArrayList<>();
        this.colored = 0;
        BFS(this);
        DFS(this);
    }

    Tree<T> addChild(Tree<T> tree) {
        tree.parent = this;
        this.childrens.add(tree);
        BFS(this);
        DFS(this);
        return tree;
    }

    Tree<T> addChild(T value) {
        Tree<T> currentTree = new Tree<>(value);
        currentTree.parent = this;
        this.childrens.add(currentTree);
        BFS(this);
        DFS(this);
        return currentTree;
    }

    void remove() {
        if (this.parent != null) {
            this.parent.childrens.remove(this);
            BFS(this.parent);
            DFS(this.parent);
        } else {
            this.root = null;
            this.childrens = null;
            this.dfsList = null;
            this.bfsList = null;
        }
    }

    void DFS(Tree<T> t) {
        this.dfsList.clear();
        resetColored(t);
        Stack<Tree<T>> s = new Stack<>();
        s.push(t);
        t.colored = 1;
        while (!s.isEmpty()) {
            Tree<T> p = s.peek();
            int len = p.childrens.size();
            int flag = 0;
            for (int i = 0; i < len; i++) {
                if (p.childrens.get(i).colored == 0) {
                    p.childrens.get(i).colored = 1;
                    s.push(p.childrens.get(i));
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                System.out.println("!");
                this.dfsList.add(s.peek());
                s.pop();
            }
        }
    }

    void BFS(Tree<T> t) {
        this.bfsList.clear();
        Queue<Tree<T>> q = new LinkedList<>();
        q.add(t);
        while (!q.isEmpty()) {
            Tree<T> tmp = q.element();
            q.remove();
            this.bfsList.add(tmp);
            int len = tmp.childrens.size();
            for (int i = 0; i < len; i++) {
                q.add(tmp.childrens.get(i));
            }
        }
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

    private void resetColored(Tree<T> tree) {
        int len = tree.childrens.size();
        tree.colored = 0;
        for (int i = 0; i < len; i++) {
            resetColored(tree.childrens.get(i));
        }
    }

    public ArrayList<Tree<T>> getBfsList() {
        return this.bfsList;
    }

    public ArrayList<Tree<T>> getDfsList() {
        return this.dfsList;
    }

    public T getRoot() {
        return this.root;
    }
}