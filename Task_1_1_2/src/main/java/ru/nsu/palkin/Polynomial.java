package ru.nsu.palkin;

import java.util.Arrays;

/**
 * Класс полинома.
 */
public class Polynomial {
    /**
     * Поле коэффициентов полинома.
     */
    private double[] body;
    /**
     * Поле значения старшей степени полинома.
     */
    private int length;

    /**
     * Конструктор класса.
     *
     * @param a - массив вещественных чисел задающий коэффициенты полинома
     */
    Polynomial(double[] a) {
        body = a.clone();
        length = a.length;
    }

    /**
     * Конструктор класса.
     *
     * @param a - массив целых чисел задающий коэффициенты полинома
     */
    Polynomial(int[] a) {
        body = new double[a.length];
        length = a.length;
        for (int i = 0; i < length; i++) {
            body[i] = a[i];
        }
    }

    /**
     * Функция суммы двух полиномов.
     *
     * @param a - полином прибавляемый к текущему
     * @return возвращает полином полученный из суммы двух
     */
    Polynomial plus(Polynomial a) {
        int tmpLen;
        double[] tmpArr;
        if (length < a.length) {
            tmpLen = a.length;
            tmpArr = new double[tmpLen];
            for (int i = 0; i < length; i++) {
                tmpArr[i] = body[i] + a.body[i];
            }
            System.arraycopy(a.body, length, tmpArr, length, tmpLen - length);
        } else {
            tmpLen = length;
            tmpArr = new double[tmpLen];
            for (int i = 0; i < a.length; i++) {
                tmpArr[i] = body[i] + a.body[i];
            }
            System.arraycopy(body, a.length, tmpArr, a.length, tmpLen - a.length);
        }
        while (tmpArr[tmpLen - 1] == 0 && tmpLen != 1) {
            tmpLen--;
        }
        double[] arrRes = new double[tmpLen];
        System.arraycopy(tmpArr, 0, arrRes, 0, tmpLen);
        return new Polynomial(arrRes);
    }

    /**
     * Функция разности двух полиномов.
     *
     * @param a - полином вычитаемый из текущего
     * @return возвращает полином полученный из разности двух
     */
    Polynomial minus(Polynomial a) {
        Polynomial tmp;
        double[] tmpArr = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            tmpArr[i] = -a.body[i];
        }
        tmp = new Polynomial(tmpArr);
        return plus(tmp);
    }

    /**
     * Функция произведения двух полиномов.
     *
     * @param a - полином, на который необходимо умножить
     * @return возвращает полином полученный из произведения двух
     */
    Polynomial times(Polynomial a) {
        Polynomial result = new Polynomial(new double[length + a.length - 1]);
        result.length = length + a.length - 1;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < a.length; j++) {
                result.body[i + j] += body[i] * a.body[j];
            }
        }
        while (result.body[result.length - 1] == 0 && result.length != 1) {
            result.length--;
        }
        return result;
    }

    /**
     * Функция взятия i-ой производной.
     *
     * @param x - номер производной
     * @return возвращет полином полученный взятием i-ой производной
     */
    Polynomial differentiate(int x) {
        if (x >= length) {
            return new Polynomial(new double[]{0});
        } else {
            double[] tmp = body.clone();
            double[] tmp2 = new double[length];
            int tmpLen = length;
            for (int i = 0; i < x; i++) {
                for (int j = 1; j < tmpLen; j++) {
                    tmp2[j - 1] = tmp[j] * j;
                }
                tmp = tmp2.clone();
                tmpLen--;
            }
            double[] resArr = new double[tmpLen];
            System.arraycopy(tmp2, 0, resArr, 0, tmpLen);
            return new Polynomial(resArr);
        }
    }

    /**
     * Функция считающая значение полинома в точке.
     *
     * @param x - точка
     * @return возвращает значение полинома в точке
     */
    double evaluate(double x) {
        double result = 0;
        double curX = 1;
        for (int i = 0; i < length; i++) {
            result = result + body[i] * curX;
            curX = curX * x;
        }
        return result;
    }

    /**
     * Функция проверки полиномов на совпадение.
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
        Polynomial a = (Polynomial) obj;
        return a.length == length && Arrays.equals(a.body, body);
    }

    /**
     * Функция представления полинома в привычном виде.
     *
     * @return возвращает строковое представление полинома
     */
    @Override
    public String toString() {
        if (length == 1 && body[0] == 0) {
            return "0";
        }
        String result = "";
        for (int i = length - 1; i >= 0; i--) {
            if (body[i] != 0) {
                if (i != length - 1) {
                    if (body[i] > 0) {
                        result = result + " + ";
                    } else {
                        result = result + " - ";
                    }
                }
                if (i == 1) {
                    if (body[i] > 0) {
                        if (body[i] == 1) {
                            result = result + "x";
                        } else {
                            result = result + body[i] + "x";
                        }
                    } else {
                        if (body[i] == -1) {
                            result = result + "x";
                        } else {
                            result = result + Math.abs(body[i]) + "x";
                        }
                    }
                } else if (i == 0) {
                    if (body[i] > 0) {
                        result = result + body[i];
                    } else {
                        result = result + Math.abs(body[i]);
                    }
                } else {
                    if (body[i] > 0) {
                        if (body[i] == 1) {
                            result = result + "x^" + i;
                        } else {
                            result = result + body[i] + "x^" + i;
                        }
                    } else {
                        if (body[i] == -1) {
                            result = result + "x^" + i;
                        } else {
                            result = result + Math.abs(body[i]) + "x^" + i;
                        }
                    }
                }
            }
        }
        return result;
    }
}