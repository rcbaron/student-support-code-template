# Lösungen Worksheet3 ANTLR

## Eigene ANTLR Grammatik
Hier ist die entstandene Grammatik für die Sprache die im Worksheet beschrieben wurde:

https://github.com/rcbaron/student-support-code-template/blob/master/src/main/antlr/MyLanguage.g4

Der dazugehörige Parse-Tree:
<img width="1699" height="368" alt="image" src="https://github.com/user-attachments/assets/3ccc6ed9-2027-45ff-a2bc-b37b8b15d307" />


Hierbei wurde auf die Reihenfolge der Definitiionen der Lexer geachtet aufgrund unterschiedlicher Vorrangsregeln.

## PrettyPrinter der eigenen Sprache
Hier ein Beipsiel der Ausgabe des Pretty Printers:

<img width="448" height="359" alt="image" src="https://github.com/user-attachments/assets/a12462ca-85c8-4904-a476-537cacfcb7dd" />

- der Eingabe String


<img width="456" height="289" alt="image" src="https://github.com/user-attachments/assets/c3c2cc10-a9dc-4d17-b289-1696d77e96c1" />

- der Ausgabe String in der vorgegebenen Formatierung


## AST Builder und PrettyPrinter für den AST
Hier einmal der ASTBuilder der mithilfe des packeges AST und den dazugehörigen Records zugriff auf die benötigten Nodes bekommt:

AST Builder ->             https://github.com/rcbaron/student-support-code-template/blob/master/src/main/java/ASTBuilder.java

AST Inteface + Records ->  https://github.com/rcbaron/student-support-code-template/tree/master/src/main/java/ast

Der PrettyPrinter für den AST und die dazugehörige Ausgabe:

AST PrettyPrinter ->       https://github.com/rcbaron/student-support-code-template/blob/master/src/main/java/ASTPrettyPrinter.java


Die Ausgabe des PrettyPrinters für den AST 

<img width="506" height="334" alt="image" src="https://github.com/user-attachments/assets/ad7699a1-09d0-4626-8628-7c60b92ce450" />



