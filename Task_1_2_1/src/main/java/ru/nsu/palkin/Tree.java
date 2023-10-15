package ru.nsu.palkin;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Класс дерева.
 *
 * @param <T> - тип значений в узлах дерева
 */
public class Tree<T> implements Iterable<Tree<T>> {
    /**
     * Значение в корне дерева.
     */
    private T root;
    /**
     * Родитель узла.
     */
    private Tree<T> parent;
    /**
     * Список детей узла.
     */
    private ArrayList<Tree<T>> childrens;
    /**
     * Флаг для выбора итератора.
     */
    public boolean iterator;
    /**
     * Реальный счетчик изменений в дереве.
     */
    private int modificationCount;

    /**
     * Конструктор класса.
     *
     * @param value - значение в корне дерева
     */
    Tree(T value) {
        this.root = value;
        this.childrens = new ArrayList<>();
        this.parent = null;
        this.iterator = false;
        this.modificationCount = 0;
    }

    /**
     * Добавление ребенка.
     *
     * @param tree - значение добовляемого ребенка
     * @return возвращает поддерево где ребенок является корнем
     */
    Tree<T> addChild(Tree<T> tree) {
        tree.parent = this;
        this.childrens.add(tree);
        updateModificationCount(tree);
        return tree;
    }

    /**
     * Добавление ребенка.
     *
     * @param value - значение добовляемого ребенка
     * @return возвращает поддерево где ребенок является корнем
     */
    Tree<T> addChild(T value) {
        Tree<T> tree = new Tree<>(value);
        tree.parent = this;
        this.childrens.add(tree);
        updateModificationCount(tree);
        return tree;
    }

    /**
     * Удаление элемента или поддерева.
     */
    void remove() {
        if (this.parent != null) {
            this.parent.childrens.remove(this);
            updateModificationCount(this.parent);
        } else {
            this.root = null;
            this.childrens = null;
        }
    }

    /**
     * Обновление реального счетчика изменений в дереве.
     *
     * @param tree - дерево у которого необходимо счетчик обновить
     */
    void updateModificationCount(Tree<T> tree) {
        tree.modificationCount = tree.modificationCount + 1;
        if (tree.parent != null) {
            updateModificationCount(tree.parent);
        }
    }

    /**
     * Возвращает значение в корне дерева.
     *
     * @return возвращает значение в корне дерева
     */
    T getRoot() {
        return this.root;
    }

    /**
     * Переопределенный метод equals.
     *
     * @param obj - объект с которым сравниваем текущий
     * @return возвращает значение true или false
     */
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

    /**
     * Переопределенный метод iterator.
     *
     * @return возвращает объект итератора
     */
    @Override
    public Iterator<Tree<T>> iterator() {
        if (iterator) {
            return new DfsTreeIterator(this);
        }
        return new BfsTreeIterator(this);
    }

    /**
     * Класс BFS итератора.
     */
    private class BfsTreeIterator implements Iterator<Tree<T>> {
        /**
         * Очередь обхода дерева.
         */
        private Deque<Tree<T>> deque;
        /**
         * Счетчик ожидаемого количесвта изменений.
         */
        private int expectedModificationCount;
        /**
         * Корень откуда начинается обход.
         */
        private Tree<T> root;

        /**
         * Конструктор класса.
         *
         * @param tree - дерево с которого начинается обход
         */
        BfsTreeIterator(Tree<T> tree) {
            this.deque = new LinkedList<>();
            this.deque.addLast(tree);
            this.expectedModificationCount = tree.modificationCount;
            this.root = tree;
        }

        /**
         * Переопределенный метод hasNext.
         *
         * @return возвращает значение true или false
         */
        @Override
        public boolean hasNext() {
            if (this.root.modificationCount != expectedModificationCount) {
                throw new ConcurrentModificationException();
            }
            return !this.deque.isEmpty();
        }

        /**
         * Переопределенный метод next.
         *
         * @return возвращает следующий элемент дерева в порядке обхода
         */
        @Override
        public Tree<T> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Tree<T> tree = deque.pollFirst();
            int len = tree.childrens.size();
            for (int i = 0; i < len; i++) {
                deque.addLast(tree.childrens.get(i));
            }
            return tree;
        }
    }

    /**
     * Класс DFS итератора.
     */
    private class DfsTreeIterator implements Iterator<Tree<T>> {
        /**
         * Очередь обхода дерева.
         */
        private Deque<Tree<T>> deque;
        /**
         * Счетчик ожидаемого количесвта изменений.
         */
        private int expectedModificationCount;

        /**
         * Конструктор класса.
         *
         * @param tree - дерево с которого начинается обход
         */
        DfsTreeIterator(Tree<T> tree) {
            this.deque = new LinkedList<>();
            this.deque.addLast(tree);
            this.expectedModificationCount = tree.modificationCount;
        }

        /**
         * Переопределенный метод hasNext.
         *
         * @return - возвращает значение true или false
         */
        @Override
        public boolean hasNext() {
            return !this.deque.isEmpty();
        }

        /**
         * Переопределенный метод next.
         *
         * @return возвращает следующий элемент дерева в порядке обхода
         */
        @Override
        public Tree<T> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Tree<T> tree = deque.pollFirst();
            int len = tree.childrens.size();
            for (int i = 0; i < len; i++) {
                deque.addFirst(tree.childrens.get(i));
            }
            return tree;
        }
    }
}