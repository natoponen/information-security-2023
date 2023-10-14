---
## Front matter
title: "Отчет по лабораторной работе по предмету Математические основы защиты информации и информационной безопасности"
subtitle: "Лабораторная работа №3. Шифрование гаммированием"
author: "Никита Андреевич Топонен"

## Generic otions
lang: ru-RU
toc-title: "Содержание"

## Bibliography
bibliography: bib/cite.bib
csl: pandoc/csl/gost-r-7-0-5-2008-numeric.csl

## Pdf output format
toc: true # Table of contents
toc-depth: 2
lof: true # List of figures
lot: true # List of tables
fontsize: 12pt
linestretch: 1.5
papersize: a4
documentclass: scrreprt
## I18n polyglossia
polyglossia-lang:
  name: russian
  options:
	- spelling=modern
	- babelshorthands=true
polyglossia-otherlangs:
  name: english
## I18n babel
babel-lang: russian
babel-otherlangs: english
## Fonts
mainfont: PT Serif
romanfont: PT Serif
sansfont: PT Sans
monofont: PT Mono
mainfontoptions: Ligatures=TeX
romanfontoptions: Ligatures=TeX
sansfontoptions: Ligatures=TeX,Scale=MatchLowercase
monofontoptions: Scale=MatchLowercase,Scale=0.9
## Biblatex
biblatex: true
biblio-style: "gost-numeric"
biblatexoptions:
  - parentracker=true
  - backend=biber
  - hyperref=auto
  - language=auto
  - autolang=other*
  - citestyle=gost-numeric
## Pandoc-crossref LaTeX customization
figureTitle: "Рис."
tableTitle: "Таблица"
listingTitle: "Листинг"
lofTitle: "Список иллюстраций"
lotTitle: "Список таблиц"
lolTitle: "Листинги"
## Misc options
indent: true
header-includes:
  - \usepackage{indentfirst}
  - \usepackage{float} # keep figures where there are in the text
  - \floatplacement{figure}{H} # keep figures where there are in the text
---

# Цель работы

Цель работы --- познакомиться с шифрованием гаммированием.

# Задание

1. Реализовать шифрование гаммированием.

# Теоретическое введение

Гаммирование, или Шифр XOR, — метод симметричного шифрования, заключающийся в «наложении» последовательности, состоящей из случайных чисел, на открытый текст. Последовательность случайных чисел называется гамма-последовательностью и используется для зашифровывания и расшифровывания данных. Суммирование обычно выполняется в каком-либо конечном поле.

# Выполнение лабораторной работы

## Шифрование гаммировнием

В рамках данной лабораторной работы я реализовал шифрование гаммированием на языке Java. Ниже приведен код:

```java
import java.util.ArrayList;

public class Gamma {
    private final ArrayList<Character> alphabet = new ArrayList<>();
    private final int alphabetSize;

    public Gamma() {
        for (char symbol = 'a'; symbol <= 'z'; symbol++) {
            alphabet.add(symbol);
        }
        alphabetSize = alphabet.size();
    }

    public String encrypt(String text, int key) {
        StringBuilder cryptogram = new StringBuilder();

        key = key % alphabetSize;
        for (int i = 0; i < text.length(); i++) {
            char symbol = text.charAt(i);
            if (symbol == ' ') {
                cryptogram.append(" ");
            } else {
                int index = alphabet.indexOf(symbol);
                index = xor(index, random(key, i)) % alphabetSize;
                cryptogram.append(alphabet.get(index));
            }
        }
        return cryptogram.toString();
    }

    public String decrypt(String text, int key) {
        return encrypt(text, key);
    }

    private int random(int number, int count) {
        int[] numbers = new int[]{5, 67, 21, 76, 13, 86, 32, 87, 3, 98, 21,
            9, 11, 54, 94, 1, 4, 7, 55, 44, 32, 95, 33, 22, 64, 87, 30, 39,
            65};
        return numbers[(number * count) % numbers.length] % alphabet.size();
    }

    private int xor(int a, int b) {
        return a ^ b;
    }
}
```

```java
public class Main {
    public static void main(String[] args) {
        Gamma gamma = new Gamma();
        String message = "testing gamma";

        String encryptedMessage = gamma.encrypt(message, 33);
        System.out.println(encryptedMessage);

        String decryptedMessage = gamma.decrypt(encryptedMessage, 33);
        System.out.println(decryptedMessage);
    }
}
```

Результаты выполнения программы на иллюстрации (рис. @fig:001).

![Шифрование гаммированием](image/gamma.png){#fig:001 width=70%}

# Выводы

В рамках данной лабораторной работы я познакомился с шифрованием гаммированием или XOR шифром. Также реализовал данный шифр на языке Java.

# Список литературы{.unnumbered}