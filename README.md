# Algorithmics I (H) - Assessed exercise

This algorithmic project builds word ladder between two words within a dictionary of words with equal length. It is able to build the shortest path using a weighted graph between two words.

## 1. Code

[Github link where the code is stored](https://github.com/sebgranie/wordladder)

### 1.1. Execution

In order to compile the project, thanks to the `Makefile`, be at the root of the project and do the followed command in your shell :

```sh
make Main.java
```

This command will compile all the `*.java` files and you will be ready to execute both main functions in the folders : **wordladder** and **dijkstra**.

#### 1.1.1 Wordladder - Program 1

You can move on to the **wordladder** folder with the command :

```sh
cd wordladder/
```

Afterwards, you can execute the code with the followed form :

```sh
java Main.java ../name_of_dictionary word1 word2
```

- **Main.java** : it is the main function that contains the code to build the shortest path between the two mentioned words in the shell command.
- **name_of_dictionary** is the dictionary you want to use to build the ladder of shortest path.
- **word1** and **word2** should be words present in the dictionary mentioned above. They can be out of the dictionary and it is not relevant to do so in this project. The ladder will not be able to be build.

* **Expected output**
  - The size of the dictionnary used;
  - The beginning Word;
  - The ending Word;
  - The minimum path distance;
  - The corresponding optimal ladder found from word1 to word2;
  - The elapsed time to compute the program.

Note that the **minimum path distance** in the program 1 represents the number of step from word1 to word2.

#### 1.1.2 Dijkstra - Program 2

If you are in the **wordladder** folder, uou can move on to the **dijkstra** folder with the command :

```sh
cd ../dijkstra/
```

If you are at the root folder, just do this command :

```sh
cd dijkstra/
```

Afterwards, you can actually execute the code with the same command line as before :

```sh
java Main.java ../name_of_dictionary word1 word2
```

- **Main.java** : it is the main function that contains the code to build the shortest path between the two mentioned words in the shell command.
- **name_of_dictionary** is the dictionary you want to use to build the ladder of shortest path. It should be a **\*.txt** file.
- **word1** and **word2** should be words present in the dictionary mentioned above. They can be out of the dictionary and it is not relevant to do so in this project. The ladder will not be able to be build.

* **Expected output**
  - The size of the dictionnary used;
  - The beginning Word;
  - The ending Word;
  - The minimum path distance;
  - The corresponding optimal ladder found from word1 to word2;
  - The elapsed time to compute the program.

Note that the **minimum path distance** in the program 2 represents the sum of the weights on each edges of the ladder to go from word1 to word2. The weights are equal to the distance in the alphabet to the letter change between two words that differ from only a letter at the same index. This sum is computed to be the smallest one using the dictionary mentioned in the command line.

### 1.2. Clean and re-compile

At any time, it is possible to clean all the **\*.class** files by returning to the root folder and enter the followed command line :

```sh
make clean
```

You can compile again with :

```sh
make Main.java
```
