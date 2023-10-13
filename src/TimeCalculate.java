// Mustafa Erdem Gulsoy -- 20050111055
import java.io.*;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Random;
class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        next = null;
    }
}

class SinglyLinkedList {
    Node head = null;
    Node tail = null; // Eklenen tail

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public boolean isEmpty() {
        return getHead() == null;
    }

    public void addFirst(int newData) {
        Node newNode = new Node(newData);
        if (isEmpty()) {
            setHead(newNode);
            tail = newNode; // Tail, yalnızca tek eleman varsa baş elemanıdır
        } else {
            newNode.next = head; // Yeni düğümün sonraki elemanı eski baş elemanı olacak
            head = newNode; // Baş elemanı yeni düğüm olarak güncelliyoruz
        }
    }

    public void insertAt(int newData, int index) {
        Node newNode = new Node(newData);
        if (index == 0) {
            addFirst(newData);  // Eğer indeks 0 ise addFirst metodunu kullan
            return;
        }

        Node current = head;
        int count = 0;

        // indeksten bir önceki düğüme git
        while (current != null && count < index - 1) {
            current = current.next;
            count++;
        }

        // Eğer belirtilen indeks geçerli bir indeks değilse (örneğin liste boyutundan büyükse) bir uyarı ver.
        if (current == null) {
            System.out.println("Index out of bounds");
            return;
        }

        newNode.next = current.next;
        current.next = newNode;
    }

    public void addLast(int data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            setHead(newNode);
            tail = newNode; // Tail, başka hiç eleman yoksa baş elemanıdır
        } else {
            tail.next = newNode; // Eski tail'in sonraki elemanı yeni düğüm olacak
            tail = newNode; // Tail'i yeni düğüm olarak güncelliyoruz
        }
    }
}

class DynamicArray {
    private int[] data;
    private int size; // Mevcut eleman sayısı
    private int capacity; // Toplam kapasite

    public DynamicArray() {
        capacity = 1;
        size = 0;
        data = new int[capacity];
    }

    public int get(int index) {
        return data[index];
    }

    public void add(int value) {
        if (size == capacity) {
            capacity *= 2; // Kapasiteyi 2 katına çıkar
            int[] newData = new int[capacity];
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }
        data[size++] = value;
    }

    public void insertAt(int value, int index) {
        if (size == capacity) {
            capacity *= 2;
            int[] newData = new int[capacity];
            System.arraycopy(data, 0, newData, 0, index);
            newData[index] = value;
            System.arraycopy(data, index, newData, index + 1, size - index);
            data = newData;
        } else {
            System.arraycopy(data, index, data, index + 1, size - index);
            data[index] = value;
        }
        size++;
    }
}


public class TimeCalculate {

    public static void FillTheFile(String filename, int max, int min, int amount){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            Random random = new Random();
            for (int i = 0; i < amount; i++) {
                int randomNum = random.nextInt(max - min + 1) + min; // Sınırları belirtiyoruz.
                writer.write(Integer.toString(randomNum));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("File Write Error: " + e.getMessage());
        }
    }

    // 1A - Build an integer array that stores integers hold in 1Mint.txt file (The length of the array must be 1,000,000).
    public static void ReadTheFileArray(String filename, int[] array) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int index = 0;
            while ((line = br.readLine()) != null && index < array.length) {
                array[index++] = Integer.parseInt(line.trim());
            }
        } catch (IOException e) {
            System.out.println("File Read Error: " + e.getMessage());
        }
    }

    // 1B -  Insert an integer into the first index.
    // Array'in boyutu 1000000 olmalıdır. Bunu yapabilmek için son index'teki elemanı silip diğer tüm elemanları sağa kaydırabiliriz.
    public static void InsertFirstAndDeleteLast (int[] array, int newNum) {
        // Son elemanı silerek tüm elemanları birer birer kaydırıyoruz
        for (int i = array.length - 1; i > 0; i--) {
            array[i] = array[i - 1];
        }

        // İlk indekse yeni sayıyı ekliyoruz
        array[0] = newNum;
    }

    // 1C - Insert an integer into the last index.
    // Array'in boyutu 1000000 olmalıdır. Bunu yapabilmek için ilk index'teki elemanı silip diğer tüm elemanları sola kaydırabiliriz.
    public static void InsertLastAndDeleteFirst (int[] array, int newNum) {
        // İlk elemanı silerek tüm elemanları birer birer kaydırıyoruz
        for (int i = 0; i < array.length -1; i++) {
            array[i] = array[i + 1];
        }

        // Son indekse yeni sayıyı ekliyoruz
        array[999999] = newNum;
    }

    // 2A - Build an integer list that stores integers hold in 1Mint.txt

    public static void ReadTheFileLinkedList(String filename, SinglyLinkedList linkedList) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                int value = Integer.parseInt(line.trim()); // Satırları okuyarak integer değerlere dönüştürüyoruz
                linkedList.addLast(value);
            }
        } catch (IOException e) {
            System.out.println("File Read Error: " + e.getMessage());
        }
    }

    // 2D - Read the integer from the index 900,000.

    public static int ReadAt(SinglyLinkedList linkedList, int index){
        Node current = linkedList.head;
        int count = 0;
        while (count <= index){
            current = current.next;
            count++;
        }
        return current.data;
    }

    public static void ReadTheFileDynamicArray(String filename, DynamicArray dynamicArray){
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                dynamicArray.add(Integer.parseInt(line.trim()));
            }
        } catch (IOException e) {
            System.out.println("File Read Error: " + e.getMessage());
        }
    }


    public static void main(String[] args) {

        // İlk olarak 1Mimt.txt dosyasını 0 ile 1000000 arasından rastgele integer sayılar ile dolduralım.
        FillTheFile("1Mint.txt", 1000000, 0, 1000000);
        //max = 1000000;  // Rastgele seçilecek sayıların üst sınırı.
        //min = 0; // Rastgele seçilecek sayıların alt sınırı.
        //amount = 1000000; // Rastgele seçilecek sayıların toplam miktarı.

        // 1
        int[] array = new int[1000000]; // Array'in uzunluğunu belirledik.

        // A - Build an integer array2 that stores integers hold in 1Mint.txt file (The length of the array must be 1,000,000).
        LocalTime start = LocalTime.now();
        ReadTheFileArray("1Mint.txt", array);
        LocalTime finish = LocalTime.now();
        Duration difference = Duration.between(start, finish);
        System.out.println("1a) The integer array structure is built in " + difference.toMillis() + " milliseconds.");

        int[] newArray = array.clone();
        // B -  Insert an integer into the first index.
        start = LocalTime.now();
        InsertFirstAndDeleteLast(newArray, 24566);
        finish = LocalTime.now();
        difference = Duration.between(start, finish);
        System.out.println("1b) An integer is inserted into the beginning of the integer array in " + difference.toMillis() + " milliseconds.");
        newArray = array.clone();

        // C - Insert an integer into the last index.
        start = LocalTime.now();
        InsertLastAndDeleteFirst(newArray, 475245);
        finish = LocalTime.now();
        difference = Duration.between(start, finish);
        System.out.println("1c) An integer is inserted into the end of the integer array in " + difference.toMillis() + " milliseconds.");
        newArray = array.clone();

        // D - Read the integer from the index 900,000.
        start = LocalTime.now();
        int the_value = newArray[900000];
        finish = LocalTime.now();
        difference = Duration.between(start, finish);
        System.out.println("1d) An integer, which is " + the_value + ", is read from index 900,000 of the integer array in " + difference.toMillis() + " milliseconds.");
        newArray = array.clone();

        // E - Read the integer from the index 9.
        start = LocalTime.now();
        the_value = newArray[9];
        finish = LocalTime.now();
        difference = Duration.between(start, finish);
        System.out.println("1e) An integer, which is " + the_value + ", is read from index 9 of the integer array in " + difference.toMillis() + " milliseconds.");



        System.out.println("-------------------------------------");



        // 2
        //  Using LinkedList data structure (Not a doubly-linked list):
        SinglyLinkedList linkedList = new SinglyLinkedList();

        // A Build an integer list that stores integers hold in 1Mint.txt
        start = LocalTime.now();
        ReadTheFileLinkedList("1Mint.txt",linkedList);
        finish = LocalTime.now();
        difference = Duration.between(start, finish);
        System.out.println("2a) The integer LinkedList structure is built in " + difference.toMillis() + " milliseconds.");

        // B Insert an integer into the head.
        start = LocalTime.now();
        linkedList.addFirst(618451);
        finish = LocalTime.now();
        difference = Duration.between(start, finish);
        System.out.println("2b) An integer is inserted into the beginning of the integer LinkedList in " + difference.toMillis() + " milliseconds.");

        // C Insert an integer into the index 900,000.
        start = LocalTime.now();
        linkedList.insertAt(265416, 900000);
        finish = LocalTime.now();
        difference = Duration.between(start, finish);
        System.out.println("2c) An integer is inserted into the 900.000's index of the integer LinkedList in " + difference.toMillis() + " milliseconds.");

        // D - Read the integer from the index 900,000.
        start = LocalTime.now();
        the_value = ReadAt(linkedList, 900000);
        finish = LocalTime.now();
        difference = Duration.between(start, finish);
        System.out.println("2d) An integer, which is " + the_value + ", is read from index 900,000 of the integer LinkedList in " + difference.toMillis() + " milliseconds.");

        // E - Read the integer from the index 9.
        start = LocalTime.now();
        the_value = ReadAt(linkedList, 9);
        finish = LocalTime.now();
        difference = Duration.between(start, finish);
        System.out.println("2e) An integer, which is " + the_value + ", is read from index 9 of the integer LinkedList in " + difference.toMillis() + " milliseconds.");


        System.out.println("-------------------------------------");


        // 3
        DynamicArray dynamicArray = new DynamicArray();

        // A Build an integer array2 that stores integers hold in 1Mint.txt
        start = LocalTime.now();
        ReadTheFileDynamicArray("1Mint.txt", dynamicArray);
        finish = LocalTime.now();
        difference = Duration.between(start, finish);
        System.out.println("3a) The Dynamic Array structure is built in " + difference.toMillis() + " milliseconds.");

        // B Insert an integer into the first index.
        start = LocalTime.now();
        dynamicArray.insertAt(618451, 0);
        finish = LocalTime.now();
        difference = Duration.between(start, finish);
        System.out.println("3b) An integer is inserted into the beginning of the Dynamic Array in " + difference.toMillis() + " milliseconds.");

        // C Insert an integer into the last index.
        start = LocalTime.now();
        dynamicArray.add(265416);
        finish = LocalTime.now();
        difference = Duration.between(start, finish);
        System.out.println("3c) An integer is added to the end of the Dynamic Array in " + difference.toMillis() + " milliseconds.");

        // D - Read the integer from the index 900,000.
        start = LocalTime.now();
        the_value = dynamicArray.get(900000);
        finish = LocalTime.now();
        difference = Duration.between(start, finish);
        System.out.println("3d) An integer, which is " + the_value + ", is read from index 900,000 of the Dynamic Array in " + difference.toMillis() + " milliseconds.");

        // E - Read the integer from the index 9.
        start = LocalTime.now();
        the_value = dynamicArray.get(9);
        finish = LocalTime.now();
        difference = Duration.between(start, finish);
        System.out.println("3e) An integer, which is " + the_value + ", is read from index 9 of the Dynamic Array in " + difference.toMillis() + " milliseconds.");




// 50 MILLION **************************************************************************************************************************************************
        System.out.println("**************************************************************************************************************************************************");


        // İlk olarak 50Mimt.txt dosyasını 0 ile 50000000 arasından rastgele integer sayılar ile dolduralım.
        FillTheFile("50Mint.txt", 50000000, 0, 50000000);
        //max = 50000000;  // Rastgele seçilecek sayıların üst sınırı.
        //min = 0; // Rastgele seçilecek sayıların alt sınırı.
        //amount = 50000000; // Rastgele seçilecek sayıların toplam miktarı.

        // 1
        int[] array2 = new int[50000000]; // Array'in uzunluğunu belirledik.

        // A - Build an integer array that stores integers hold in 1Mint.txt file (The length of the array must be 1,000,000).
        LocalTime start2 = LocalTime.now();
        ReadTheFileArray("50Mint.txt", array2);
        LocalTime finish2 = LocalTime.now();
        Duration difference2 = Duration.between(start2, finish2);
        System.out.println("1a) The integer array structure is built in " + difference2.toMillis() + " milliseconds.");

        int[] newArray2 = array2.clone();
        // B -  Insert an integer into the first index.
        start2 = LocalTime.now();
        InsertFirstAndDeleteLast(newArray2, 24566);
        finish2 = LocalTime.now();
        difference2 = Duration.between(start2, finish2);
        System.out.println("1b) An integer is inserted into the beginning of the integer array in " + difference2.toMillis() + " milliseconds.");
        newArray2 = array2.clone();

        // C - Insert an integer into the last index.
        start2 = LocalTime.now();
        InsertLastAndDeleteFirst(newArray2, 475245);
        finish2 = LocalTime.now();
        difference2 = Duration.between(start2, finish2);
        System.out.println("1c) An integer is inserted into the end of the integer array in " + difference2.toMillis() + " milliseconds.");
        newArray2 = array2.clone();

        // D - Read the integer from the index 900,000.
        start2 = LocalTime.now();
        int the_value2 = newArray2[900000];
        finish2 = LocalTime.now();
        difference2 = Duration.between(start2, finish2);
        System.out.println("1d) An integer, which is " + the_value2 + ", is read from index 900,000 of the integer array in " + difference2.toMillis() + " milliseconds.");
        newArray2 = array2.clone();

        // E - Read the integer from the index 9.
        start2 = LocalTime.now();
        the_value2 = newArray2[9];
        finish2 = LocalTime.now();
        difference2 = Duration.between(start2, finish2);
        System.out.println("1e) An integer, which is " + the_value2 + ", is read from index 9 of the integer array in " + difference2.toMillis() + " milliseconds.");



        System.out.println("-------------------------------------");



        // 2
        //  Using LinkedList data structure (Not a doubly-linked list):
        SinglyLinkedList linkedList2 = new SinglyLinkedList();

        // A Build an integer list that stores integers hold in 1Mint.txt
        start2 = LocalTime.now();
        ReadTheFileLinkedList("50Mint.txt",linkedList2);
        finish2 = LocalTime.now();
        difference2 = Duration.between(start2, finish2);
        System.out.println("2a) The integer LinkedList structure is built in " + difference2.toMillis() + " milliseconds.");

        // B Insert an integer into the head.
        start2 = LocalTime.now();
        linkedList2.addFirst(618451);
        finish2 = LocalTime.now();
        difference2 = Duration.between(start2, finish2);
        System.out.println("2b) An integer is inserted into the beginning of the integer LinkedList in " + difference2.toMillis() + " milliseconds.");

        // C Insert an integer into the index 900,000.
        start2 = LocalTime.now();
        linkedList2.insertAt(265416, 900000);
        finish2 = LocalTime.now();
        difference2 = Duration.between(start2, finish2);
        System.out.println("2c) An integer is inserted into the 900.000's index of the integer LinkedList in " + difference2.toMillis() + " milliseconds.");

        // D - Read the integer from the index 900,000.
        start2 = LocalTime.now();
        the_value2 = ReadAt(linkedList2, 900000);
        finish2 = LocalTime.now();
        difference2 = Duration.between(start2, finish2);
        System.out.println("2d) An integer, which is " + the_value2 + ", is read from index 900,000 of the integer LinkedList in " + difference2.toMillis() + " milliseconds.");

        // E - Read the integer from the index 9.
        start2 = LocalTime.now();
        the_value2 = ReadAt(linkedList2, 9);
        finish2 = LocalTime.now();
        difference2 = Duration.between(start2, finish2);
        System.out.println("2e) An integer, which is " + the_value2 + ", is read from index 9 of the integer LinkedList in " + difference2.toMillis() + " milliseconds.");


        System.out.println("-------------------------------------");


        // 3
        DynamicArray dynamicArray2 = new DynamicArray();

        // A Build an integer array2 that stores integers hold in 1Mint.txt
        start2 = LocalTime.now();
        ReadTheFileDynamicArray("50Mint.txt", dynamicArray2);
        finish2 = LocalTime.now();
        difference2 = Duration.between(start2, finish2);
        System.out.println("3a) The Dynamic Array structure is built in " + difference2.toMillis() + " milliseconds.");

        // B Insert an integer into the first index.
        start2 = LocalTime.now();
        dynamicArray2.insertAt(618451, 0);
        finish2 = LocalTime.now();
        difference2 = Duration.between(start2, finish2);
        System.out.println("3b) An integer is inserted into the beginning of the Dynamic Array in " + difference2.toMillis() + " milliseconds.");

        // C Insert an integer into the last index.
        start2 = LocalTime.now();
        dynamicArray2.add(265416);
        finish2 = LocalTime.now();
        difference2 = Duration.between(start2, finish2);
        System.out.println("3c) An integer is added to the end of the Dynamic Array in " + difference2.toMillis() + " milliseconds.");

        // D - Read the integer from the index 900,000.
        start2 = LocalTime.now();
        the_value2 = dynamicArray2.get(900000);
        finish2 = LocalTime.now();
        difference2 = Duration.between(start2, finish2);
        System.out.println("3d) An integer, which is " + the_value2 + ", is read from index 900,000 of the Dynamic Array in " + difference2.toMillis() + " milliseconds.");

        // E - Read the integer from the index 9.
        start2 = LocalTime.now();
        the_value2 = dynamicArray2.get(9);
        finish2 = LocalTime.now();
        difference2 = Duration.between(start2, finish2);
        System.out.println("3e) An integer, which is " + the_value2 + ", is read from index 9 of the Dynamic Array in " + difference2.toMillis() + " milliseconds.");



    }
}
